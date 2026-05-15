package net.xun.unoredinary.config.client.particle;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ParticleConfig {
    public final FrostNovaParticleConfig frostNovaConfig;

    public ParticleConfig(final ModConfigSpec.Builder builder) {
        builder.push("particle_ops");

        frostNovaConfig = new FrostNovaParticleConfig(builder);

        builder.pop();
    }
}
