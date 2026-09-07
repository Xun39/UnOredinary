package net.xun.unoredinary.block.entity.container;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
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
import net.xun.unoredinary.util.TransenchantmentHelper;

import java.util.Objects;

public class TransenchantingTableMenu extends AbstractContainerMenu {
    public static final int CONFIRM_BUTTON = 0;
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

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity.isOutputReady() ? 1 : 0;
            }

            @Override
            public void set(int value) {
                blockEntity.setOutputReady(value != 0);
            }
        });
    }

    @Override
    public void broadcastChanges() {
        if (!level.isClientSide) {
            updatePreview();
        }

        super.broadcastChanges();
    }

    private void updatePreview() {
        if (blockEntity.isOutputReady()) {
            return;
        }

        if (!blockEntity.canTransenchant()) {
            if (!inventory.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
                inventory.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            }
            return;
        }

        ItemStack preview = blockEntity.getPreviewResult();
        ItemStack currentOutput = inventory.getStackInSlot(OUTPUT_SLOT);

        if (!ItemStack.matches(currentOutput, preview)) {
            inventory.setStackInSlot(OUTPUT_SLOT, preview);
        }
    }

    @Override
    public boolean clickMenuButton(final Player player, final int id) {
        if (id != CONFIRM_BUTTON || level.isClientSide) {
            return false;
        }

        return clickConfirmButton(player);
    }

    private boolean clickConfirmButton(final Player player) {
        if (blockEntity.isOutputReady()) {
            return false;
        }

        ItemStack translator = inventory.getStackInSlot(TRANSLATOR_SLOT);
        ItemStack target = inventory.getStackInSlot(TRANSENCHANT_SLOT);

        if (!TransenchantmentHelper.canTransenchant(translator, target)) return false;

        int levelCost = TransenchantmentHelper.calculateLevelCost(translator);
        if (!player.isCreative() && player.experienceLevel < levelCost) {
            return false;
        }

        ItemStack result = TransenchantmentHelper.commitFullTransenchant(
                player,
                translator,
                target
        );

        if (result.isEmpty()) {
            return false;
        }

        inventory.setStackInSlot(OUTPUT_SLOT, result);
        blockEntity.setOutputReady(true);

        level.playSound(
                null,
                blockEntity.getBlockPos(),
                UOSounds.TRANSENCHANTMENT_TABLE_USE.get(),
                SoundSource.BLOCKS,
                1.0F,
                level.random.nextFloat() * 0.1F + 0.9F
        );

        broadcastChanges();
        return true;
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
        addSlot(new SlotItemHandler(inventory, TRANSLATOR_SLOT, 25, 63) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return TransenchantmentHelper.hasEnchantments(stack) && stack.get(DataComponents.ENCHANTMENTS) != null;
            }

            @Override
            public boolean mayPickup(Player player) {
                return !blockEntity.isOutputReady() && !getItem().isEmpty();
            }
        });

        // Transenchant target
        addSlot(new SlotItemHandler(inventory, TRANSENCHANT_SLOT, 67, 63) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Tags.Items.ENCHANTABLES) || stack.is(Items.BOOK);
            }

            @Override
            public boolean mayPickup(Player player) {
                return !blockEntity.isOutputReady() && !getItem().isEmpty();
            }
        });

        // Output
        addSlot(new SlotItemHandler(inventory, OUTPUT_SLOT, 142, 37) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return blockEntity.isOutputReady() && !getItem().isEmpty();
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                blockEntity.setOutputReady(false);
                super.onTake(player, stack);
            }
        });
    }

    private void addPlayerInventory(Inventory playerInventory) {
        // Player Inventory
        for (int row = 0; row < 3; ++row) {
            for (int coloumn = 0; coloumn < 9; ++coloumn) {
                addSlot(new Slot(playerInventory, coloumn + row * 9 + 9, 8 + coloumn * 18, 94 + row * 18));
            }
        }

        // Hotbar
        for (int coloumn = 0; coloumn < 9; ++coloumn) {
            addSlot(new Slot(playerInventory, coloumn, 8 + coloumn * 18, 152));
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
