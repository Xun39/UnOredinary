package net.xun.unoredinary.data.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
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

    protected void blockWithItem(DeferredBlock<?> block, Block textureBlock) {
        simpleBlockWithItem(block.get(), cubeAll(textureBlock));
    }

    protected void existingBlockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(
                block.get(),
                models().getExistingFile(modLoc(block.getKey().location().getPath()))
        );
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

    protected void doorBlock(DeferredBlock<DoorBlock> block, String renderType) {
        doorBlockWithRenderType(
                block.get(),
                modLoc("block/" + block.getKey().location().getPath() + "_bottom"),
                modLoc("block/" + block.getKey().location().getPath() + "_top"),
                renderType
        );
    }

    protected void trapdoorBlockWithItem(DeferredBlock<TrapDoorBlock> block, String renderType) {
        String blockRegistryName = "block/" + block.getKey().location().getPath();

        trapdoorBlockWithRenderType(
                block.get(),
                modLoc("block/" + block.getKey().location().getPath()),
                true,
                renderType
        );

        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(modLoc(blockRegistryName + "_bottom")));
    }

    protected void buttonBlockWithItem(DeferredBlock<ButtonBlock> block, Block textureBlock) {
        buttonBlock(block.get(), blockTexture(textureBlock));

        ModelFile inventoryModel = models()
                .buttonInventory(
                        block.getKey().location().getPath() + "_inventory",
                        blockTexture(textureBlock)
                );

        simpleBlockItem(block.get(), inventoryModel);
    }

    protected void cauldronBlock(DeferredBlock<?> block, String contentTextureName) {
        simpleBlock(
                block.get(),
                models().withExistingParent(block.getKey().location().getPath(), mcLoc("block/template_cauldron_full"))
                        .texture("bottom", mcLoc("block/cauldron_bottom"))
                        .texture("content", modLoc("fluid/" + contentTextureName))
                        .texture("inside", mcLoc("block/cauldron_inner"))
                        .texture("particle", mcLoc("block/cauldron_side"))
                        .texture("side", mcLoc("block/cauldron_side"))
                        .texture("top", mcLoc("block/cauldron_top"))
        );
    }

    protected void fluidBlock(DeferredBlock<?> block, String textureName) {
        simpleBlock(
                block.get(),
                models().getBuilder(block.getKey().location().getPath()).texture("particle", modLoc("fluid/" + textureName))
        );
    }

    protected void uncheckedBlockItem(DeferredBlock<?> block) {
        simpleBlockItem(
                block.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/" + block.getKey().location().getPath()))
        );
    }
}
