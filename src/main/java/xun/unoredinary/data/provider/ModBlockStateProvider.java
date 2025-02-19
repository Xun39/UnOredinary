package xun.unoredinary.data.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import xun.unoredinary.UnOredinary;

public abstract class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, UnOredinary.MOD_ID, exFileHelper);
    }

    protected void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    protected void stairBlockWithItem(DeferredBlock<StairBlock> deferredBlock, DeferredBlock<?> textureBlock) {
        stairsBlock(deferredBlock.get(), blockTexture(textureBlock.get()));
        blockItem(deferredBlock);
    }

    protected void slabBlockWithItem(DeferredBlock<SlabBlock> deferredBlock, DeferredBlock<?> textureBlock) {
        slabBlock(deferredBlock.get(), blockTexture(textureBlock.get()), blockTexture(textureBlock.get()));
        blockItem(deferredBlock);
    }

    protected void wallBlock(DeferredBlock<WallBlock> deferredBlock, DeferredBlock<?> textureBlock) {
        wallBlock(deferredBlock.get(), blockTexture(textureBlock.get()));
    }

    protected void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(UnOredinary.modLoc("block/" + getBlockRegistryName(deferredBlock))));
    }

    protected void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("block/" + getBlockRegistryName(deferredBlock) + appendix));
    }

    public ResourceLocation resourceBlock(String path) {
        return UnOredinary.modLoc("block/" + path );
    }

    protected static String getBlockRegistryName(DeferredBlock<?> deferredBlock) {
        return deferredBlock.getId().getPath();
    }
}
