package xun.unoredinary.content.item.tool;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import xun.unoredinary.content.item.tool.pickaxe.FrosteelPickItem;
import xun.unoredinary.util.BiomeUtils;

public class FrosteelToolSet extends ToolSet {

    public FrosteelToolSet(String name, Tier toolTier, Item.Properties toolProperties) {
        super(name, toolTier, toolProperties);
    }

    @Override
    protected SwordItem createSword(Tier toolTier, Item.Properties properties) {
        return new SwordItem(toolTier, properties
                .attributes(SwordItem.createAttributes(toolTier, getAttackDamage().getFirst(), getAttackSpeed().getFirst()))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(target, attacker, 4.0F);
                return result;
            }
        };
    }

    @Override
    protected PickaxeItem createPickaxe(Tier toolTier, Item.Properties properties) {
        return new FrosteelPickItem(toolTier, properties
                .attributes(PickaxeItem.createAttributes(toolTier, getAttackDamage().get(1), getAttackSpeed().get(1)))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(target, attacker, 1.0F);
                return result;
            }
        };
    }

    @Override
    protected AxeItem createAxe(Tier toolTier, Item.Properties properties) {
        return new AxeItem(toolTier, properties
                .attributes(AxeItem.createAttributes(toolTier, getAttackDamage().get(2), getAttackSpeed().get(2)))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(target, attacker, 5.0F);
                return result;
            }
        };
    }

    @Override
    protected HoeItem createHoe(Tier toolTier, Item.Properties properties) {
        return new HoeItem(toolTier, properties
                .attributes(HoeItem.createAttributes(toolTier, getAttackDamage().get(3), getAttackSpeed().get(3)))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(target, attacker, 0.5F);
                return result;
            }
        };
    }

    @Override
    protected ShovelItem createShovel(Tier toolTier, Item.Properties properties) {
        return new ShovelItem(toolTier, properties
                .attributes(ShovelItem.createAttributes(toolTier, getAttackDamage().getLast(), getAttackSpeed().getLast()))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(target, attacker, 1.0F);
                return result;
            }
        };
    }

    private static void handleHitEffect(LivingEntity target, LivingEntity attacker, float extraDamage) {
        if (BiomeUtils.isInColdBiome(attacker.level(), attacker.getOnPos())) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1));
            target.hurt(target.damageSources().freeze(), extraDamage);
        } else {
            target.hurt(target.damageSources().freeze(), extraDamage / 2);
        }
    }
}
