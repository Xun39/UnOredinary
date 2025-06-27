package net.xun.unoredinary.item.tool;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.xun.lib.common.api.item.tools.ToolConfigurator;
import net.xun.lib.common.api.item.tools.ToolType;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.common.UOCommonConfig;

import java.util.List;

public class LuminiumToolConfigurator implements ToolConfigurator {
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

        if (!UOCommonConfig.toolEffectConfig.luminiumConfig.enable.get())
            return;

        if (UOCommonConfig.toolEffectConfig.luminiumConfig.enableGlowingOnHit.get()) {
            addGlowingEffect(target);
        }
    }

    private static void addGlowingEffect(LivingEntity target) {
        MobEffectUtils.applyEffectsWithStrategy(
                target,
                List.of(
                        MobEffectInstanceBuilder.of(MobEffects.GLOWING)
                                .withDuration(100)
                                .ambient()
                                .build()
                ),
                EffectStackingStrategy.FORCE_OVERRIDE
        );
    }
}
