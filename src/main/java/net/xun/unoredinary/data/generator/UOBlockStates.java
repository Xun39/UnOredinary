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
        blockWithItem(UOBlocks.GLACIUM_ORE);
        blockWithItem(UOBlocks.GLACIAL_CORE);
        blockWithItem(UOBlocks.GLACIUM_BLOCK);
    }
}
