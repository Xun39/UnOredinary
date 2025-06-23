package net.xun.unoredinary.config.common.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ToolEffectConfigCommon {
    public final LuminiumToolConfigCommon luminiumConfig;

    public ToolEffectConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("tool_effects");

        luminiumConfig = new LuminiumToolConfigCommon(builder);

        builder.pop();
    }
}
