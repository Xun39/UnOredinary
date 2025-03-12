package xun.unoredinary.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class PlayerUtils {

    private PlayerUtils() {}

    public static void addEffectToPlayerIfEffectEndWithin(int endWithinDuration, Player player, List<MobEffectInstance> effects) {

        for (MobEffectInstance effect : effects) {
            MobEffectInstance currentEffect = player.getEffect(effect.getEffect());

            if (currentEffect == null || currentEffect.endsWithin(endWithinDuration)) {
                player.addEffect(new MobEffectInstance(
                        effect.getEffect(),
                        effect.getDuration(),
                        effect.getAmplifier(),
                        effect.isAmbient(),
                        effect.isVisible()
                ));
            }
        }
    }

    public static void addEffectToPlayer(Player player, List<MobEffectInstance> effects) {
        boolean hasPlayerEffect = effects.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));

        if(!hasPlayerEffect) {
            for (MobEffectInstance effect : effects) {
                player.addEffect(new MobEffectInstance(effect.getEffect(),
                        effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }
}
