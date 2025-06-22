package net.xun.unoredinary.config.common;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.xun.unoredinary.config.common.armor.ArmorEffectConfigCommon;

public class UOCommonConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ArmorEffectConfigCommon armorEffectConfig;

    static {
        armorEffectConfig = new ArmorEffectConfigCommon(BUILDER);

        SPEC = BUILDER.build();
    }
}
