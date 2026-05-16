package net.xun.unoredinary.item.tool;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.xun.armory.api.item.tools.ToolCustomizer;
import net.xun.armory.api.item.tools.ToolType;

public abstract class AbstractEffectToolCustomizer implements ToolCustomizer {
    @Override
    public Item createTool(ToolType type, Tier tier, Item.Properties properties) {
        return switch (type) {
            case SWORD -> createSword(tier, properties);
            case AXE -> createAxe(tier, properties);
            case PICKAXE -> createPickaxe(tier, properties);
            case HOE -> createHoe(tier, properties);
            case SHOVEL -> createShovel(tier, properties);
        };
    }

    protected abstract void handleHitEffect(ToolType toolType, LivingEntity target, LivingEntity attacker);

    protected boolean onHit(ToolType toolType, boolean flag, LivingEntity target, LivingEntity attacker) {
        if (flag && !target.level().isClientSide) {
            handleHitEffect(toolType, target, attacker);
        }

        return flag;
    }

    protected Item createSword(Tier tier, Item.Properties properties) {
        return new SwordItem(tier, properties) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                return onHit(
                        ToolType.SWORD,
                        super.hurtEnemy(stack, target, attacker),
                        target,
                        attacker
                );
            }
        };
    }

    protected Item createAxe(Tier tier, Item.Properties properties) {
        return new AxeItem(tier, properties) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                return onHit(
                        ToolType.AXE,
                        super.hurtEnemy(stack, target, attacker),
                        target,
                        attacker
                );
            }
        };
    }

    protected Item createPickaxe(Tier tier, Item.Properties properties) {
        return new PickaxeItem(tier, properties) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                return onHit(
                        ToolType.PICKAXE,
                        super.hurtEnemy(stack, target, attacker),
                        target,
                        attacker
                );
            }
        };
    }

    protected Item createHoe(Tier tier, Item.Properties properties) {
        return new HoeItem(tier, properties) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                return onHit(
                        ToolType.HOE,
                        super.hurtEnemy(stack, target, attacker),
                        target,
                        attacker
                );
            }
        };
    }

    protected Item createShovel(Tier tier, Item.Properties properties) {
        return new ShovelItem(tier, properties) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                return onHit(
                        ToolType.SHOVEL,
                        super.hurtEnemy(stack, target, attacker),
                        target,
                        attacker
                );
            }
        };
    }
}
