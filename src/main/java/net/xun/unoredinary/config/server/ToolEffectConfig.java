package net.xun.unoredinary.config.server;

import net.xun.lib.common.api.config.ConfigEntry;
import net.xun.lib.common.api.config.ConfigGroup;
import net.xun.lib.common.api.config.VisibleWhen;

public class ToolEffectConfig {

    @ConfigGroup
    public final Luminium luminium = new Luminium();

    @ConfigGroup
    public final Froststeel froststeel = new Froststeel();

    @ConfigGroup
    public final Glacialite glacialite = new Glacialite();

    public static class Luminium {
        @ConfigEntry
        public boolean enable = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean enableGlowingOnHit = true;
    }

    public static class Froststeel {
        @ConfigEntry
        public boolean enable = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean enableNormalEffect = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean doHitParticlesSpawn = true;
    }

    public static class Glacialite {
        @ConfigEntry
        public boolean enable = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean enableNormalEffect = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean enableFrostNova = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean frostNovaToPassive = false;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean enableFrostNovaSound = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean doHitParticlesSpawn = true;
    }
}
