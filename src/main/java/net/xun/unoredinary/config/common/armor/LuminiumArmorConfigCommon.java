package net.xun.unoredinary.config.common.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class LuminiumArmorConfigCommon {
    public final ModConfigSpec.BooleanValue enable;

    public LuminiumArmorConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("luminium");

        enable = builder
                .comment("Disabling this setting will turn off all armor effects of Luminium Armors")
                .define("enable_armor", true);

        builder.pop();
    }
}
