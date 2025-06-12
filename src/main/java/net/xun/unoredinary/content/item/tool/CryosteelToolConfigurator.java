package net.xun.unoredinary.content.item.tool;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.xun.lib.common.api.item.tools.ToolConfigurator;
import net.xun.lib.common.api.item.tools.ToolType;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;

import java.util.List;

public class CryosteelToolConfigurator implements ToolConfigurator {
    @Override
    public Item createTool(ToolType type, Tier tier, Item.Properties properties) {
        switch (type) {
            case SWORD -> {
                return new SwordItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        handleHitEffect(target);
                        return super.hurtEnemy(stack, target, attacker);
                    }
                };
            }

            case AXE -> {
                return new AxeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        handleHitEffect(target);
                        return super.hurtEnemy(stack, target, attacker);
                    }
                };
            }

            case PICKAXE -> {
                return new PickaxeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        handleHitEffect(target);
                        return super.hurtEnemy(stack, target, attacker);
                    }
                };
            }

            case HOE -> {
                return new HoeItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        handleHitEffect(target);
                        return super.hurtEnemy(stack, target, attacker);
                    }
                };
            }

            case SHOVEL -> {
                return new ShovelItem(tier, properties) {
                    @Override
                    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                        handleHitEffect(target);
                        return super.hurtEnemy(stack, target, attacker);
                    }
                };
            }

            default -> {
                return type.create(tier, properties);
            }
        }
    }

    private static void handleHitEffect(LivingEntity target) {
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
    }
}
