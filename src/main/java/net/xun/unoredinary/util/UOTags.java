package net.xun.unoredinary.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.xun.lib.common.api.util.CommonUtils;

public class UOTags {

    public static class Blocks {

        public static final TagKey<Block> NEEDS_FROSTSTEEL_TOOL = create("needs_froststeel_tool");
        public static final TagKey<Block> INCORRECT_FOR_FROSTSTEEL_TOOL = create("incorrect_for_froststeel_tool");

        public static final TagKey<Block> ORES_CRYIC = createCommon("ores/cryic");
        public static final TagKey<Block> ORES_SAPPHIRE = createCommon("ores/sapphire");
        public static final TagKey<Block> ORES_GLACIUM = createCommon("ores/glacium");
        public static final TagKey<Block> ORES_LUMINITE = createCommon("ores/luminite");

        public static final TagKey<Block> STORAGE_BLOCKS_CRYIC = createCommon("storage_blocks/cryic");
        public static final TagKey<Block> STORAGE_BLOCKS_SAPPHIRE = createCommon("storage_blocks/sapphire");
        public static final TagKey<Block> STORAGE_BLOCKS_GLACIUM = createCommon("storage_blocks/glacium");
        public static final TagKey<Block> STORAGE_BLOCKS_FROSTSTEEL = createCommon("storage_blocks/froststeel");
        public static final TagKey<Block> STORAGE_BLOCKS_GLACIALITE = createCommon("storage_blocks/glacialite");
        public static final TagKey<Block> STORAGE_BLOCKS_LUMINITE = createCommon("storage_blocks/luminite");
        public static final TagKey<Block> STORAGE_BLOCKS_LUMINIUM = createCommon("storage_blocks/luminium");

        private static TagKey<Block> create(String name) {
            return BlockTags.create(CommonUtils.modLoc(name));
        }

        private static TagKey<Block> createCommon(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Items {

        public static final TagKey<Item> GEMS_GLACIUM = createCommon("gems/glacium");
        public static final TagKey<Item> GEMS_SAPPHIRE = createCommon("gems/sapphire");
        public static final TagKey<Item> GEMS_LUMINITE = createCommon("gems/luminite");

        public static final TagKey<Item> NUGGETS_GLACIUM = createCommon("nuggets/glacium");

        public static final TagKey<Item> INGOTS_FROSTSTEEL = createCommon("ingots/froststeel");
        public static final TagKey<Item> INGOTS_GLACIALITE = createCommon("ingots/glacialite");
        public static final TagKey<Item> INGOTS_LUMINIUM = createCommon("ingots/luminium");

        private static TagKey<Item> create(String name) {
            return ItemTags.create(CommonUtils.modLoc(name));
        }

        private static TagKey<Item> createCommon(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> HAS_ICEBERG = create("has_iceberg");

        public static final TagKey<Biome> HAS_STRUCTURE_FROST_DUNGEON = create("has_structure/frost_dungeon");

        private static TagKey<Biome> create(String name) {
            return TagKey.create(Registries.BIOME, CommonUtils.modLoc(name));
        }
    }
}