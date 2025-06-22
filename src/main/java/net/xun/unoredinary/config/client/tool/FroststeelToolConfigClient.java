package net.xun.unoredinary.config.client.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FroststeelToolConfigClient {
    public final ModConfigSpec.BooleanValue doHitParticlesSpawn;

    public FroststeelToolConfigClient(final ModConfigSpec.Builder builder) {
        builder.push("tools_froststeel");

        doHitParticlesSpawn = builder
                .comment("Disable this setting will disable the particles that spawns when hitting an entity with Froststeel Tools")
                .define("hit_particles_froststeel", true);

        builder.pop();
    }
}
