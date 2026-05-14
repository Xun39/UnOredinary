package net.xun.unoredinary.data.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.UnOredinary;

public abstract class UOBlockStateProvider extends BlockStateProvider {
    public UOBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, UnOredinary.MOD_ID, exFileHelper);
    }

    protected void blockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    protected void stairBlockWithItem(DeferredBlock<StairBlock> block, DeferredBlock<?> textureBlock) {
        stairsBlock(block.get(), blockTexture(textureBlock.get()));
        uncheckedBlockItem(block);
    }

    protected void slabBlockWithItem(DeferredBlock<SlabBlock> block, DeferredBlock<?> textureBlock) {
        slabBlock(block.get(), blockTexture(textureBlock.get()), blockTexture(textureBlock.get()));
        uncheckedBlockItem(block);
    }

    protected void wallBlockWithItem(DeferredBlock<WallBlock> block, DeferredBlock<?> textureBlock) {
        wallBlock(block.get(), blockTexture(textureBlock.get()));

        ModelFile inventoryModel = models()
                .wallInventory(
                        block.getKey().location().getPath() + "_inventory",
                        blockTexture(textureBlock.get())
                );

        simpleBlockItem(block.get(), inventoryModel);
    }

    protected void existingBlockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(
                block.get(),
                models().getExistingFile(CommonUtils.modLoc(block.getKey().location().getPath()))
        );
    }

    protected void uncheckedBlockItem(DeferredBlock<?> block) {
        simpleBlockItem(
                block.get(),
                new ModelFile.UncheckedModelFile(CommonUtils.modLoc("block/" + block.getKey().location().getPath()))
        );
    }
}
