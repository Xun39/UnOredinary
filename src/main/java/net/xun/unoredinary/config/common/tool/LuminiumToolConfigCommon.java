package net.xun.unoredinary.config.common.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class LuminiumToolConfigCommon {
    public final ModConfigSpec.BooleanValue enable;

    public LuminiumToolConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("luminium");

        enable = builder
                .comment("Disabling this setting will turn off all tool effects of Luminium Tools")
                .define("enable_tool", true);

        builder.pop();
    }
}
