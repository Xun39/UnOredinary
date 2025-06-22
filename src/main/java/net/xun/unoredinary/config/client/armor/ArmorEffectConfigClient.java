package net.xun.unoredinary.config.client.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ArmorEffectConfigClient {
    public final GlacialiteArmorConfigClient glacialiteConfig;

    public ArmorEffectConfigClient(final ModConfigSpec.Builder builder) {
        builder.push("armor_effects");

        glacialiteConfig = new GlacialiteArmorConfigClient(builder);

        builder.pop();
    }
}
