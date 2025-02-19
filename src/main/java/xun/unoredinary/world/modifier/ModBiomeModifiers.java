package xun.unoredinary.world.modifier;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModEntityTypes;
import xun.unoredinary.world.feature.ModPlacedFeatures;

import java.util.List;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_ORE_HEMOCRYLIC = registerKey("add_ore_hemocrylic");
    public static final ResourceKey<BiomeModifier> ADD_ORE_HEMOCRYLIC_LARGE = registerKey("add_ore_hemocrylic_large");

    public static final ResourceKey<BiomeModifier> ADD_ORE_CRYOSTONE = registerKey("add_ore_cryostone");
    public static final ResourceKey<BiomeModifier> ADD_ORE_CRYOSTONE_LOWER = registerKey("add_ore_cryostone_lower");

    public static final ResourceKey<BiomeModifier> SPAWN_FROST_ZOMBIE = registerKey("spawn_entity_frost_zombie");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Hemocrylic Ores
        context.register(ADD_ORE_HEMOCRYLIC, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.HEMOCRYLIC_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_HEMOCRYLIC_LARGE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LARGE_HEMOCRYLIC_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Cryostone Ores
        context.register(ADD_ORE_CRYOSTONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CRYOSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_CRYOSTONE_LOWER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LOWER_CRYOSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Frost Zombies
        context.register(SPAWN_FROST_ZOMBIE, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntityTypes.FROST_ZOMBIE.get(), 20, 2, 4))));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, UnOredinary.modLoc(name));
    }
}
