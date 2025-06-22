package net.xun.unoredinary.config.client;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.xun.unoredinary.config.client.armor.ArmorEffectConfigClient;
import net.xun.unoredinary.config.client.tool.ToolEffectConfigClient;

public class UOClientConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ToolEffectConfigClient toolEffectConfig;
    public static final ArmorEffectConfigClient armorEffectConfig;

    static {
        toolEffectConfig = new ToolEffectConfigClient(BUILDER);
        armorEffectConfig = new ArmorEffectConfigClient(BUILDER);

        SPEC = BUILDER.build();
    }
}
