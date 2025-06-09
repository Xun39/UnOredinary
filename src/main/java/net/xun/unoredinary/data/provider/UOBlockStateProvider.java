package net.xun.unoredinary.data.provider;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.xun.unoredinary.UnOredinary;

public abstract class UOBlockStateProvider extends BlockStateProvider {
    public UOBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, UnOredinary.MOD_ID, exFileHelper);
    }

    protected void blockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
