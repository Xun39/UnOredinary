package net.xun.unoredinary.config.client.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteArmorConfigClient {
    public final ModConfigSpec.BooleanValue doHurtParticlesSpawn;

    public GlacialiteArmorConfigClient(final ModConfigSpec.Builder builder) {
        builder.push("armors_glacialite");

        doHurtParticlesSpawn = builder
                .comment("Disable this setting will disable the particles that spawns randomly when an enemy hurt you while you're wearing Glacialite armors")
                .define("hurt_particles_glacialite", true);

        builder.pop();
    }
}
