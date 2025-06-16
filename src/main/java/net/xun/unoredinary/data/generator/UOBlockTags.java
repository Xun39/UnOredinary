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

        /* ------------------------------ MINEABLE ------------------------------ */
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                UOBlocks.CRYIC_ORE.get(),
                UOBlocks.DEEPSLATE_CRYIC_ORE.get(),

                UOBlocks.SAPPHIRE_ORE.get(),
                UOBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),

                UOBlocks.GLACIUM_ORE.get(),
                UOBlocks.PRIMAL_GLACIUM_ORE.get(),

                UOBlocks.CRYIC_BLOCK.get(),
                UOBlocks.SAPPHIRE_BLOCK.get(),
                UOBlocks.GLACIUM_BLOCK.get(),
                UOBlocks.FROSTSTEEL_BLOCK.get(),
                UOBlocks.GLACIALITE_BLOCK.get()
        );

        tag(BlockTags.NEEDS_STONE_TOOL).add(
                UOBlocks.CRYIC_ORE.get(),
                UOBlocks.DEEPSLATE_SAPPHIRE_ORE.get()
        );

        tag(BlockTags.NEEDS_IRON_TOOL).add(
                UOBlocks.SAPPHIRE_ORE.get(),
                UOBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),

                UOBlocks.SAPPHIRE_BLOCK.get()
        );

        tag(UOTags.Blocks.NEEDS_FROSTSTEEL_TOOL).add(
                UOBlocks.GLACIUM_ORE.get(),
                UOBlocks.PRIMAL_GLACIUM_ORE.get(),

                UOBlocks.GLACIUM_BLOCK.get(),

                UOBlocks.FROSTSTEEL_BLOCK.get(),
                UOBlocks.GLACIALITE_BLOCK.get()
        );

        // Froststeel
        tag(UOTags.Blocks.INCORRECT_FOR_FROSTSTEEL_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL).addTag(UOTags.Blocks.NEEDS_FROSTSTEEL_TOOL);
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).addTag(UOTags.Blocks.NEEDS_FROSTSTEEL_TOOL);
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL).addTag(UOTags.Blocks.NEEDS_FROSTSTEEL_TOOL);
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL).addTag(UOTags.Blocks.NEEDS_FROSTSTEEL_TOOL);

        /* ------------------------------ COMMON ------------------------------ */

        // cTags: Ores
        addToTags(
                Set.of(Tags.Blocks.ORES, UOTags.Blocks.ORES_CRYIC),
                Set.of(UOBlocks.CRYIC_ORE.get(), UOBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
        );

        addToTags(
                Set.of(Tags.Blocks.ORES, UOTags.Blocks.ORES_SAPPHIRE),
                Set.of(UOBlocks.SAPPHIRE_ORE.get(), UOBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
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
                Set.of(UOBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
        );

        // cTags: Storage Blocks
        addToTags(
                Set.of(Tags.Blocks.STORAGE_BLOCKS, UOTags.Blocks.STORAGE_BLOCKS_CRYIC),
                Set.of(UOBlocks.CRYIC_BLOCK.get())
        );

        addToTags(
                Set.of(Tags.Blocks.STORAGE_BLOCKS, UOTags.Blocks.STORAGE_BLOCKS_SAPPHIRE),
                Set.of(UOBlocks.SAPPHIRE_BLOCK.get())
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
                Set.of(Tags.Blocks.STORAGE_BLOCKS, UOTags.Blocks.STORAGE_BLOCKS_GLACIALITE),
                Set.of(UOBlocks.GLACIALITE_BLOCK.get())
        );
    }

    private void addToTags(Collection<TagKey<Block>> tags, Collection<Block> entries) {
        for (TagKey<Block> key : tags) {
            tag(key).add(entries.toArray(Block[]::new));
        }
    }
}