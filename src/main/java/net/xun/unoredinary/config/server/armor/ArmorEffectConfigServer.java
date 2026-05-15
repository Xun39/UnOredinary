package net.xun.unoredinary.config.server.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ArmorEffectConfigServer {
    public final FroststeelArmorConfigServer froststeelConfig;
    public final GlacialiteArmorConfigServer glacialiteConfig;
    public final LuminiumArmorConfigServer luminiumConfig;

    public ArmorEffectConfigServer(final ModConfigSpec.Builder builder) {
        builder.push("armor_effects");

        froststeelConfig = new FroststeelArmorConfigServer(builder);
        glacialiteConfig = new GlacialiteArmorConfigServer(builder);
        luminiumConfig = new LuminiumArmorConfigServer(builder);

        builder.pop();
    }
}
