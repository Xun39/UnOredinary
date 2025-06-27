package net.xun.unoredinary.config.common.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class LuminiumToolConfigCommon {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableGlowingOnHit;

    public LuminiumToolConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("luminium");

        enable = builder
                .comment("Disabling this setting will turn off all tool effects of Luminium Tools")
                .define("enable_tool", true);

        enableGlowingOnHit = builder
                .comment("Disabling this setting will turn off the glowing effect when you hit a mob with any Luminium Tools")
                .define("enable_glowing_on_hit", true);

        builder.pop();
    }
}
