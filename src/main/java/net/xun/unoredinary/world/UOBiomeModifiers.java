package net.xun.unoredinary.world;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOPlacedFeatures;
import net.xun.unoredinary.util.UOTags;

public class UOBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ORE_CRYIC = createKey("ore_cryic");
    public static final ResourceKey<BiomeModifier> ORE_CRYIC_LOWER = createKey("ore_cryic_lower");

    public static final ResourceKey<BiomeModifier> ORE_GLACIUM = createKey("ore_glacium");
    public static final ResourceKey<BiomeModifier> ORE_GLACIUM_RARE = createKey("ore_glacium_rare");
    public static final ResourceKey<BiomeModifier> ORE_GLACIUM_LARGE = createKey("ore_glacium_large");

    public static final ResourceKey<BiomeModifier> ORE_SAPPHIRE = createKey("ore_sapphire");
    public static final ResourceKey<BiomeModifier> ORE_SAPPHIRE_MEDIUM = createKey("ore_sapphire_medium");
    public static final ResourceKey<BiomeModifier> ORE_SAPPHIRE_LARGE = createKey("ore_sapphire_large");
    public static final ResourceKey<BiomeModifier> ORE_SAPPHIRE_BURIED = createKey("ore_sapphire_buried");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Cryic Ores
        context.register(ORE_CRYIC, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_CRYIC)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );

        context.register(ORE_CRYIC_LOWER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_CRYIC_LOWER)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );

        // Glacium Ores
        context.register(ORE_GLACIUM, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(UOTags.Biomes.HAS_ICEBERG),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_GLACIUM)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(ORE_GLACIUM_RARE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(UOTags.Biomes.HAS_ICEBERG),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_GLACIUM_RARE)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(ORE_GLACIUM_LARGE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(UOTags.Biomes.HAS_ICEBERG),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_GLACIUM_LARGE)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );

        // Sapphire Ores
        context.register(ORE_SAPPHIRE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_SAPPHIRE)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(ORE_SAPPHIRE_MEDIUM, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_SAPPHIRE_MEDIUM)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(ORE_SAPPHIRE_LARGE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_SAPPHIRE_LARGE)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(ORE_SAPPHIRE_BURIED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_SAPPHIRE_BURIED)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
    }

    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CommonUtils.modLoc(name));
    }
}
