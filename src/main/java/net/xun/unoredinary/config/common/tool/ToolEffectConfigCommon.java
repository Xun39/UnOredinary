package net.xun.unoredinary.config.common.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ToolEffectConfigCommon {
    public final FroststeelToolConfigCommon froststeelConfig;
    public final GlacialiteToolConfigCommon glacialiteConfig;
    public final LuminiumToolConfigCommon luminiumConfig;

    public ToolEffectConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("tool_effects");

        froststeelConfig = new FroststeelToolConfigCommon(builder);
        glacialiteConfig = new GlacialiteToolConfigCommon(builder);
        luminiumConfig = new LuminiumToolConfigCommon(builder);

        builder.pop();
    }
}
