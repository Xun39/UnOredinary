package xun.unoredinary.world.modifier;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.UOEntityTypes;
import xun.unoredinary.world.feature.UOPlacedFeatures;

import java.util.List;

public class UOBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_ORE_HEMOCRYLIC = registerKey("add_ore_hemocrylic");
    public static final ResourceKey<BiomeModifier> ADD_ORE_HEMOCRYLIC_LARGE = registerKey("add_ore_hemocrylic_large");

    public static final ResourceKey<BiomeModifier> ADD_ORE_CRYOSTONE = registerKey("add_ore_cryostone");
    public static final ResourceKey<BiomeModifier> ADD_ORE_CRYOSTONE_LOWER = registerKey("add_ore_cryostone_lower");

    public static final ResourceKey<BiomeModifier> ADD_ORE_LUMINITE = registerKey("add_ore_luminite");

    public static final ResourceKey<BiomeModifier> ADD_ORE_SOLARITE = registerKey("add_ore_solarite");

    public static final ResourceKey<BiomeModifier> ADD_ORE_RUBY = registerKey("add_ore_ruby");
    public static final ResourceKey<BiomeModifier> ADD_ORE_RUBY_MEDIUM = registerKey("add_ore_ruby_medium");
    public static final ResourceKey<BiomeModifier> ADD_ORE_RUBY_LARGE= registerKey("add_ore_ruby_large");
    public static final ResourceKey<BiomeModifier> ADD_ORE_RUBY_BURIED = registerKey("add_ore_ruby_buried");


    public static final ResourceKey<BiomeModifier> ADD_FROST_ZOMBIE = registerKey("add_entity_frost_zombie");
    public static final ResourceKey<BiomeModifier> REMOVE_ZOMBIE_IN_COLD = registerKey("remove_entity_zombie_in_cold_biome");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var entityTypes = context.lookup(Registries.ENTITY_TYPE);
        var biomes = context.lookup(Registries.BIOME);

        // Hemocrylic Ores
        context.register(ADD_ORE_HEMOCRYLIC, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.HEMOCRYLIC_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_HEMOCRYLIC_LARGE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.LARGE_HEMOCRYLIC_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Cryostone Ores
        context.register(ADD_ORE_CRYOSTONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.CRYOSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_CRYOSTONE_LOWER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.LOWER_CRYOSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Luminite Ores
        context.register(ADD_ORE_LUMINITE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_LUSH),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.LUMINITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Solarite Ores
        context.register(ADD_ORE_SOLARITE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.SOLARITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Ruby Ores
        context.register(ADD_ORE_RUBY, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.RUBY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_RUBY_MEDIUM, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.MEDIUM_RUBY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_RUBY_LARGE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.LARGE_RUBY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_RUBY_BURIED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.BURIED_RUBY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Frost Zombies
        context.register(ADD_FROST_ZOMBIE, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                List.of(new MobSpawnSettings.SpawnerData(UOEntityTypes.FROST_ZOMBIE.get(), 24, 3, 5))));

        /*context.register(REMOVE_ZOMBIE_IN_COLD, new BiomeModifiers.RemoveSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(entityTypes.getOrThrow())));*/
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, UnOredinary.modLoc(name));
    }
}
