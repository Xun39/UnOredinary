package net.xun.unoredinary.content.item.tool;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.xun.lib.common.api.item.tools.ToolConfigurator;
import net.xun.lib.common.api.item.tools.ToolType;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.registry.UOParticleTypes;

import java.util.List;

public class FroststeelToolConfigurator implements ToolConfigurator {
    @Override
    public Item createTool(ToolType type, Tier tier, Item.Properties properties) {
        switch (type) {
            case SWORD -> {
                return new SwordItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean result = super.hurtEnemy(stack, target, attacker);
                        if (result && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return result;
                    }
                };
            }
            case AXE -> {
                return new AxeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean result = super.hurtEnemy(stack, target, attacker);
                        if (result && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return result;
                    }
                };
            }
            case PICKAXE -> {
                return new PickaxeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean result = super.hurtEnemy(stack, target, attacker);
                        if (result && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return result;
                    }
                };
            }
            case HOE -> {
                return new HoeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean result = super.hurtEnemy(stack, target, attacker);
                        if (result && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return result;
                    }
                };
            }
            case SHOVEL -> {
                return new ShovelItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        boolean result = super.hurtEnemy(stack, target, attacker);
                        if (result && !target.level().isClientSide) {
                            handleHitEffect(target, attacker);
                        }
                        return result;
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

        Level level = target.level();

        MobEffectUtils.applyEffectsWithStrategy(
                target,
                List.of(
                        MobEffectInstanceBuilder.of(MobEffects.MOVEMENT_SLOWDOWN)
                                .withDuration(60)
                                .withAmplifier(2)
                                .ambient()
                                .build(),
                        MobEffectInstanceBuilder.of(MobEffects.WEAKNESS)
                                .withDuration(40)
                                .withAmplifier(1)
                                .ambient()
                                .build()
                ),
                EffectStackingStrategy.UPGRADE_EXISTING
        );

        if (level instanceof ServerLevel serverLevel) {
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
    }
}
