package xun.unoredinary.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> HEMOCRYLIC_ORE_KEY = registerKey("ore_hemocrylic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_HEMOCRYLIC_ORE_KEY = registerKey("ore_hemocrylic_large");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYOSTONE_ORE_KEY = registerKey("ore_cryostone");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest iceReplaceables = new TagMatchTest(BlockTags.ICE);
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldCryostoneOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.CRYOSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_CRYOSTONE_ORE.get().defaultBlockState()));

        register(context, HEMOCRYLIC_ORE_KEY, Feature.ORE, new OreConfiguration(iceReplaceables,
                ModBlocks.HEMOCRYLIC_ORE.get().defaultBlockState(), 3));
        register(context, LARGE_HEMOCRYLIC_ORE_KEY, Feature.ORE, new OreConfiguration(iceReplaceables,
                ModBlocks.HEMOCRYLIC_ORE.get().defaultBlockState(), 7));

        register(context, CRYOSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldCryostoneOres, 8));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, UnOredinary.modLoc(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
