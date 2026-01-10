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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.xun.unoredinary.block.entity.container.TransenchantingTableMenu;
import net.xun.unoredinary.registry.UOBlockEntityTypes;
import net.xun.unoredinary.util.TransenchantmentHelper;
import org.jetbrains.annotations.Nullable;

public class TransenchantingTableBlockEntity extends BlockEntity implements MenuProvider {
    public static final int TRANSLATOR_SLOT = 0;
    public static final int TRANSENCHANT_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    public static final int INVENTORY_SIZE = OUTPUT_SLOT + 1;

    private final ItemStackHandler inventory;

    public TransenchantingTableBlockEntity(BlockPos pos, BlockState blockState) {
        super(UOBlockEntityTypes.TRANSENCHANTING_TABLE.get(), pos, blockState);
        this.inventory = createHandler();
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
                inventory.getStackInSlot(TRANSLATOR_SLOT),
                inventory.getStackInSlot(TRANSENCHANT_SLOT)
        );
    }

    public ItemStack getPreviewResult() {
        return TransenchantmentHelper.createPreviewResult(
                inventory.getStackInSlot(TRANSLATOR_SLOT),
                inventory.getStackInSlot(TRANSENCHANT_SLOT)
        );
    }

    public void commitTransenchant(Player player) {
        TransenchantmentHelper.commitFullTransenchant(
                player,
                inventory.getStackInSlot(TRANSLATOR_SLOT),
                inventory.getStackInSlot(TRANSENCHANT_SLOT)
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
