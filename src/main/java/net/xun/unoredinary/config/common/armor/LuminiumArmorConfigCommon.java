package net.xun.unoredinary.config.common.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class LuminiumArmorConfigCommon {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableNightVision;

    public LuminiumArmorConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("luminium");

        enable = builder
                .comment("Disabling this setting will turn off all armor effects of Luminium Armors")
                .define("enable_armor", true);

        enableNightVision = builder
                .comment("Disabling this setting will turn off the night vision effect when wearing Luminium Helmet")
                .define("enable_night_vision", true);

        builder.pop();
    }
}
