package net.xun.unoredinary.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.xun.lib.common.api.block.entity.ITickableBlockEntity;
import net.xun.unoredinary.block.entity.container.TransenchantingTableMenu;
import net.xun.unoredinary.registry.UOBlockEntityTypes;
import net.xun.unoredinary.util.TransenchantmentHelper;
import org.jetbrains.annotations.Nullable;

public class TransenchantingTableBlockEntity extends EnchantingTableBlockEntity implements MenuProvider, ITickableBlockEntity {
    public static final int TRANSENCHANTER_SLOT = 0;
    public static final int TARGET_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    public static final int INVENTORY_SIZE = OUTPUT_SLOT + 1;

    private final ItemStackHandler inventory;
    private boolean outputReady;

    public TransenchantingTableBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
        this.inventory = createHandler();
    }

    @Override
    public BlockEntityType<?> getType() {
        return UOBlockEntityTypes.TRANSENCHANTING_TABLE.get();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putBoolean("OutputReady", outputReady);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        outputReady = tag.getBoolean("OutputReady");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        loadAdditional(tag, registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider registries) {
        handleUpdateTag(packet.getTag(), registries);
    }

    public boolean canTransenchant() {
        return TransenchantmentHelper.canTransenchant(
                inventory.getStackInSlot(TRANSENCHANTER_SLOT),
                inventory.getStackInSlot(TARGET_SLOT)
        );
    }

    public ItemStack getPreviewResult() {
        return TransenchantmentHelper.createPreviewResult(
                inventory.getStackInSlot(TRANSENCHANTER_SLOT),
                inventory.getStackInSlot(TARGET_SLOT)
        );
    }

    /**
     * Marks the currently generated output as a real, confirmed result.
     * The actual transaction is performed by the menu on the server.
     */
    public void setOutputReady(boolean ready) {
        if (this.outputReady == ready) {
            return;
        }

        this.outputReady = ready;
        setChanged();

        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(
                    getBlockPos(),
                    getBlockState(),
                    getBlockState(),
                    Block.UPDATE_CLIENTS
            );
        }
    }

    public boolean isOutputReady() {
        return outputReady;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public NonNullList<ItemStack> getDrops() {
        NonNullList<ItemStack> drops = NonNullList.create();

        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (i == OUTPUT_SLOT && !outputReady) {
                continue;
            }

            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                drops.add(stack.copy());
            }
        }

        return drops;
    }

    @Override
    public void clientTick(Level level, BlockPos pos, BlockState state) {
        bookAnimationTick(level, pos, state, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("unoredinary.container.transenchanting_table");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new TransenchantingTableMenu(containerId, playerInventory, this);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(INVENTORY_SIZE) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();

                if (slot != OUTPUT_SLOT) {
                    setOutputReady(false);
                } else if (getStackInSlot(OUTPUT_SLOT).isEmpty()) {
                    setOutputReady(false);
                }

                if (level != null) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
                }
            }
        };
    }
}
