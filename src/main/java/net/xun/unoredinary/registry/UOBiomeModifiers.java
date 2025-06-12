package net.xun.unoredinary.registry;

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

public class UOBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ORE_GLACIUM = createKey("ore_glacium");
    public static final ResourceKey<BiomeModifier> ORE_GLACIUM_RARE = createKey("ore_glacium_rare");
    public static final ResourceKey<BiomeModifier> ORE_GLACIUM_LARGE = createKey("ore_glacium_large");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Glacium Ore
        context.register(ORE_GLACIUM, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_GLACIUM)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(ORE_GLACIUM_RARE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_GLACIUM_RARE)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(ORE_GLACIUM_LARGE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD),
                HolderSet.direct(placedFeatures.getOrThrow(UOPlacedFeatures.ORE_GLACIUM_LARGE)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );
    }

    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CommonUtils.modLoc(name));
    }
}
