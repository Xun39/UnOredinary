package net.xun.unoredinary.item.tool;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.xun.lib.common.api.item.tools.ToolConfigurator;
import net.xun.lib.common.api.item.tools.ToolType;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.client.UOClientConfig;
import net.xun.unoredinary.registry.UOMobEffects;
import net.xun.unoredinary.registry.UOParticleTypes;

import java.util.List;

public class GlacialiteToolConfigurator implements ToolConfigurator {
    private static final int FROSTED_DURATION = 400;
    private static final int WEAKNESS_DURATION_NOVA = 60;
    private static final int WEAKNESS_AMPLIFIER_NOVA = 2;
    private static final int SLOW_DURATION = 60;
    private static final int SLOW_AMPLIFIER = 2;
    private static final int WEAKNESS_DURATION_SINGLE = 40;
    private static final int WEAKNESS_AMPLIFIER_SINGLE = 1;

    @Override
    public Item createTool(ToolType type, Tier tier, Item.Properties properties) {
        switch (type) {
            case SWORD -> {
                return new SwordItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean flag = super.hurtEnemy(stack, target, attacker);
                        if (flag && !target.level().isClientSide) {
                            handleHitEffect(target, attacker, true);
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
                            handleHitEffect(target, attacker, true);
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
                            handleHitEffect(target, attacker, false);
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
                            handleHitEffect(target, attacker, false);
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
                            handleHitEffect(target, attacker, false);
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

    private static void handleHitEffect(LivingEntity target, LivingEntity attacker, boolean applyFrostNova) {
        if (!(attacker instanceof Player)) return;

        if (applyFrostNova) {
            applyFrostNovaEffect(target);
        } else {
            applySingleTargetEffects(target);
        }
    }

    private static void applyFrostNovaEffect(LivingEntity target) {
        Level level = target.level();
        List<Monster> monsters = level.getEntitiesOfClass(
                Monster.class,
                BlockPosUtils.createAABBFromCenter(target.blockPosition(), 4, 2, 4)
        );

        for (Monster monster : monsters) {
            applyFrostEffects(monster);
            spawnFrostParticles(monster);
        }
    }

    private static void applyFrostEffects(LivingEntity target) {
        List<MobEffectInstance> effects = List.of(
                buildEffectInstance(UOMobEffects.FROSTED_EFFECT, FROSTED_DURATION, 0),
                buildEffectInstance(MobEffects.WEAKNESS, WEAKNESS_DURATION_NOVA, WEAKNESS_AMPLIFIER_NOVA)
        );

        MobEffectUtils.applyEffectsWithStrategy(target, effects, EffectStackingStrategy.FORCE_OVERRIDE);
    }

    private static void spawnFrostParticles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return;

        if (!UOClientConfig.toolEffectConfig.glacialiteConfig.doHitParticlesSpawn.get())
            return;

        double centerX = target.getX();
        double centerY = target.getY() + target.getBbHeight() / 2.0;
        double centerZ = target.getZ();

        double halfWidth = target.getBbWidth() / 2.0;
        double halfHeight = target.getBbHeight() / 2.0;

        serverLevel.sendParticles(
                UOParticleTypes.SUBZERO_FROST.get(),
                centerX, centerY, centerZ,
                24,
                halfWidth, halfHeight, halfWidth,
                0.03
        );
    }

    private static void applySingleTargetEffects(LivingEntity target) {
        List<MobEffectInstance> effects = List.of(
                buildEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, SLOW_DURATION, SLOW_AMPLIFIER),
                buildEffectInstance(MobEffects.WEAKNESS, WEAKNESS_DURATION_SINGLE, WEAKNESS_AMPLIFIER_SINGLE)
        );

        MobEffectUtils.applyEffectsWithStrategy(target, effects, EffectStackingStrategy.UPGRADE_EXISTING);
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
