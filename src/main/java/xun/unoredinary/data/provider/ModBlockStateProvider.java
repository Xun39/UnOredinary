package xun.unoredinary.data.provider;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
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

    protected void torchBlock(DeferredBlock<?> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().torch(getBlockRegistryName(deferredBlock),
                UnOredinary.modLoc("block/" + getBlockRegistryName(deferredBlock))).renderType("cutout"));
    }

    public void wallTorchBlock(DeferredBlock<WallTorchBlock> torchBlockDeferredBlock, DeferredBlock<?> textureBlock) {
        getVariantBuilder(torchBlockDeferredBlock.get())
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(WallTorchBlock.FACING);
                    int yRot = (int) facing.getClockWise().toYRot();

                    if (facing == Direction.SOUTH) {
                        yRot += 360;
                    }

                    yRot %= 360;

                    return ConfiguredModel.builder()
                            .modelFile(models().torchWall(getBlockRegistryName(torchBlockDeferredBlock),
                                    UnOredinary.modLoc("block/" + getBlockRegistryName(textureBlock))).renderType("cutout"))
                            .rotationY(yRot)
                            .build();
                });
    }

    public ResourceLocation resourceBlock(String path) {
        return UnOredinary.modLoc("block/" + path );
    }

    protected static String getBlockRegistryName(DeferredBlock<?> deferredBlock) {
        return deferredBlock.getId().getPath();
    }
}
