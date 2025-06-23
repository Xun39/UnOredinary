package net.xun.unoredinary.config.client.armor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteArmorConfigClient {
    public final ModConfigSpec.BooleanValue doHurtParticlesSpawn;

    public GlacialiteArmorConfigClient(final ModConfigSpec.Builder builder) {
        builder.push("glacialite");

        doHurtParticlesSpawn = builder
                .comment("Disabling this setting will turn off the particles that spawn when enemies damage you while wearing Glacialite armor")
                .define("hurt_particles", true);

        builder.pop();
    }
}
