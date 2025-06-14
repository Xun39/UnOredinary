package net.xun.unoredinary.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.xun.lib.common.api.util.CommonUtils;

import java.util.List;

public class UOPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ORE_CRYIC = createKey("ore_cryic");
    public static final ResourceKey<PlacedFeature> ORE_CRYIC_LOWER = createKey("ore_cryic_lower");

    public static final ResourceKey<PlacedFeature> ORE_GLACIUM = createKey("ore_glacium");
    public static final ResourceKey<PlacedFeature> ORE_GLACIUM_RARE = createKey("ore_glacium_rare");
    public static final ResourceKey<PlacedFeature> ORE_GLACIUM_LARGE = createKey("ore_glacium_large");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ORE_CRYIC, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_CRYIC),
                List.of(CountPlacement.of(6), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(16), VerticalAnchor.absolute(54)),
                        BiomeFilter.biome())
        );

        register(context, ORE_CRYIC_LOWER, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_CRYIC),
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32)),
                        BiomeFilter.biome())
        );

        register(context, ORE_GLACIUM, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_GLACIUM),
                List.of(CountPlacement.of(52), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(360)),
                        BiomeFilter.biome())
        );
        register(context, ORE_GLACIUM_RARE, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_GLACIUM_RARE),
                List.of(CountPlacement.of(36), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(360)),
                        BiomeFilter.biome())
        );
        register(context, ORE_GLACIUM_LARGE, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_GLACIUM_LARGE),
                List.of(CountPlacement.of(48), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(360)),
                        BiomeFilter.biome())
        );
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, CommonUtils.modLoc(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
