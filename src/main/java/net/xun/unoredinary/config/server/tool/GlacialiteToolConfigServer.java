package net.xun.unoredinary.config.server.tool;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GlacialiteToolConfigServer {
    public final ModConfigSpec.BooleanValue enable;
    public final ModConfigSpec.BooleanValue enableNormalEffect;
    public final ModConfigSpec.BooleanValue enableFrostNova;
    public final ModConfigSpec.BooleanValue frostNovaToPassive;

    public final ModConfigSpec.BooleanValue enableFrostNovaSound;
    public final ModConfigSpec.BooleanValue doHitParticlesSpawn;

    public GlacialiteToolConfigServer(final ModConfigSpec.Builder builder) {
        builder.push("glacialite");

        enable = builder
                .comment("Disabling this setting will turn off all tool effects of Glacialite Tools")
                .define("enable_tool", true);

        enableNormalEffect = builder
                .comment("Disabling this setting will turn off the normal effect of all Glacialite Tools/Weapons, this effect includes applying, after hitting an enemy, Weakness I and Slowness II")
                .define("enable_normal", true);

        enableFrostNova = builder
                .comment("Disabling this setting will turn off the frost nova effect of Glacialite Sword and Glacialite Axe, notice that they are going to have the normal Glacialite effect after")
                .define("enable_frost_nova", true);

        frostNovaToPassive = builder
                .comment("Turning this setting on will allow frost nova effect to be applied on passive mobs")
                .define("frost_nova_to_passive", false);

        enableFrostNovaSound = builder
                .comment("Disabling this setting will turn off the sound effect played when you hit a mob with Glacialite Sword or Glacialite Axe")
                .define("frost_nova_sound", true);

        doHitParticlesSpawn = builder
                .comment("Disabling this setting will turn off the particles that spawn when hitting an entity with any Glacialite Tools")
                .define("hit_particles", true);

        builder.pop();
    }
}
