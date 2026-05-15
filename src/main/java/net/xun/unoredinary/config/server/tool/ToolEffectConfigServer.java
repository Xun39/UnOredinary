package net.xun.unoredinary.config.server.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ToolEffectConfigServer {
    public final FroststeelToolConfigServer froststeelConfig;
    public final GlacialiteToolConfigServer glacialiteConfig;
    public final LuminiumToolConfigServer luminiumConfig;

    public ToolEffectConfigServer(final ModConfigSpec.Builder builder) {
        builder.push("tool_effects");

        froststeelConfig = new FroststeelToolConfigServer(builder);
        glacialiteConfig = new GlacialiteToolConfigServer(builder);
        luminiumConfig = new LuminiumToolConfigServer(builder);

        builder.pop();
    }
}
