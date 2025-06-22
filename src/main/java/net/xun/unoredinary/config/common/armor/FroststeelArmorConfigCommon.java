package net.xun.unoredinary.config.common.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FroststeelArmorConfigCommon {
    public final ModConfigSpec.BooleanValue enableFrostWalker;

    public FroststeelArmorConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("armors_froststeel");

        enableFrostWalker = builder
                .comment("Set this off will disable the frost walker effect for Froststeel Armors")
                .define("enable_frost_walker_froststeel", true);

        builder.pop();
    }
}
