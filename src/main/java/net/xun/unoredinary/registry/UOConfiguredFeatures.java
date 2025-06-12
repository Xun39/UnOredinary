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

public class UOConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM = createKey("ore_glacium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM_RARE = createKey("ore_glacium_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLACIUM_LARGE = createKey("ore_glacium_large");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest iceReplaceable = new TagMatchTest(BlockTags.ICE);

        register(context, ORE_GLACIUM, Feature.ORE, new OreConfiguration(iceReplaceable, UOBlocks.GLACIUM_ORE.get().defaultBlockState(), 3, 0.3F));
        register(context, ORE_GLACIUM_RARE, Feature.ORE, new OreConfiguration(iceReplaceable, UOBlocks.GLACIAL_CORE.get().defaultBlockState(), 3, 0.6F));
        register(context, ORE_GLACIUM_LARGE, Feature.ORE, new OreConfiguration(iceReplaceable, UOBlocks.GLACIUM_ORE.get().defaultBlockState(), 7, 0.3F));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CommonUtils.modLoc(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
