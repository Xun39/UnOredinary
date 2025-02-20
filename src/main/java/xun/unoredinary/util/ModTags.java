package xun.unoredinary.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import xun.unoredinary.UnOredinary;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> NEEDS_FROSTEEL_TOOL = createTag("needs_frosteel_tool");
        public static final TagKey<Block> INCORRECT_FOR_FROSTEEL_TOOL = createTag("incorrect_for_frosteel_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(UnOredinary.modLoc(name));
        }
    }

    public static class Items {

        public static final TagKey<Item> FROSTEEL_TOOL = createTag("frosteel_tool");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(UnOredinary.modLoc(name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> HAS_FROZEN_TOMB = createTag("has_structure/frozen_tomb");

        private static TagKey<Biome> createTag(String name) {
            return TagKey.create(ResourceKey.create(Registries.BIOME.registryKey(), UnOredinary.modLoc(name)), UnOredinary.modLoc(name));
        }
    }
}
