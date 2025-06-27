package net.xun.unoredinary.config.common.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteToolConfigCommon {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableNormalEffect;
    public final ModConfigSpec.BooleanValue enableFrostNova;

    public GlacialiteToolConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("glacialite");

        enable = builder
                .comment("Disabling this setting will turn off all tool effects of Glacialite Tools")
                .define("enable_tool", true);

        enableNormalEffect = builder
                .comment("Disabling this setting will turn off the normal effect of all Glacialite Tools/Weapons, this effect includes applying, after hitting an enemy, Weakness I and Slowness II")
                .define("enable_normal", true);

        enableFrostNova = builder
                .comment("Disabling this setting will turn off the frost nova effect of Glacialite Sword and Glacialite Axe, notice that they are going to have the normal Glacialite effect after")
                .define("enable_frost_nova", true);

        builder.pop();
    }
}
