package net.xun.unoredinary.config.client.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ToolEffectConfigClient {
    public final FroststeelToolConfigClient froststeelConfig;
    public final GlacialiteToolConfigClient glacialiteConfig;

    public ToolEffectConfigClient(final ModConfigSpec.Builder builder) {
        builder.push("tool_effects");

        froststeelConfig = new FroststeelToolConfigClient(builder);
        glacialiteConfig = new GlacialiteToolConfigClient(builder);

        builder.pop();
    }
}
