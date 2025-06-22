package net.xun.unoredinary.config.common.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ArmorEffectConfigCommon {
    public final FroststeelArmorConfigCommon froststeelConfig;
    public final GlacialiteArmorConfigCommon glacialiteConfig;

    public ArmorEffectConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("armor_effects");

        froststeelConfig = new FroststeelArmorConfigCommon(builder);
        glacialiteConfig = new GlacialiteArmorConfigCommon(builder);

        builder.pop();
    }
}
