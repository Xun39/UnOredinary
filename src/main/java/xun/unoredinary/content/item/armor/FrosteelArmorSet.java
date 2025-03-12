package xun.unoredinary.content.item.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import xun.unoredinary.registry.UOArmorMaterials;

import static xun.unoredinary.util.InventoryUtils.*;

public class FrosteelArmorSet extends ArmorSet {

    public FrosteelArmorSet(String name, Holder<ArmorMaterial> armorMaterial, int durabilityFactor, Item.Properties armorProperties) {
        super(name, armorMaterial, durabilityFactor, armorProperties);
    }

    @Override
    protected ArmorItem createArmorItem(Holder<ArmorMaterial> armorMaterial, ArmorItem.Type type, Item.Properties properties) {
        return new ArmorItem(armorMaterial, type, properties.durability(type.getDurability(getDurabilityFactor()))) {

            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (entity instanceof Player player) {

                    if (!player.isInWater()) {

                        BlockPos playerPos = player.getBlockPosBelowThatAffectsMyMovement();

                        int radius = 3;
                        int radiusSquared = radius * radius;

                        if (hasCorrectArmorInSlot(player, FEET_SLOT, UOArmorMaterials.FROSTEEL)) {

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
        };
    }
}
