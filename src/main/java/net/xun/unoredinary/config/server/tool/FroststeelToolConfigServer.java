package net.xun.unoredinary.config.server.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FroststeelToolConfigServer {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableNormalEffect;

    public final ModConfigSpec.BooleanValue doHitParticlesSpawn;

    public FroststeelToolConfigServer(final ModConfigSpec.Builder builder) {
        builder.push("froststeel");

        enable = builder
                .comment("Disabling this setting will turn off all tool effects of Froststeel Tools")
                .define("enable_tool", true);

        enableNormalEffect = builder
                .comment("Disabling this setting will turn off the normal effect of all Froststeel Tools/Weapons, this effect includes applying, after hitting an enemy, a 3 seconds Slowness I effect")
                .define("enable_normal", true);

        doHitParticlesSpawn = builder
                .comment("Disabling this setting will turn off the particles that spawn when hitting an entity with any Froststeel Tools")
                .define("hit_particles", true);

        builder.pop();
    }
}
