package net.xun.unoredinary.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.xun.lib.common.api.util.CommonUtils;

public class FrostbittenEffect extends MobEffect {

    public FrostbittenEffect() {
        super(MobEffectCategory.HARMFUL, 0x33b2e7);
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                CommonUtils.modLoc("frostbite_slow"),
                -0.2D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide) return true; // TODO: no early return, because now it's confusing
        if (!entity.canFreeze()) return false;

        if (entity.isOnFire()) {
            entity.extinguishFire();
            return true;
        }

        int required = entity.getTicksRequiredToFreeze();
        entity.setTicksFrozen(required);

        if (amplifier > 0) {
            entity.setTicksFrozen(required + (amplifier * 40));
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
