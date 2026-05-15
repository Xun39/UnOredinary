package net.xun.unoredinary.config.client.particle;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FrostNovaParticleConfig {
    public final ModConfigSpec.BooleanValue emissive;

    public FrostNovaParticleConfig(final ModConfigSpec.Builder builder) {
        builder.push("frost_nova_par");

        emissive = builder
                .comment("Make it emissive")
                .define("emissive", true);
    }
}
