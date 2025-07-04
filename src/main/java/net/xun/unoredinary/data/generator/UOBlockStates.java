package net.xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xun.unoredinary.data.provider.UOBlockStateProvider;
import net.xun.unoredinary.registry.UOBlocks;

public class UOBlockStates extends UOBlockStateProvider {
    public UOBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(UOBlocks.CRYIC_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_CRYIC_ORE);

        blockWithItem(UOBlocks.SAPPHIRE_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_SAPPHIRE_ORE);

        blockWithItem(UOBlocks.GLACIUM_ORE);
        blockWithItem(UOBlocks.PRIMAL_GLACIUM_ORE);

        blockWithItem(UOBlocks.LUMINITE_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_LUMINITE_ORE);

        blockWithItem(UOBlocks.CRYIC_BLOCK);
        blockWithItem(UOBlocks.SAPPHIRE_BLOCK);
        blockWithItem(UOBlocks.GLACIUM_BLOCK);
        blockWithItem(UOBlocks.LUMINITE_BLOCK);
        blockWithItem(UOBlocks.FROSTSTEEL_BLOCK);
        blockWithItem(UOBlocks.GLACIALITE_BLOCK);
        blockWithItem(UOBlocks.LUMINIUM_BLOCK);
    }
}
