package xun.unoredinary.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.world.util.ModOrePlacement;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> HEMOCRYLIC_ORE_PLACED_KEY = registerKey("ore_hemocrylic");
    public static final ResourceKey<PlacedFeature> LARGE_HEMOCRYLIC_ORE_PLACED_KEY = registerKey("ore_hemocrylic_large");

    public static final ResourceKey<PlacedFeature> CRYOSTONE_ORE_PLACED_KEY = registerKey("ore_cryostone");
    public static final ResourceKey<PlacedFeature> LOWER_CRYOSTONE_ORE_PLACED_KEY = registerKey("ore_cryostone_lower");

    public static final ResourceKey<PlacedFeature> LUMINITE_ORE_PLACED_KEY = registerKey("ore_luminite");

    public static final ResourceKey<PlacedFeature> SOLARITE_ORE_PLACED_KEY = registerKey("ore_solarite");

    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ore_ruby");
    public static final ResourceKey<PlacedFeature> MEDIUM_RUBY_ORE_PLACED_KEY = registerKey("ore_ruby_medium");
    public static final ResourceKey<PlacedFeature> LARGE_RUBY_ORE_PLACED_KEY = registerKey("ore_ruby_large");
    public static final ResourceKey<PlacedFeature> BURIED_RUBY_ORE_PLACED_KEY = registerKey("ore_ruby_buried");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, HEMOCRYLIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HEMOCRYLIC_ORE_KEY),
                ModOrePlacement.oreCountPlacement(82,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(360))));
        register(context, LARGE_HEMOCRYLIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LARGE_HEMOCRYLIC_ORE_KEY),
                ModOrePlacement.oreChancePlacement(48,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(360))));


        register(context, CRYOSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYOSTONE_ORE_KEY),
                ModOrePlacement.oreCountPlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(15))));
        register(context, LOWER_CRYOSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYOSTONE_ORE_KEY),
                ModOrePlacement.oreCountPlacement(8,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))));


        register(context, LUMINITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LUMINITE_ORE_KEY),
                ModOrePlacement.oreCountPlacement(6,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-48), VerticalAnchor.belowTop(36))));

        register(context, SOLARITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SOLARITE_ORE_KEY),
                ModOrePlacement.oreCountPlacement(20,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(0))));

        register(context, RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SMALL_RUBY_ORE_KEY),
                ModOrePlacement.oreCountPlacement(7,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, MEDIUM_RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MEDIUM_RUBY_ORE_KEY),
                ModOrePlacement.oreCountPlacement(2,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-8))));
        register(context, LARGE_RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LARGE_RUBY_ORE_KEY),
                ModOrePlacement.oreChancePlacement(11,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, BURIED_RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SMALL_RUBY_ORE_KEY),
                ModOrePlacement.oreCountPlacement(5,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, UnOredinary.modLoc(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
