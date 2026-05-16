package net.xun.unoredinary.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
    public static final int TRANSENCHANTOR_SLOT = 0;
    public static final int TRANSENCHANTING_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    public static final int INVENTORY_SIZE = OUTPUT_SLOT + 1;

    private final ItemStackHandler inventory;

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
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
    }

    public boolean canTransenchant() {
        return TransenchantmentHelper.canTransenchant(
                inventory.getStackInSlot(TRANSENCHANTOR_SLOT),
                inventory.getStackInSlot(TRANSENCHANTING_SLOT)
        );
    }

    public ItemStack getPreviewResult() {
        return TransenchantmentHelper.createPreviewResult(
                inventory.getStackInSlot(TRANSENCHANTOR_SLOT),
                inventory.getStackInSlot(TRANSENCHANTING_SLOT)
        );
    }

    public void commitTransenchant(Player player) {
        TransenchantmentHelper.commitFullTransenchant(
                player,
                inventory.getStackInSlot(TRANSENCHANTOR_SLOT),
                inventory.getStackInSlot(TRANSENCHANTING_SLOT)
        );

        setChanged();
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public NonNullList<ItemStack> getDrops() {
        NonNullList<ItemStack> drops = NonNullList.create();
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (i != OUTPUT_SLOT) {
                drops.add(inventory.getStackInSlot(i));
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

                if (level != null) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
                }
            }
        };
    }
}
