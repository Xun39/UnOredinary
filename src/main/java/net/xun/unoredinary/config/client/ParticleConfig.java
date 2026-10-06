package net.xun.unoredinary.config.client;

import net.xun.lib.common.api.config.ConfigEntry;
import net.xun.lib.common.api.config.ConfigGroup;

public class ParticleConfig {

    @ConfigGroup
    public final FrostNova frostNova = new FrostNova();

    public static class FrostNova {
        @ConfigEntry
        public boolean emissive = true;
    }
}
