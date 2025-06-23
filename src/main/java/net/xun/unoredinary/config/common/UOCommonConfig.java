package net.xun.unoredinary.config.common;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.xun.unoredinary.config.common.armor.ArmorEffectConfigCommon;
import net.xun.unoredinary.config.common.tool.ToolEffectConfigCommon;

public class UOCommonConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ArmorEffectConfigCommon armorEffectConfig;
    public static final ToolEffectConfigCommon toolEffectConfig;

    static {
        armorEffectConfig = new ArmorEffectConfigCommon(BUILDER);
        toolEffectConfig = new ToolEffectConfigCommon(BUILDER);

        SPEC = BUILDER.build();
    }
}
