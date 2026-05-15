package net.xun.unoredinary.config.client;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.xun.unoredinary.config.client.particle.ParticleConfig;

public class UOClientConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ParticleConfig particleConfig;

    static {
        particleConfig = new ParticleConfig(BUILDER);

        SPEC = BUILDER.build();
    }
}
