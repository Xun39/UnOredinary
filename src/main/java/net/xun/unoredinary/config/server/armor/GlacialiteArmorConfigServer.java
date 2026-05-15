package net.xun.unoredinary.config.server.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteArmorConfigServer {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableFrostWalker;
    public final ModConfigSpec.BooleanValue enableSlownessImmunity;
    public final ModConfigSpec.BooleanValue enableHotFloorDamage;
    public final ModConfigSpec.BooleanValue enableThornsEffect;
    public final ModConfigSpec.BooleanValue canWalkOnPowderSnow;

    public final ModConfigSpec.IntValue frostWalkerRadius;

    public final ModConfigSpec.BooleanValue doDamageParticlesSpawn;

    public GlacialiteArmorConfigServer(final ModConfigSpec.Builder builder) {
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

        frostWalkerRadius = builder
                .comment("This setting corresponds to the radius of the frost walker effect you get while wearing Glacialite Boots")
                .defineInRange("frost_walker_rad", 4, 1, 16);

        doDamageParticlesSpawn = builder
                .comment("Disabling this setting will turn off the particles that spawn when enemies damage you (if you wear Glacialite Armor)")
                .define("hurt_particles", true);

        builder.pop();
    }
}
