package net.xun.unoredinary.registry;

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
import net.xun.lib.common.api.util.CommonUtils;

import java.util.List;

public class UOConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CRYIC = createKey("ore_cryic");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM = createKey("ore_glacium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM_RARE = createKey("ore_glacium_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM_LARGE = createKey("ore_glacium_large");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest iceReplaceable = new TagMatchTest(BlockTags.ICE);

        register(context, ORE_CRYIC, Feature.ORE, new OreConfiguration(
                List.of(
                        OreConfiguration.target(stoneReplaceable, UOBlocks.CRYIC_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceable, UOBlocks.DEEPSLATE_CRYIC_ORE.get().defaultBlockState())
                ), 7)
        );

        register(context, ORE_GLACIUM, Feature.SCATTERED_ORE, new OreConfiguration(iceReplaceable, UOBlocks.GLACIUM_ORE.get().defaultBlockState(), 2, 0.3F));
        register(context, ORE_GLACIUM_RARE, Feature.SCATTERED_ORE, new OreConfiguration(iceReplaceable, UOBlocks.PRIMAL_GLACIUM_ORE.get().defaultBlockState(), 2, 0.6F));
        register(context, ORE_GLACIUM_LARGE, Feature.SCATTERED_ORE, new OreConfiguration(iceReplaceable, UOBlocks.GLACIUM_ORE.get().defaultBlockState(), 4, 0.3F));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CommonUtils.modLoc(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
