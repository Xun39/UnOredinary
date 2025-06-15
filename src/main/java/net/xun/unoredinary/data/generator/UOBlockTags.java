package net.xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.util.UOTags;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UOBlockTags extends BlockTagsProvider {
    public UOBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, UnOredinary.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                UOBlocks.CRYIC_ORE.get(),
                UOBlocks.DEEPSLATE_CRYIC_ORE.get(),

                UOBlocks.GLACIUM_ORE.get(),
                UOBlocks.PRIMAL_GLACIUM_ORE.get(),

                UOBlocks.CRYIC_BLOCK.get(),
                UOBlocks.GLACIUM_BLOCK.get(),
                UOBlocks.FROSTSTEEL_BLOCK.get(),
                UOBlocks.CRYOSTEEL_BLOCK.get()
        );

        tag(BlockTags.NEEDS_STONE_TOOL).add(
                UOBlocks.CRYIC_ORE.get(),
                UOBlocks.DEEPSLATE_CRYIC_ORE.get()
        );

        tag(BlockTags.NEEDS_IRON_TOOL).add(
                UOBlocks.FROSTSTEEL_BLOCK.get()
        );

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                UOBlocks.GLACIUM_ORE.get(),
                UOBlocks.PRIMAL_GLACIUM_ORE.get(),

                UOBlocks.GLACIUM_BLOCK.get(),
                UOBlocks.CRYOSTEEL_BLOCK.get()
        );

        // cTags: Ores
        addToTags(
                Set.of(Tags.Blocks.ORES, UOTags.Blocks.ORES_FROSTBOUND),
                Set.of(UOBlocks.CRYIC_ORE.get(), UOBlocks.DEEPSLATE_CRYIC_ORE.get())
        );

        addToTags(
                Set.of(Tags.Blocks.ORES, UOTags.Blocks.ORES_GLACIUM),
                Set.of(UOBlocks.GLACIUM_ORE.get(), UOBlocks.PRIMAL_GLACIUM_ORE.get()));

        // cTags: Ores in Ground
        addToTags(
                Set.of(Tags.Blocks.ORES_IN_GROUND_STONE),
                Set.of(UOBlocks.CRYIC_ORE.get())
        );

        addToTags(
                Set.of(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE),
                Set.of(UOBlocks.DEEPSLATE_CRYIC_ORE.get())
        );

        // cTags: Storage Blocks
        addToTags(
                Set.of(Tags.Blocks.STORAGE_BLOCKS, UOTags.Blocks.STORAGE_BLOCKS_CRYIC),
                Set.of(UOBlocks.CRYIC_BLOCK.get())
        );

        addToTags(
                Set.of(Tags.Blocks.STORAGE_BLOCKS, UOTags.Blocks.STORAGE_BLOCKS_GLACIUM),
                Set.of(UOBlocks.GLACIUM_BLOCK.get())
        );

        addToTags(
                Set.of(Tags.Blocks.STORAGE_BLOCKS, UOTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL),
                Set.of(UOBlocks.FROSTSTEEL_BLOCK.get())
        );

        addToTags(
                Set.of(Tags.Blocks.STORAGE_BLOCKS, UOTags.Blocks.STORAGE_BLOCKS_CRYOSTEEL),
                Set.of(UOBlocks.CRYOSTEEL_BLOCK.get())
        );
    }

    private void addToTags(Collection<TagKey<Block>> tags, Collection<Block> entries) {
        for (TagKey<Block> key : tags) {
            tag(key).add(entries.toArray(Block[]::new));
        }
    }
}