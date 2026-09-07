package net.xun.unoredinary.item.tool;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.xun.lib.common.api.item.tools.AbstractHitEffectCustomizer;
import net.xun.lib.common.api.item.tools.ToolPieceType;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategies;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.server.UOServerConfig;

public class LuminiumToolCustomizer extends AbstractHitEffectCustomizer {

    @Override
    protected void onHit(ToolPieceType piece, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player) || !UOServerConfig.toolEffectConfig.luminiumConfig.enable.get())
            return;

        if (UOServerConfig.toolEffectConfig.luminiumConfig.enableGlowingOnHit.get()) {
            addGlowingEffect(target);
        }
    }

    private static void addGlowingEffect(LivingEntity target) {
        MobEffectUtils.applyEffectWithStrategy(
                target,
                MobEffectInstanceBuilder.of(MobEffects.GLOWING)
                        .duration(100)
                        .build(),
                EffectStackingStrategies.FORCE_OVERRIDE
        );
    }
}
