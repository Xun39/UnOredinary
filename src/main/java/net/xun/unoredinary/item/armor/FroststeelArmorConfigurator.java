package net.xun.unoredinary.item.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.xun.lib.common.api.item.armor.ArmorConfigurator;
import net.xun.lib.common.api.item.armor.ArmorType;
import net.xun.lib.common.api.util.ArmorSlotsUtils;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.common.UOCommonConfig;
import net.xun.unoredinary.registry.UOArmorMaterials;

@EventBusSubscriber(modid = UnOredinary.MOD_ID)
public class FroststeelArmorConfigurator implements ArmorConfigurator {
    @Override
    public ArmorItem createArmor(ArmorType type, Holder<ArmorMaterial> material, int durabilityFactor, Item.Properties props) {
        return new ArmorItem(material, type.getType(), props.durability(type.getType().getDurability(durabilityFactor))) {
            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (!(entity instanceof Player player) || !(stack.getItem() instanceof ArmorItem))
                    return;

                if (!UOCommonConfig.armorEffectConfig.froststeelConfig.enable.get())
                    return;

                if (UOCommonConfig.armorEffectConfig.froststeelConfig.enableFrostWalker.get()) {
                    handleFrostWalkerEffect(player, level);
                }
            }
        };
    }

    // Make the player immune to hot floor damage
    @SubscribeEvent
    public static void onInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity living))
            return;

        if (!(living instanceof Player player))
            return;

        if (UOCommonConfig.armorEffectConfig.froststeelConfig.enableHotFloorDamage.get())
            return;

        if (ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET.getIndex(), UOArmorMaterials.FROSTSTEEL)) {
            event.setInvulnerable(event.getSource().is(DamageTypeTags.BURN_FROM_STEPPING));
        }
    }

    private static void handleFrostWalkerEffect(Player player, Level level) {
        if (!ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET.getIndex(), UOArmorMaterials.FROSTSTEEL))
            return;

        if (level.isClientSide || !player.onGround())
            return;

        BlockPos groundPos = player.getBlockPosBelowThatAffectsMyMovement();

        int radius = 2;

        BlockPosUtils.getDisc(groundPos, radius).forEach(pos -> {
            if (pos.closerToCenterThan(player.position(), radius)) {
                freezeNearbyBlock(level, pos, player);
            }

            /* if (!level.getBlockState(pos).is(Blocks.WATER))
                return;

            BlockPos abovePos = pos.above();

            if (level.getBlockState(abovePos).isAir() && level.isUnobstructed(Blocks.FROSTED_ICE.defaultBlockState(), pos, CollisionContext.empty())) {
                level.setBlock(pos, Blocks.FROSTED_ICE.defaultBlockState(), Block.UPDATE_ALL);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
            }
             */
        });
    }

    private static void freezeNearbyBlock(Level level, BlockPos pos, Player player) {
        BlockState currentState = level.getBlockState(pos);
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        if (currentState.is(Blocks.WATER) && currentState.getFluidState().isSource()) {
            if (aboveState.isAir() || aboveState.canBeReplaced()) {

                level.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
                level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);

                level.scheduleTick(pos, Blocks.FROSTED_ICE, level.getRandom().nextInt(60) + 20);
            }
        }
    }
}
