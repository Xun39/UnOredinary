package net.xun.unoredinary.item.tool;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.xun.lib.common.api.item.tools.ToolConfigurator;
import net.xun.lib.common.api.item.tools.ToolType;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.client.UOClientConfig;
import net.xun.unoredinary.registry.UOParticleTypes;

import java.util.List;

public class FroststeelToolConfigurator implements ToolConfigurator {
    private static final int SLOW_DURATION = 40;
    private static final int SLOW_AMPLIFIER = 1;

    @Override
    public Item createTool(ToolType type, Tier tier, Item.Properties properties) {
        switch (type) {
            case SWORD -> {
                return new SwordItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean flag = super.hurtEnemy(stack, target, attacker);
                        if (flag && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return flag;
                    }
                };
            }
            case AXE -> {
                return new AxeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean flag = super.hurtEnemy(stack, target, attacker);
                        if (flag && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return flag;
                    }
                };
            }
            case PICKAXE -> {
                return new PickaxeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean flag = super.hurtEnemy(stack, target, attacker);
                        if (flag && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return flag;
                    }
                };
            }
            case HOE -> {
                return new HoeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean flag = super.hurtEnemy(stack, target, attacker);
                        if (flag && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return flag;
                    }
                };
            }
            case SHOVEL -> {
                return new ShovelItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean flag = super.hurtEnemy(stack, target, attacker);
                        if (flag && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return flag;
                    }
                };
            }
            default -> {
                return type.create(tier, properties);
            }
        }
    }

    private static void handleHitEffect(LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player))
            return;

        applyHurtEffects(target);
    }

    private static void applyHurtEffects(LivingEntity target) {
        List<MobEffectInstance> effects = List.of(
                buildEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, SLOW_DURATION, SLOW_AMPLIFIER)
        );

        MobEffectUtils.applyEffectsWithStrategy(target, effects, EffectStackingStrategy.FORCE_OVERRIDE);
        spawnRimeParticles(target);
    }

    private static void spawnRimeParticles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return;

        if (!UOClientConfig.toolEffectConfig.froststeelConfig.doHitParticlesSpawn.get())
            return;

        double centerX = target.getX();
        double centerY = target.getY() + target.getBbHeight() / 2.0;
        double centerZ = target.getZ();

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

    private static MobEffectInstance buildEffectInstance(
            Holder<MobEffect> effect,
            int duration,
            int amplifier
    ) {
        return MobEffectInstanceBuilder.of(effect)
                .withDuration(duration)
                .withAmplifier(amplifier)
                .build();
    }
}
