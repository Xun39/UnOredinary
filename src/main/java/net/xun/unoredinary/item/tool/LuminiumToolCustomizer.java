package net.xun.unoredinary.item.tool;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.xun.armory.api.item.tools.ToolCustomizer;
import net.xun.armory.api.item.tools.ToolType;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.server.UOServerConfig;

import java.util.List;

public class LuminiumToolCustomizer extends AbstractEffectToolCustomizer {

    @Override
    protected void handleHitEffect(ToolType toolType, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player) || !UOServerConfig.toolEffectConfig.luminiumConfig.enable.get())
            return;

        if (UOServerConfig.toolEffectConfig.luminiumConfig.enableGlowingOnHit.get()) {
            addGlowingEffect(target);
        }
    }

    private static void addGlowingEffect(LivingEntity target) {
        MobEffectUtils.applyEffectsWithStrategy(
                target,
                List.of(
                        MobEffectInstanceBuilder.of(MobEffects.GLOWING)
                                .withDuration(100)
                                .ambient()
                                .build()
                ),
                EffectStackingStrategy.FORCE_OVERRIDE
        );
    }
}
