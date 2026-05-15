package net.xun.unoredinary.config.server;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.xun.unoredinary.config.server.armor.ArmorEffectConfigServer;
import net.xun.unoredinary.config.server.tool.ToolEffectConfigServer;

public class UOServerConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ArmorEffectConfigServer armorEffectConfig;
    public static final ToolEffectConfigServer toolEffectConfig;

    static {
        armorEffectConfig = new ArmorEffectConfigServer(BUILDER);
        toolEffectConfig = new ToolEffectConfigServer(BUILDER);

        SPEC = BUILDER.build();
    }
}
