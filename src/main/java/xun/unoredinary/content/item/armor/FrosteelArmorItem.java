package xun.unoredinary.content.item.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FrosteelArmorItem extends UOArmorItem {

    public FrosteelArmorItem(Type type) {
        super(ModArmorMaterials.FROSTEEL, type,
                new Properties().durability(type.getDurability(35)));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player) {

            if (!player.isInWater()) {

                BlockPos playerPos = player.getBlockPosBelowThatAffectsMyMovement();

                int radius = 3;
                int radiusSquared = radius * radius;

                if (hasCorrectArmorInSlot(player, FEET_SLOT, ModArmorMaterials.FROSTEEL)) {

                    BlockPos.betweenClosed(playerPos.offset(-radius, 0, -radius), playerPos.offset(radius, 0, radius)).forEach(pos -> {

                        double distanceSquared = pos.distSqr(playerPos);
                        if (distanceSquared <= radiusSquared) {

                            BlockState adjacentState = level.getBlockState(pos);
                            if (adjacentState.is(Blocks.WATER)) {
                                level.setBlock(pos, Blocks.FROSTED_ICE.defaultBlockState(), 3);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return true;
    }
}
