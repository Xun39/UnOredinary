package xun.unoredinary.data.provider;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
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
                resourceBlock(getBlockRegistryName(deferredBlock))).renderType("cutout"));
    }

    protected void wallTorchBlock(Block torchBlock, DeferredBlock<?> textureBlock) {
        getVariantBuilder(torchBlock)
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(WallTorchBlock.FACING);
                    int yRot = (int) facing.getClockWise().toYRot();

                    if (facing == Direction.SOUTH) {
                        yRot += 360;
                    }

                    yRot %= 360;

                    return ConfiguredModel.builder()
                            .modelFile(models().torchWall(getRegistryName(torchBlock),
                                    resourceBlock(getBlockRegistryName(textureBlock))).renderType("cutout"))
                            .rotationY(yRot)
                            .build();
                });
    }

    protected void torchBlockWithLitProperty(DeferredBlock<?> torchblock) {
        getVariantBuilder(torchblock.get())
                .forAllStatesExcept(state -> {
                    boolean lit = state.getValue(RedstoneWallTorchBlock.LIT);

                    return ConfiguredModel.builder()
                            .modelFile(lit == true ? models().torch(getRegistryName(torchblock), resourceBlock(getBlockRegistryName(torchblock))).renderType("cutout")
                                    : models().torch(getRegistryName(torchblock) + "_off", resourceBlock(getBlockRegistryName(torchblock) + "_off")))
                            .build();
                });
    }

    protected void wallTorchBlockWithLitProperty(Block torchBlock, DeferredBlock<?> textureBlock) {
        getVariantBuilder(torchBlock)
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(RedstoneWallTorchBlock.FACING);
                    boolean lit = state.getValue(RedstoneWallTorchBlock.LIT);

                    int yRot = (int) facing.getClockWise().toYRot();

                    if (facing == Direction.SOUTH) {
                        yRot += 360;
                    }

                    yRot %= 360;

                    return ConfiguredModel.builder()
                            .modelFile(lit == true ? models().torchWall(getRegistryName(torchBlock), resourceBlock(getBlockRegistryName(textureBlock))).renderType("cutout")
                            : models().torchWall(getRegistryName(torchBlock) + "_off", resourceBlock(getBlockRegistryName(textureBlock) + "_off")).renderType("cutout"))
                            .rotationY(yRot)
                            .build();
                });
    }

    // TODO: luminite lantern.
    protected void lanternBlock() {}

    public ResourceLocation resourceBlock(String path) {
        return UnOredinary.modLoc("block/" + path );
    }

    protected static String getRegistryName(ItemLike itemLike) {
        return BuiltInRegistries.ITEM.getKey(itemLike.asItem()).getPath();
    }

    protected static String getBlockRegistryName(DeferredBlock<?> deferredBlock) {
        return deferredBlock.getId().getPath();
    }
}
