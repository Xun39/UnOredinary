package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagEntry;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModBlocks;
import xun.unoredinary.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends BlockTagsProvider {

    public ModBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, UnOredinary.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.HEMOCRYLIC_BLOCK.get(),
                ModBlocks.HEMOCRYLIC_ORE.get(),
                ModBlocks.FROSTEEL_BLOCK.get(),

                ModBlocks.CRYOSTONE_ORE.get(),
                ModBlocks.DEEPSLATE_CRYOSTONE_ORE.get(),
                ModBlocks.CRYOSTONE_BLOCK.get(),

                ModBlocks.LUMINITE_ORE.get(),
                ModBlocks.DEEPSLATE_LUMINITE_ORE.get(),
                ModBlocks.LUMINITE_BLOCK.get(),

                ModBlocks.RUBY_ORE.get(),
                ModBlocks.DEEPSLATE_RUBY_ORE.get(),
                ModBlocks.RUBY_BLOCK.get(),

                ModBlocks.SOLARITE_ORE.get(),

                ModBlocks.FROSTBOUND_STONE.get(),

                ModBlocks.FROSTBOUND_STONE_STAIRS.get(),
                ModBlocks.FROSTBOUND_STONE_SLAB.get(),

                ModBlocks.FROSTBOUND_COBBLESTONE.get(),
                ModBlocks.FROSTBOUND_COBBLESTONE_STAIRS.get(),
                ModBlocks.FROSTBOUND_COBBLESTONE_SLAB.get(),
                ModBlocks.FROSTBOUND_COBBLESTONE_WALL.get(),

                ModBlocks.CRYOBOUND_COBBLESTONE.get(),
                ModBlocks.CRYOBOUND_COBBLESTONE_STAIRS.get(),
                ModBlocks.CRYOBOUND_COBBLESTONE_SLAB.get(),
                ModBlocks.CRYOBOUND_COBBLESTONE_WALL.get(),

                ModBlocks.FROSTBOUND_STONE_BRICKS.get(),
                ModBlocks.FROSTBOUND_STONE_BRICK_STAIRS.get(),
                ModBlocks.FROSTBOUND_STONE_BRICK_SLAB.get(),
                ModBlocks.FROSTBOUND_STONE_BRICK_WALL.get(),

                ModBlocks.ICE_BRICKS.get(),
                ModBlocks.ICE_BRICK_STAIRS.get(),
                ModBlocks.ICE_BRICK_SLAB.get(),
                ModBlocks.ICE_BRICK_WALL.get()
        );


        tag(BlockTags.NEEDS_STONE_TOOL).add(
                ModBlocks.LUMINITE_ORE.get(),
                ModBlocks.DEEPSLATE_LUMINITE_ORE.get()
        );

        tag(BlockTags.NEEDS_IRON_TOOL).add(
                ModBlocks.HEMOCRYLIC_BLOCK.get(),
                ModBlocks.HEMOCRYLIC_ORE.get(),

                ModBlocks.CRYOSTONE_ORE.get(),
                ModBlocks.DEEPSLATE_CRYOSTONE_ORE.get(),

                ModBlocks.RUBY_ORE.get(),
                ModBlocks.DEEPSLATE_RUBY_ORE.get()
        );

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(
                ModBlocks.SOLARITE_ORE.get()
        );

        tag(ModTags.Blocks.NEEDS_FROSTEEL_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);
        tag(ModTags.Blocks.NEEDS_LUMINTHIUM_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);
        tag(ModTags.Blocks.NEEDS_SOLARITE_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        tag(ModTags.Blocks.NEEDS_RUBY_TOOL).addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);

        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FROSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_LUMINTHIUM_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SOLARITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL);

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FROSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_LUMINTHIUM_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SOLARITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL);

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FROSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_LUMINTHIUM_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SOLARITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL);

        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FROSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_LUMINTHIUM_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SOLARITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL);

        tag(BlockTags.WALLS).add(
                ModBlocks.FROSTBOUND_COBBLESTONE_WALL.get(),
                ModBlocks.CRYOBOUND_COBBLESTONE_WALL.get(),
                ModBlocks.FROSTBOUND_STONE_BRICK_WALL.get(),
                ModBlocks.ICE_BRICK_WALL.get()
        );

        tag(BlockTags.SLABS).add(
                ModBlocks.FROSTBOUND_STONE_SLAB.get(),
                ModBlocks.FROSTBOUND_COBBLESTONE_SLAB.get(),
                ModBlocks.CRYOBOUND_COBBLESTONE_SLAB.get(),
                ModBlocks.FROSTBOUND_STONE_BRICK_SLAB.get(),
                ModBlocks.ICE_BRICK_SLAB.get()
        );

        tag(BlockTags.STAIRS).add(
                ModBlocks.FROSTBOUND_STONE_STAIRS.get(),
                ModBlocks.FROSTBOUND_COBBLESTONE_STAIRS.get(),
                ModBlocks.CRYOBOUND_COBBLESTONE_STAIRS.get(),
                ModBlocks.FROSTBOUND_STONE_BRICK_STAIRS.get(),
                ModBlocks.ICE_BRICK_STAIRS.get()
        );
    }
}
