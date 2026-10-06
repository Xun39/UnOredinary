package net.xun.unoredinary.item.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.xun.lib.common.api.util.BlockPosUtil;
import net.xun.lib.common.api.util.EquipmentSlotsUtil;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.UOConfigServer;
import net.xun.unoredinary.registry.UOArmorMaterials;
import net.xun.unoredinary.registry.UOParticleTypes;

@EventBusSubscriber(modid = UnOredinary.MOD_ID)
public class GlacialiteArmorCustomizer extends AbstractEffectArmorCustomizer {
    @Override
    protected void armorEffectTick(Level level, LivingEntity entity, ArmorItem item) {
        if (UOConfigServer.armorEffect.glacialite.immuneToSlowness)
            handleSlownessImmunity(entity);

        if (UOConfigServer.armorEffect.glacialite.enableFrostWalker)
            handleFrostWalkerEffect(entity, level);
    }

    @Override
    protected boolean shouldApplyArmorEffect() {
        return UOConfigServer.armorEffect.glacialite.enable;
    }

    @Override
    protected boolean canWalkOnPowderedSnow() {
        return UOConfigServer.armorEffect.glacialite.walkOnPowderSnow;
    }

    @SubscribeEvent
    public static void onInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity living))
            return;

        if (!(living instanceof Player player))
            return;

        if (!UOConfigServer.armorEffect.glacialite.immuneToHotFloor)
            return;

        if (EquipmentSlotsUtil.isArmorMaterialInSlot(player, EquipmentSlot.FEET, UOArmorMaterials.GLACIALITE)) {
            event.setInvulnerable(event.getSource().is(DamageTypeTags.BURN_FROM_STEPPING));
        }
    }

    private static void handleFrostWalkerEffect(LivingEntity entity, Level level) {
        if (!EquipmentSlotsUtil.isArmorMaterialInSlot(entity, EquipmentSlot.FEET, UOArmorMaterials.GLACIALITE))
            return;

        if (level.isClientSide || !entity.onGround())
            return;

        BlockPos groundPos = entity.getBlockPosBelowThatAffectsMyMovement();

        int radius = UOConfigServer.armorEffect.glacialite.frostWalkerRadius;

        BlockPosUtil.getDisc(groundPos, radius).forEach(pos -> {
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

    private static void handleSlownessImmunity(LivingEntity entity) {
        if (!EquipmentSlotsUtil.hasFullSetOfMaterial(entity, UOArmorMaterials.GLACIALITE))
            return;

        if (entity.getEffect(MobEffects.MOVEMENT_SLOWDOWN) != null) {
            MobEffectInstance effectInstance = entity.getEffect(MobEffects.MOVEMENT_SLOWDOWN);
            if (effectInstance == null)
                return;

            entity.removeEffect(effectInstance.getEffect());
        }
    }

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event) {
        Entity attacker = event.getSource().getDirectEntity();
        LivingEntity receiver = event.getEntity();

        if (UOConfigServer.armorEffect.glacialite.enable) {

            if (UOConfigServer.armorEffect.glacialite.thorns) {
                handleThornsEffect(event, attacker, receiver);
            }
        }
    }

    private static void handleThornsEffect(LivingDamageEvent.Pre event, Entity attacker, LivingEntity receiver) {
        if (UOConfigServer.armorEffect.onlyPlayer && !(receiver instanceof Player))
            return;

        if (!EquipmentSlotsUtil.hasFullSetOfMaterial(receiver, UOArmorMaterials.GLACIALITE))
            return;

        if (attacker instanceof LivingEntity) {
            attacker.hurt(event.getSource(), event.getOriginalDamage());
        }

        if (UOConfigServer.armorEffect.glacialite.damageParticle) {
            spawnHurtParticles(receiver);
        }
    }

    private static void spawnHurtParticles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return;

        if (target.level().getRandom().nextFloat() < 0.3F) {
            double centerX = target.getX() + target.level().getRandom().nextFloat();
            double centerY = target.getY() + target.getBbHeight() / 2.0;
            double centerZ = target.getZ() + target.level().getRandom().nextFloat();

            double halfWidth = target.getBbWidth() / 2.0;
            double halfHeight = target.getBbHeight() / 2.0;

            serverLevel.sendParticles(
                    UOParticleTypes.RIME.get(),
                    centerX, centerY, centerZ,
                    20,
                    halfWidth, halfHeight, halfWidth,
                    0.02
            );
        }
    }
}
