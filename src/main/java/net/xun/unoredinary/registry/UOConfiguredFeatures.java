package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.xun.lib.common.api.util.CommonUtils;

import java.util.List;

public class UOConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CRYIC = createKey("ore_cryic");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM = createKey("ore_glacium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM_RARE = createKey("ore_glacium_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM_LARGE = createKey("ore_glacium_large");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LUMINITE = createKey("ore_luminite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LUMINITE_UPPER = createKey("ore_luminite_upper");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_SMALL = createKey("ore_sapphire_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_MEDIUM = createKey("ore_sapphire_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_LARGE = createKey("ore_sapphire_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_BURIED = createKey("ore_sapphire_buried");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = createKey("ore_ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_LARGE = createKey("ore_ruby_large");

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CommonUtils.modLoc(name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest iceReplaceable = new TagMatchTest(BlockTags.ICE);
        RuleTest netherStonesReplaceable = new TagMatchTest(BlockTags.BASE_STONE_NETHER);

        List<OreConfiguration.TargetBlockState> luminiteOres = List.of(
                OreConfiguration.target(stoneReplaceable, UOBlocks.LUMINITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, UOBlocks.DEEPSLATE_LUMINITE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> sapphireOres = List.of(
                OreConfiguration.target(stoneReplaceable, UOBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, UOBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())
        );

        register(context, ORE_CRYIC, Feature.ORE, new OreConfiguration(
                List.of(
                        OreConfiguration.target(stoneReplaceable, UOBlocks.CRYIC_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceable, UOBlocks.DEEPSLATE_CRYIC_ORE.get().defaultBlockState())
                ), 7)
        );

        register(context, ORE_GLACIUM, Feature.SCATTERED_ORE, new OreConfiguration(iceReplaceable, UOBlocks.GLACIUM_ORE.get().defaultBlockState(), 2, 0.3F));
        register(context, ORE_GLACIUM_RARE, Feature.SCATTERED_ORE, new OreConfiguration(iceReplaceable, UOBlocks.PRIMAL_GLACIUM_ORE.get().defaultBlockState(), 2, 0.6F));
        register(context, ORE_GLACIUM_LARGE, Feature.SCATTERED_ORE, new OreConfiguration(iceReplaceable, UOBlocks.GLACIUM_ORE.get().defaultBlockState(), 4, 0.3F));

        register(context, ORE_LUMINITE, Feature.ORE, new OreConfiguration(luminiteOres, 5));
        register(context, ORE_LUMINITE_UPPER, Feature.ORE, new OreConfiguration(luminiteOres, 2));

        register(context, ORE_SAPPHIRE_SMALL, Feature.ORE, new OreConfiguration(sapphireOres, 3, 0.5F));
        register(context, ORE_SAPPHIRE_MEDIUM, Feature.ORE, new OreConfiguration(sapphireOres, 6, 0.6F));
        register(context, ORE_SAPPHIRE_LARGE, Feature.ORE, new OreConfiguration(sapphireOres, 10, 0.7F));
        register(context, ORE_SAPPHIRE_BURIED, Feature.ORE, new OreConfiguration(sapphireOres, 6, 1.0F));

        register(context, ORE_RUBY, Feature.ORE, new OreConfiguration(netherStonesReplaceable, UOBlocks.NETHER_RUBY_ORE.get().defaultBlockState(), 4));
        register(context, ORE_RUBY_LARGE, Feature.ORE, new OreConfiguration(netherStonesReplaceable, UOBlocks.NETHER_RUBY_ORE.get().defaultBlockState(), 9, 0.2F));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
