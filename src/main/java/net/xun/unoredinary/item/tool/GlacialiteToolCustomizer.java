package net.xun.unoredinary.item.tool;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.xun.armory.api.item.tools.ToolCustomizer;
import net.xun.armory.api.item.tools.ToolType;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.server.UOServerConfig;
import net.xun.unoredinary.registry.UOMobEffects;
import net.xun.unoredinary.registry.UOParticleTypes;
import net.xun.unoredinary.registry.UOSounds;

import java.util.List;

public class GlacialiteToolCustomizer implements ToolCustomizer {
    private static final int FROSTED_DURATION = 400;
    private static final int WEAKNESS_DURATION_NOVA = 60;
    private static final int WEAKNESS_AMPLIFIER_NOVA = 2;
    private static final int SLOW_DURATION = 60;
    private static final int SLOW_AMPLIFIER = 2;
    private static final int WEAKNESS_DURATION_SINGLE = 40;
    private static final int WEAKNESS_AMPLIFIER_SINGLE = 1;

    private static final float FROST_NOVA_RADIUS = 4.0F;

    @Override
    public Item createTool(ToolType type, Tier tier, Item.Properties properties) {
        switch (type) {
            case SWORD -> {
                return new SwordItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        return onHit(
                                super.hurtEnemy(stack, target, attacker),
                                target,
                                attacker,
                                true
                        );
                    }
                };
            }
            case AXE -> {
                return new AxeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        return onHit(
                                super.hurtEnemy(stack, target, attacker),
                                target,
                                attacker,
                                true
                        );
                    }
                };
            }
            case PICKAXE -> {
                return new PickaxeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        return onHit(
                                super.hurtEnemy(stack, target, attacker),
                                target,
                                attacker,
                                false
                        );
                    }
                };
            }
            case HOE -> {
                return new HoeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        return onHit(
                                super.hurtEnemy(stack, target, attacker),
                                target,
                                attacker,
                                false
                        );
                    }
                };
            }
            case SHOVEL -> {
                return new ShovelItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        return onHit(
                                super.hurtEnemy(stack, target, attacker),
                                target,
                                attacker,
                                false
                        );
                    }
                };
            }
            default -> throw new MatchException(null, null);
        }
    }

    private static boolean onHit(boolean flag, LivingEntity target, LivingEntity attacker, boolean frostNova) {
        if (flag && !target.level().isClientSide) {
            handleHitEffect(target, attacker, frostNova);
        }

        return flag;
    }

    private static void handleHitEffect(LivingEntity target, LivingEntity attacker, boolean canNova) {
        if (!(attacker instanceof Player) || !UOServerConfig.toolEffectConfig.glacialiteConfig.enable.get()) {
            return;
        }

        boolean frostNovaEnabled = canNova && UOServerConfig.toolEffectConfig.glacialiteConfig.enableFrostNova.get();

        if (frostNovaEnabled) {
            applyFrostNovaEffect(target);
            return;
        }

        applySingleTargetEffects(target);
    }

    private static void applyFrostNovaEffect(LivingEntity target) {
        Level level = target.level();
        AABB area = BlockPosUtils.createAABBFromCenter(target.blockPosition(), FROST_NOVA_RADIUS);

        var config = UOServerConfig.toolEffectConfig.glacialiteConfig;

        if (config.doHitParticlesSpawn.get()) {
            spawnFrostParticles(target);
        }

        if (config.enableFrostNovaSound.get()) {
            level.playSound(
                    null,
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    UOSounds.FROST_NOVA.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }

        boolean affectPassive = config.frostNovaToPassive.get();

        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                area,
                entity -> entity.isAlive() && (affectPassive || entity instanceof Enemy)
        );

        for (LivingEntity entity : entities) {
            applyFrostEffects(entity);
        }
    }

    private static void applyFrostEffects(LivingEntity target) {
        List<MobEffectInstance> effects = List.of(
                buildEffectInstance(UOMobEffects.FROSTED_EFFECT, FROSTED_DURATION, 0),
                buildEffectInstance(MobEffects.WEAKNESS, WEAKNESS_DURATION_NOVA, WEAKNESS_AMPLIFIER_NOVA)
        );

        MobEffectUtils.applyEffectsWithStrategy(target, effects, EffectStackingStrategy.UPGRADE_EXISTING);
    }

    private static void spawnFrostParticles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return;

        double x = target.getX();
        double y = target.getBoundingBox().minY;
        double z = target.getZ();

        serverLevel.sendParticles(
                UOParticleTypes.FROST_NOVA.get(),
                x, y, z,
                1,
                0, 0, 0,
                FROST_NOVA_RADIUS
        );
    }

    private static void applySingleTargetEffects(LivingEntity target) {
        if (!UOServerConfig.toolEffectConfig.glacialiteConfig.enableNormalEffect.get())
            return;

        List<MobEffectInstance> effects = List.of(
                buildEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, SLOW_DURATION, SLOW_AMPLIFIER),
                buildEffectInstance(MobEffects.WEAKNESS, WEAKNESS_DURATION_SINGLE, WEAKNESS_AMPLIFIER_SINGLE)
        );

        MobEffectUtils.applyEffectsWithStrategy(target, effects, EffectStackingStrategy.UPGRADE_EXISTING);
    }

    private static MobEffectInstance buildEffectInstance(Holder<MobEffect> effect, int duration, int amplifier) {
        return MobEffectInstanceBuilder.of(effect)
                .withDuration(duration)
                .withAmplifier(amplifier)
                .build();
    }
}
