package net.xun.unoredinary.config.client.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteToolConfigClient {
    public final ModConfigSpec.BooleanValue doHitParticlesSpawn;

    public GlacialiteToolConfigClient(final ModConfigSpec.Builder builder) {
        builder.push("tools_glacialite");

        doHitParticlesSpawn = builder
                .comment("Disable this setting will disable the particles that spawns when hitting an entity with Glacialite Tools")
                .define("hit_particles_glacialite", true);

        builder.pop();
    }
}
