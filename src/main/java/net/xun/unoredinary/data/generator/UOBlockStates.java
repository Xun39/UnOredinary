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

        blockWithItem(UOBlocks.GLACIUM_ORE);
        blockWithItem(UOBlocks.PRIMAL_GLACIUM_ORE);

        blockWithItem(UOBlocks.GLACIUM_BLOCK);
    }
}
