package net.xun.unoredinary.config.common.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteArmorConfigCommon {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableFrostWalker;
    public final ModConfigSpec.BooleanValue enableSlownessImmunity;
    public final ModConfigSpec.BooleanValue enableHotFloorDamage;
    public final ModConfigSpec.BooleanValue enableThornsEffect;
    public final ModConfigSpec.BooleanValue canWalkOnPowderSnow;

    public GlacialiteArmorConfigCommon(final ModConfigSpec.Builder builder) {
        builder.push("glacialite");

        enable = builder
                .comment("Disabling this setting will turn off all armor effects of Glacialite Armors")
                .define("enable_armor", true);

        enableFrostWalker = builder
                .comment("Disabling this setting will turn off the frost walker effect while wearing Glacialite Boots")
                .define("enable_frost_walker", true);

        enableSlownessImmunity = builder
                .comment("Disabling this setting will turn off the slowness immunity while wearing full set of Glacialite Armor")
                .define("enable_slowness_immunity", true);

        enableHotFloorDamage = builder
                .comment("Disabling this setting will let you immune to hot floor damage (e.g. Stepping on Campfires, Magma Blocks, etc.) when wearing Glacialite Boots")
                .define("enable_hot_floor_damage", false);

        enableThornsEffect = builder
                .comment("Disabling this setting will turn off the damage you take from enemies attacking you while wearing a full set of Glacialite Armor")
                .define("enable_thorns", true);

        canWalkOnPowderSnow = builder
                .comment("Disabling this setting will make you unable to walk on powder snow while wearing Glacialite Boots")
                .define("can_walk_on_powder_snow", true);

        builder.pop();
    }
}
