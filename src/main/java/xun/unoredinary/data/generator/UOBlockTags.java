package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.UOBlocks;
import xun.unoredinary.util.UOTags;

import java.util.concurrent.CompletableFuture;

public class UOBlockTags extends BlockTagsProvider {

    public UOBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, UnOredinary.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                UOBlocks.HEMOCRYLIC_BLOCK.get(),
                UOBlocks.HEMOCRYLIC_ORE.get(),
                UOBlocks.FROSTEEL_BLOCK.get(),

                UOBlocks.CRYOSTONE_ORE.get(),
                UOBlocks.DEEPSLATE_CRYOSTONE_ORE.get(),
                UOBlocks.CRYOSTONE_BLOCK.get(),

                UOBlocks.LUMINITE_ORE.get(),
                UOBlocks.DEEPSLATE_LUMINITE_ORE.get(),
                UOBlocks.LUMINITE_BLOCK.get(),

                UOBlocks.RUBY_ORE.get(),
                UOBlocks.DEEPSLATE_RUBY_ORE.get(),
                UOBlocks.RUBY_BLOCK.get(),

                UOBlocks.SOLARITE_ORE.get(),

                UOBlocks.FROSTBOUND_STONE.get(),

                UOBlocks.FROSTBOUND_STONE_STAIRS.get(),
                UOBlocks.FROSTBOUND_STONE_SLAB.get(),

                UOBlocks.FROSTBOUND_COBBLESTONE.get(),
                UOBlocks.FROSTBOUND_COBBLESTONE_STAIRS.get(),
                UOBlocks.FROSTBOUND_COBBLESTONE_SLAB.get(),
                UOBlocks.FROSTBOUND_COBBLESTONE_WALL.get(),

                UOBlocks.CRYOBOUND_COBBLESTONE.get(),
                UOBlocks.CRYOBOUND_COBBLESTONE_STAIRS.get(),
                UOBlocks.CRYOBOUND_COBBLESTONE_SLAB.get(),
                UOBlocks.CRYOBOUND_COBBLESTONE_WALL.get(),

                UOBlocks.FROSTBOUND_STONE_BRICKS.get(),
                UOBlocks.FROSTBOUND_STONE_BRICK_STAIRS.get(),
                UOBlocks.FROSTBOUND_STONE_BRICK_SLAB.get(),
                UOBlocks.FROSTBOUND_STONE_BRICK_WALL.get(),

                UOBlocks.ICE_BRICKS.get(),
                UOBlocks.ICE_BRICK_STAIRS.get(),
                UOBlocks.ICE_BRICK_SLAB.get(),
                UOBlocks.ICE_BRICK_WALL.get()
        );


        tag(BlockTags.NEEDS_STONE_TOOL).add(
                UOBlocks.LUMINITE_ORE.get(),
                UOBlocks.DEEPSLATE_LUMINITE_ORE.get()
        );

        tag(BlockTags.NEEDS_IRON_TOOL).add(
                UOBlocks.HEMOCRYLIC_BLOCK.get(),
                UOBlocks.HEMOCRYLIC_ORE.get(),

                UOBlocks.CRYOSTONE_ORE.get(),
                UOBlocks.DEEPSLATE_CRYOSTONE_ORE.get(),

                UOBlocks.RUBY_ORE.get(),
                UOBlocks.DEEPSLATE_RUBY_ORE.get()
        );

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(
                UOBlocks.SOLARITE_ORE.get()
        );

        tag(BlockTags.WALLS).add(
                UOBlocks.FROSTBOUND_COBBLESTONE_WALL.get(),
                UOBlocks.CRYOBOUND_COBBLESTONE_WALL.get(),
                UOBlocks.FROSTBOUND_STONE_BRICK_WALL.get(),
                UOBlocks.ICE_BRICK_WALL.get()
        );

        tag(BlockTags.SLABS).add(
                UOBlocks.FROSTBOUND_STONE_SLAB.get(),
                UOBlocks.FROSTBOUND_COBBLESTONE_SLAB.get(),
                UOBlocks.CRYOBOUND_COBBLESTONE_SLAB.get(),
                UOBlocks.FROSTBOUND_STONE_BRICK_SLAB.get(),
                UOBlocks.ICE_BRICK_SLAB.get()
        );

        tag(BlockTags.STAIRS).add(
                UOBlocks.FROSTBOUND_STONE_STAIRS.get(),
                UOBlocks.FROSTBOUND_COBBLESTONE_STAIRS.get(),
                UOBlocks.CRYOBOUND_COBBLESTONE_STAIRS.get(),
                UOBlocks.FROSTBOUND_STONE_BRICK_STAIRS.get(),
                UOBlocks.ICE_BRICK_STAIRS.get()
        );
    }
}
