package xun.unoredinary.world.feature;

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
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.UOBlocks;

import java.util.List;

public class UOConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> HEMOCRYLIC_ORE_KEY = registerKey("ore_hemocrylic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_HEMOCRYLIC_ORE_KEY = registerKey("ore_hemocrylic_large");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYOSTONE_ORE_KEY = registerKey("ore_cryostone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LUMINITE_ORE_KEY = registerKey("ore_luminite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SOLARITE_ORE_KEY = registerKey("ore_solarite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_RUBY_ORE_KEY = registerKey("ore_ruby_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_RUBY_ORE_KEY = registerKey("ore_ruby_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_RUBY_ORE_KEY = registerKey("ore_ruby_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURIED_RUBY_ORE_KEY = registerKey("ore_ruby_buried");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest iceReplaceable = new TagMatchTest(BlockTags.ICE);
        RuleTest lavaReplaceable = new BlockMatchTest(Blocks.LAVA);
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldCryostoneOres = List.of(
                OreConfiguration.target(stoneReplaceable, UOBlocks.CRYOSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, UOBlocks.DEEPSLATE_CRYOSTONE_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldLuminiteOres = List.of(
                OreConfiguration.target(stoneReplaceable, UOBlocks.LUMINITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, UOBlocks.DEEPSLATE_LUMINITE_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldRubyOres = List.of(
                OreConfiguration.target(stoneReplaceable, UOBlocks.RUBY_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, UOBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));

        register(context, HEMOCRYLIC_ORE_KEY, Feature.ORE, new OreConfiguration(iceReplaceable, UOBlocks.HEMOCRYLIC_ORE.get().defaultBlockState(), 3));
        register(context, LARGE_HEMOCRYLIC_ORE_KEY, Feature.ORE, new OreConfiguration(iceReplaceable, UOBlocks.HEMOCRYLIC_ORE.get().defaultBlockState(), 7));

        register(context, CRYOSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldCryostoneOres, 8));

        register(context, LUMINITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldLuminiteOres, 5));

        register(context, SOLARITE_ORE_KEY, Feature.ORE, new OreConfiguration(lavaReplaceable, UOBlocks.SOLARITE_ORE.get().defaultBlockState(), 3));

        register(context, SMALL_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRubyOres, 3,0.5F));
        register(context, MEDIUM_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRubyOres, 7,0.6F));
        register(context, LARGE_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRubyOres, 13,0.8F));
        register(context, BURIED_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRubyOres, 8,1.0F));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, UnOredinary.modLoc(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
