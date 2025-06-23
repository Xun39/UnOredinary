package net.xun.unoredinary.config.client.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FroststeelToolConfigClient {
    public final ModConfigSpec.BooleanValue doHitParticlesSpawn;

    public FroststeelToolConfigClient(final ModConfigSpec.Builder builder) {
        builder.push("froststeel");

        doHitParticlesSpawn = builder
                .comment("Disabling this setting will turn off the particles that spawn when hitting an entity with any Froststeel Tools")
                .define("hit_particles", true);

        builder.pop();
    }
}
