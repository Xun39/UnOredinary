package net.xun.unoredinary.config.server.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FroststeelArmorConfigServer {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableFrostWalker;
    public final ModConfigSpec.BooleanValue enableHotFloorDamage;

    public final ModConfigSpec.IntValue frostWalkerRadius;

    public FroststeelArmorConfigServer(final ModConfigSpec.Builder builder) {
        builder.push("froststeel");

        enable = builder
                .comment("Disabling this setting will turn off all armor effects of Froststeel Armors")
                .define("enable_armor", true);

        enableFrostWalker = builder
                .comment("Disabling this setting will turn off the frost walker effect while wearing Froststeel Boots")
                .define("enable_frost_walker", true);

        enableHotFloorDamage = builder
                .comment("Disabling this setting will let you immune to hot floor damage (e.g. Stepping on Campfires, Magma Blocks, etc.) when wearing Froststeel Boots")
                .define("enable_hot_floor_damage", false);

        frostWalkerRadius = builder
                .comment("This setting corresponds to the radius of the frost walker effect you get while wearing Froststeel Boots")
                .defineInRange("frost_walker_rad", 2, 1, 16);

        builder.pop();
    }
}
