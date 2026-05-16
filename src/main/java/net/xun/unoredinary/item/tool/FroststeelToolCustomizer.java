package net.xun.unoredinary.item.tool;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.xun.armory.api.item.tools.ToolType;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.server.UOServerConfig;
import net.xun.unoredinary.registry.UOParticleTypes;

import java.util.List;

public class FroststeelToolCustomizer extends AbstractEffectToolCustomizer {
    private static final int SLOW_DURATION = 40;
    private static final int SLOW_AMPLIFIER = 1;

    @Override
    protected void handleHitEffect(ToolType toolType, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player) || !UOServerConfig.toolEffectConfig.froststeelConfig.enable.get())
            return;

        if (UOServerConfig.toolEffectConfig.froststeelConfig.enableNormalEffect.get()) {
            applyHitEffects(target);
        }
    }

    private static void applyHitEffects(LivingEntity target) {
        List<MobEffectInstance> effects = List.of(
                buildEffectInstance()
        );

        MobEffectUtils.applyEffectsWithStrategy(target, effects, EffectStackingStrategy.FORCE_OVERRIDE);

        if (UOServerConfig.toolEffectConfig.froststeelConfig.doHitParticlesSpawn.get()) {
            spawnRimeParticles(target);
        }
    }

    private static void spawnRimeParticles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return;

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

    private static MobEffectInstance buildEffectInstance() {
        return MobEffectInstanceBuilder.of(MobEffects.MOVEMENT_SLOWDOWN)
                .withDuration(FroststeelToolCustomizer.SLOW_DURATION)
                .withAmplifier(FroststeelToolCustomizer.SLOW_AMPLIFIER)
                .build();
    }
}
