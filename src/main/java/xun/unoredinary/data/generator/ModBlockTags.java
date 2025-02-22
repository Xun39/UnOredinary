package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModBlocks;

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
