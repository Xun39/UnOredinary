package net.xun.unoredinary.data.generator;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.xun.unoredinary.data.provider.UOBlockLootProvider;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.registry.UOItems;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UOLootTables extends LootTableProvider {

    public UOLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(
                output,
                Collections.emptySet(),
                List.of(
                    new SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)
                ),
                registries
        );
    }

    public static class BlockLootTables extends UOBlockLootProvider {

        public BlockLootTables(HolderLookup.Provider registries) {
            super(registries);
        }

        @Override
        protected void generate() {

            /* ------------------------------ ORES ------------------------------ */
            // Cryic ores
            multipleDrops(UOBlocks.CRYIC_ORE.get(), UOItems.CRYIC_POWDER, 3.0F, 5.0F);
            multipleDrops(UOBlocks.DEEPSLATE_CRYIC_ORE.get(), UOItems.CRYIC_POWDER, 3.0F, 5.0F);

            // Glacium ores
            multipleDrops(UOBlocks.GLACIUM_ORE.get(), UOItems.GLACIUM_SHARDS, 3.0F, 4.0F);
            dropOtherWithoutSilkTouch(UOBlocks.PRIMAL_GLACIUM_ORE.get(), UOItems.GLACIUM_CRYSTAL);

            /* ------------------------------ STORAGE BLOCKS ------------------------------ */
            dropSelf(UOBlocks.CRYIC_BLOCK.get());
            dropSelf(UOBlocks.GLACIUM_BLOCK.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return UOBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        }
    }
}
