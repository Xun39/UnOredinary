package net.xun.unoredinary.config.common.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteArmorConfigCommon {
    public final ModConfigSpec.BooleanValue enableFrostWalker;

    public GlacialiteArmorConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("armors_glacialite");

        enableFrostWalker = builder
                .comment("Set this off will disable the frost walker effect for Glacialite Armors")
                .define("enable_frost_walker_glacialite", true);

        builder.pop();
    }
}
