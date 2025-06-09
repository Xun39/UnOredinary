package net.xun.unoredinary.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.xun.lib.common.api.util.CommonUtils;

public class UOTags {

    public static class Blocks {

        public static final TagKey<Block> ORES_GLACIUM = createCommon("ores/glacium");

        private static TagKey<Block> create(String name) {
            return BlockTags.create(CommonUtils.modLoc(name));
        }

        private static TagKey<Block> createCommon(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Items {

        public static final TagKey<Item> GEMS_GLACIUM = createCommon("gems/glacium");
        public static final TagKey<Item> NUGGETS_GLACIUM = createCommon("nuggets/glacium");
        public static final TagKey<Item> INGOTS_FROSTSTEEL = createCommon("ingots/froststeel");

        private static TagKey<Item> create(String name) {
            return ItemTags.create(CommonUtils.modLoc(name));
        }

        private static TagKey<Item> createCommon(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}
