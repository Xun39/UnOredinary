package net.xun.unoredinary.item.tool;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.xun.lib.common.api.item.tools.AbstractHitEffectCustomizer;
import net.xun.lib.common.api.item.tools.ToolPieceType;
import net.xun.lib.common.api.util.MobEffectUtil;
import net.xun.lib.common.api.world.effect.EffectStackingStrategies;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.UOConfigServer;

public class LuminiumToolCustomizer extends AbstractHitEffectCustomizer {

    @Override
    protected void onHit(ToolPieceType piece, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player) || !UOConfigServer.toolEffect.luminium.enable)
            return;

        if (UOConfigServer.toolEffect.luminium.enableGlowingOnHit) {
            addGlowingEffect(target);
        }
    }

    private static void addGlowingEffect(LivingEntity target) {
        MobEffectUtil.applyEffectWithStrategy(
                target,
                MobEffectInstanceBuilder.of(MobEffects.GLOWING)
                        .duration(100)
                        .build(),
                EffectStackingStrategies.FORCE_OVERRIDE
        );
    }
}
