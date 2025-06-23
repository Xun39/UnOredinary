package net.xun.unoredinary.config.common.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FroststeelArmorConfigCommon {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableFrostWalker;
    public final ModConfigSpec.BooleanValue enableHotFloorDamage;

    public FroststeelArmorConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("froststeel");

        enable = builder
                .comment("Disabling this setting will turn off all armor effects of Froststeel Armors")
                .define("enable_armor", true);

        enableFrostWalker = builder
                .comment("Disabling this setting will turn off the frost walker effect while wearing Froststeel Boots")
                .define("enable_frost_walker", true);

        enableHotFloorDamage = builder
                .comment("Disabling this setting will enable hot floor damage (e.g. Stepping on Campfires, Magma Blocks, etc.) when wearing Froststeel Boots")
                .define("enable_hot_floor_damage", false);

        builder.pop();
    }
}
