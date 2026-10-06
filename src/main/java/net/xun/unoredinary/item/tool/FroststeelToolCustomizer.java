package net.xun.unoredinary.item.tool;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.xun.lib.common.api.item.tools.AbstractHitEffectCustomizer;
import net.xun.lib.common.api.item.tools.ToolPieceType;
import net.xun.lib.common.api.util.MobEffectUtil;
import net.xun.lib.common.api.world.effect.EffectStackingStrategies;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.UOConfigServer;
import net.xun.unoredinary.registry.UOParticleTypes;

import java.util.List;

public class FroststeelToolCustomizer extends AbstractHitEffectCustomizer {
    private static final int SLOW_DURATION = 40;
    private static final int SLOW_AMPLIFIER = 1;

    @Override
    protected void onHit(ToolPieceType piece, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player) || !UOConfigServer.toolEffect.froststeel.enable)
            return;

        if (UOConfigServer.toolEffect.froststeel.enableNormalEffect) {
            applyHitEffects(target);
        }
    }

    private static void applyHitEffects(LivingEntity target) {
        List<MobEffectInstance> effects = List.of(
                buildEffectInstance()
        );

        for (MobEffectInstance effect : effects) {
            MobEffectUtil.applyEffectWithStrategy(target, effect, EffectStackingStrategies.FORCE_OVERRIDE);
        }

        if (UOConfigServer.toolEffect.froststeel.doHitParticlesSpawn) {
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
                .duration(FroststeelToolCustomizer.SLOW_DURATION)
                .amplifier(FroststeelToolCustomizer.SLOW_AMPLIFIER)
                .build();
    }
}
