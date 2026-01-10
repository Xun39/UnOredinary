package net.xun.unoredinary.block.entity.container;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.xun.unoredinary.block.entity.TransenchantingTableBlockEntity;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.registry.UOMenuTypes;
import net.xun.unoredinary.registry.UOSounds;

import java.util.Objects;

public class TransenchantingTableMenu extends AbstractContainerMenu {
    private static final int TRANSLATOR_SLOT = 0;
    private static final int TRANSENCHANT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int PLAYER_INV_START = 3;
    private static final int PLAYER_INV_END = PLAYER_INV_START + 36;

    public final TransenchantingTableBlockEntity blockEntity;
    public final ItemStackHandler inventory;
    private final ContainerLevelAccess access;
    private final Level level;

    public TransenchantingTableMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, getBlockEntity(playerInventory, data));
    }

    public TransenchantingTableMenu(final int containerId, final Inventory playerInventory, final TransenchantingTableBlockEntity blockEntity) {
        super(UOMenuTypes.TRANSENCHANTING_TABLE.get(), containerId);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.getInventory();
        this.level = playerInventory.player.level();
        this.access = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        addInputSlots();
        addPlayerInventory(playerInventory);
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();

        if (!level.isClientSide) {
            updatePreview();
        }
    }

    private void updatePreview() {
        if (!blockEntity.canTransenchant()) {
            clearOutputSlot();
            return;
        }

        if (!inventory.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            return;
        }

        ItemStack preview = blockEntity.getPreviewResult();
        if (!ItemStack.matches(preview, inventory.getStackInSlot(OUTPUT_SLOT))) {
            inventory.setStackInSlot(OUTPUT_SLOT, preview);
        }
    }

    private void clearOutputSlot() {
        if (!inventory.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            inventory.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack slotStackCopy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (!slot.hasItem()) {
            return slotStackCopy;
        }

        ItemStack slotStack = slot.getItem();
        slotStackCopy = slotStack.copy();

        if (index == OUTPUT_SLOT) {
            if (!this.moveItemStackTo(slotStack, PLAYER_INV_START, PLAYER_INV_END, true)) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
            return slotStackCopy;
        }

        if (index >= PLAYER_INV_START && index < PLAYER_INV_END) {
            if (!this.moveItemStackTo(slotStack, TRANSLATOR_SLOT, OUTPUT_SLOT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < PLAYER_INV_START) {
            if (!this.moveItemStackTo(slotStack, PLAYER_INV_START, PLAYER_INV_END, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (slotStack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (slotStack.getCount() == slotStackCopy.getCount()) {
            return ItemStack.EMPTY;
        }

        return slotStackCopy;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, UOBlocks.TRANSENCHANTING_TABLE.get());
    }

    private void addInputSlots() {
        // Translator
        addSlot(new SlotItemHandler(inventory, TRANSLATOR_SLOT, 18, 34));

        // Transenchant target
        addSlot(new SlotItemHandler(inventory, TRANSENCHANT_SLOT, 143, 48) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Tags.Items.ENCHANTABLES) || stack.is(Items.BOOK);
            }
        });

        // Output
        addSlot(new SlotItemHandler(inventory, OUTPUT_SLOT, 143, 22) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);
                blockEntity.commitTransenchant(player);
                inventory.setStackInSlot(2, ItemStack.EMPTY);

                level.playSound(
                        null,
                        blockEntity.getBlockPos(),
                        UOSounds.TRANSENCHANTMENT_TABLE_USE.get(),
                        SoundSource.BLOCKS,
                        1.0F,
                        level.random.nextFloat() * 0.1F + 0.9F
                );
            }
        });
    }

    private void addPlayerInventory(Inventory playerInventory) {
        // Player Inventory
        for (int row = 0; row < 3; ++row) {
            for (int coloumn = 0; coloumn < 9; ++coloumn) {
                addSlot(new Slot(playerInventory, coloumn + row * 9 + 9, 8 + coloumn * 18, 84 + row * 18));
            }
        }

        // Hotbar
        for (int coloumn = 0; coloumn < 9; ++coloumn) {
            addSlot(new Slot(playerInventory, coloumn, 8 + coloumn * 18, 142));
        }
    }

    private static TransenchantingTableBlockEntity getBlockEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");

        BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(data.readBlockPos());

        if (!(blockEntity instanceof TransenchantingTableBlockEntity transenchantingTable)) {
            throw new IllegalStateException("Block entity at " + data.readBlockPos() + " is not a TransenchantingTableBlockEntity!");
        }

        return transenchantingTable;
    }
}
