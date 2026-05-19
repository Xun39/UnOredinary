package net.xun.unoredinary.effect;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.registry.UOMobEffects;

@EventBusSubscriber(modid = UnOredinary.MOD_ID)
public class WarmthEffect extends MobEffect {

    public WarmthEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xe7710f);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide) return true;

        if (entity.hasEffect(UOMobEffects.FROSTED_EFFECT))
            entity.removeEffect(UOMobEffects.FROSTED_EFFECT);

        if (entity.getTicksFrozen() > 0) {
            entity.setTicksFrozen(0);
            entity.setIsInPowderSnow(false);
            return true;
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    // Immune to cold damage
    @SubscribeEvent
    public static void onInvulnerabilityCheck(EntityInvulnerabilityCheckEvent e) {
        Entity entity = e.getEntity();

        if (!(entity instanceof LivingEntity living))
            return;

        if (living.hasEffect(UOMobEffects.WARMTH_EFFECT)) {
            e.setInvulnerable(e.getSource().is(DamageTypeTags.IS_FREEZING));
        }
    }
}
