package net.xun.unoredinary.data.generator;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.xun.unoredinary.data.provider.UOBlockLootProvider;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.registry.UOItems;

public class UOBlockLoot extends UOBlockLootProvider {
    public UOBlockLoot(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {
        /* ------------------------------ ORES ------------------------------ */
        // Cryic ores
        multipleDrops(UOBlocks.CRYIC_ORE.get(), UOItems.CRYIC_POWDER, 3.0F, 5.0F);
        multipleDrops(UOBlocks.DEEPSLATE_CRYIC_ORE.get(), UOItems.CRYIC_POWDER, 3.0F, 5.0F);

        // Sapphire ores
        dropOtherWithoutSilkTouch(UOBlocks.SAPPHIRE_ORE.get(), UOItems.SAPPHIRE);
        dropOtherWithoutSilkTouch(UOBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), UOItems.SAPPHIRE);

        // Glacium ores
        multipleDrops(UOBlocks.GLACIUM_ORE.get(), UOItems.GLACIUM_SHARD, 3.0F, 4.0F);
        dropOtherWithoutSilkTouch(UOBlocks.PRIMAL_GLACIUM_ORE.get(), UOItems.GLACIUM_CRYSTAL);

        /* ------------------------------ STORAGE BLOCKS ------------------------------ */
        dropSelf(UOBlocks.CRYIC_BLOCK.get());
        dropSelf(UOBlocks.SAPPHIRE_BLOCK.get());
        dropSelf(UOBlocks.GLACIUM_BLOCK.get());
        dropSelf(UOBlocks.FROSTSTEEL_BLOCK.get());
        dropSelf(UOBlocks.GLACIALITE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return UOBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
