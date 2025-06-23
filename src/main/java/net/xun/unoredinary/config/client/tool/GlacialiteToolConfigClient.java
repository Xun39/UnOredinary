package net.xun.unoredinary.config.client.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteToolConfigClient {
    public final ModConfigSpec.BooleanValue doHitParticlesSpawn;

    public GlacialiteToolConfigClient(final ModConfigSpec.Builder builder) {
        builder.push("glacialite");

        doHitParticlesSpawn = builder
                .comment("Disabling this setting will turn off the particles that spawn when hitting an entity with any Glacialite Tools")
                .define("hit_particles", true);

        builder.pop();
    }
}
