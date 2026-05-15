package net.xun.unoredinary.config.server.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class LuminiumToolConfigServer {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableGlowingOnHit;

    public LuminiumToolConfigServer(final ModConfigSpec.Builder builder) {
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
