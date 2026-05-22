package net.xun.unoredinary.config.server.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ArmorEffectConfigServer {
    public final ModConfigSpec.BooleanValue onlyPlayer;

    public final FroststeelArmorConfigServer froststeelConfig;
    public final GlacialiteArmorConfigServer glacialiteConfig;
    public final LuminiumArmorConfigServer luminiumConfig;

    public ArmorEffectConfigServer(final ModConfigSpec.Builder builder) {
        builder.push("armor_effects");

        onlyPlayer = builder
                .comment("Disabling this setting will allow other entities to have their armor effect applied")
                .define("only_player", true);

        froststeelConfig = new FroststeelArmorConfigServer(builder);
        glacialiteConfig = new GlacialiteArmorConfigServer(builder);
        luminiumConfig = new LuminiumArmorConfigServer(builder);

        builder.pop();
    }
}
