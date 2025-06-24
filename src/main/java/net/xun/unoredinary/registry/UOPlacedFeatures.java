package net.xun.unoredinary.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.world.helper.OrePlacementHelper;

import java.util.List;

public class UOPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ORE_CRYIC = createKey("ore_cryic");
    public static final ResourceKey<PlacedFeature> ORE_CRYIC_LOWER = createKey("ore_cryic_lower");

    public static final ResourceKey<PlacedFeature> ORE_GLACIUM = createKey("ore_glacium");
    public static final ResourceKey<PlacedFeature> ORE_GLACIUM_RARE = createKey("ore_glacium_rare");
    public static final ResourceKey<PlacedFeature> ORE_GLACIUM_LARGE = createKey("ore_glacium_large");

    public static final ResourceKey<PlacedFeature> ORE_LUMINITE = createKey("ore_luminite");
    public static final ResourceKey<PlacedFeature> ORE_LUMINITE_UPPER = createKey("ore_luminite_upper");

    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE = createKey("ore_sapphire");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_MEDIUM = createKey("ore_sapphire_medium");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_LARGE = createKey("ore_sapphire_large");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_BURIED = createKey("ore_sapphire_buried");


    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, CommonUtils.modLoc(name));
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Cryic ores
        register(context, ORE_CRYIC, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_CRYIC),
                OrePlacementHelper.countPlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(16), VerticalAnchor.absolute(54)))
        );
        register(context, ORE_CRYIC_LOWER, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_CRYIC),
                OrePlacementHelper.countPlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32)))
        );

        // Glacium ores
        register(context, ORE_GLACIUM, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_GLACIUM),
                OrePlacementHelper.countPlacement(52, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(360)))
        );
        register(context, ORE_GLACIUM_RARE, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_GLACIUM_RARE),
                OrePlacementHelper.countPlacement(36, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(360)))
        );
        register(context, ORE_GLACIUM_LARGE, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_GLACIUM_LARGE),
                OrePlacementHelper.countPlacement(48, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(360)))
        );

        // Luminite ores
        register(context, ORE_LUMINITE, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_LUMINITE),
                OrePlacementHelper.countPlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-48), VerticalAnchor.absolute(36)))
        );
        register(context, ORE_LUMINITE_UPPER, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_LUMINITE_UPPER),
                OrePlacementHelper.countPlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(36), VerticalAnchor.absolute(84)))
        );

        // Sapphire ores
        register(context, ORE_SAPPHIRE, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_SAPPHIRE_SMALL),
                OrePlacementHelper.countPlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))
        );
        register(context, ORE_SAPPHIRE_MEDIUM, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_SAPPHIRE_MEDIUM),
                OrePlacementHelper.countPlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-6)))
        );
        register(context, ORE_SAPPHIRE_LARGE, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_SAPPHIRE_MEDIUM),
                OrePlacementHelper.rarityPlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)))
        );
        register(context, ORE_SAPPHIRE_BURIED, configuredFeatures.getOrThrow(UOConfiguredFeatures.ORE_SAPPHIRE_MEDIUM),
                OrePlacementHelper.countPlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)))
        );
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
