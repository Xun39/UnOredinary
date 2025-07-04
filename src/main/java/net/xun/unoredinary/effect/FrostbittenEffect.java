package net.xun.unoredinary.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.xun.lib.common.api.util.CommonUtils;

public class FrostbittenEffect extends MobEffect {
    public static final ResourceLocation MOVEMENT_SPEED_MODIFIER = CommonUtils.modLoc("frosted_slowdown");
    public static final double FROST_MULTIPLIER = -0.15D;

    public FrostbittenEffect() {
        super(MobEffectCategory.HARMFUL, 0x33b2e7);
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                FrostbittenEffect.MOVEMENT_SPEED_MODIFIER,
                FROST_MULTIPLIER,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        livingEntity.setIsInPowderSnow(true);
        if (amplifier > 0 && livingEntity.canFreeze()) {
            livingEntity.setTicksFrozen(Math.min(livingEntity.getTicksRequiredToFreeze(), livingEntity.getTicksFrozen() + amplifier));
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}