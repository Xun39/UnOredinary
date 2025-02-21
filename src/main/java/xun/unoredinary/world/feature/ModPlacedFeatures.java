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

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, HEMOCRYLIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HEMOCRYLIC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(82,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(360))));

        register(context, LARGE_HEMOCRYLIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LARGE_HEMOCRYLIC_ORE_KEY),
                ModOrePlacement.rareOrePlacement(48,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(360))));


        register(context, CRYOSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYOSTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(15))));

        register(context, LOWER_CRYOSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYOSTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))));

        register(context, LUMINITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LUMINITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-48), VerticalAnchor.belowTop(36))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, UnOredinary.modLoc(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
