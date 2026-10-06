package net.xun.unoredinary.config.server;

import net.xun.lib.common.api.config.ConfigEntry;
import net.xun.lib.common.api.config.ConfigGroup;
import net.xun.lib.common.api.config.VisibleWhen;

public class ArmorEffectConfig {

    @ConfigEntry
    public boolean onlyPlayer = true;

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
        public boolean enableNightVision = true;
    }

    public static class Froststeel {
        @ConfigEntry
        public boolean enable = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean enableFrostWalker = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean immuneToHotFloor = true;

        @ConfigEntry(min = 1, max = 8, step = 1)
        @VisibleWhen(field = "enable")
        public int frostWalkerRadius = 2;
    }

    public static class Glacialite {
        @ConfigEntry
        public boolean enable = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean enableFrostWalker = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean immuneToSlowness = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean thorns = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean immuneToHotFloor = true;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean walkOnPowderSnow = true;

        @ConfigEntry(min = 1, max = 16, step = 1)
        @VisibleWhen(field = "enable")
        public int frostWalkerRadius = 4;

        @ConfigEntry
        @VisibleWhen(field = "enable")
        public boolean damageParticle = true;
    }
}
