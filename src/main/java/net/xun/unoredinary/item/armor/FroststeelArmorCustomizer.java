package net.xun.unoredinary.item.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.lib.common.api.util.EquipmentSlotsUtils;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.server.UOServerConfig;
import net.xun.unoredinary.registry.UOArmorMaterials;

@EventBusSubscriber(modid = UnOredinary.MOD_ID)
public class FroststeelArmorCustomizer extends AbstractEffectArmorCustomizer {
    @Override
    protected void armorEffectTick(Level level, LivingEntity entity, ArmorItem item) {
        if (UOServerConfig.armorEffectConfig.froststeelConfig.enableFrostWalker.get())
            handleFrostWalkerEffect(entity, level);
    }

    @Override
    protected boolean shouldApplyArmorEffect() {
        return UOServerConfig.armorEffectConfig.froststeelConfig.enable.get();
    }

    @SubscribeEvent
    public static void onInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity living))
            return;

        if (!(living instanceof Player player))
            return;

        if (!UOServerConfig.armorEffectConfig.froststeelConfig.immuneToHotFloorDamage.get())
            return;

        if (EquipmentSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET, UOArmorMaterials.FROSTSTEEL)) {
            event.setInvulnerable(event.getSource().is(DamageTypeTags.BURN_FROM_STEPPING));
        }
    }

    private static void handleFrostWalkerEffect(LivingEntity entity, Level level) {
        if (!EquipmentSlotsUtils.isArmorMaterialInSlot(entity, EquipmentSlot.FEET, UOArmorMaterials.FROSTSTEEL))
            return;

        if (level.isClientSide || !entity.onGround())
            return;

        BlockPos groundPos = entity.getBlockPosBelowThatAffectsMyMovement();

        int radius = UOServerConfig.armorEffectConfig.froststeelConfig.frostWalkerRadius.getAsInt();

        BlockPosUtils.getDisc(groundPos, radius).forEach(pos -> {
            if (pos.closerToCenterThan(entity.position(), radius)) {
                freezeNearbyBlock(level, pos, entity);
            }
        });
    }

    private static void freezeNearbyBlock(Level level, BlockPos pos, LivingEntity entity) {
        BlockState currentState = level.getBlockState(pos);
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        if (currentState.is(Blocks.WATER) && currentState.getFluidState().isSource()) {
            if (aboveState.isAir() || aboveState.canBeReplaced()) {

                level.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
                level.gameEvent(entity, GameEvent.BLOCK_PLACE, pos);

                level.scheduleTick(pos, Blocks.FROSTED_ICE, level.getRandom().nextInt(60) + 20);
            }
        }
    }
}
