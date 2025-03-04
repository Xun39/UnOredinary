package xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xun.unoredinary.data.provider.ModBlockStateProvider;
import xun.unoredinary.registry.ModBlocks;

public class ModBlockStates extends ModBlockStateProvider {

    public ModBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.HEMOCRYLIC_BLOCK);
        blockWithItem(ModBlocks.HEMOCRYLIC_ORE);
        blockWithItem(ModBlocks.FROSTEEL_BLOCK);

        blockWithItem(ModBlocks.CRYOSTONE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_CRYOSTONE_ORE);
        blockWithItem(ModBlocks.CRYOSTONE_BLOCK);

        blockWithItem(ModBlocks.LUMINITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_LUMINITE_ORE);
        blockWithItem(ModBlocks.LUMINITE_BLOCK);
        blockWithItem(ModBlocks.LUMINTHIUM_BLOCK);

        blockWithItem(ModBlocks.RUBY_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockWithItem(ModBlocks.RUBY_BLOCK);

        blockWithItem(ModBlocks.SOLARITE_ORE);

        blockWithItem(ModBlocks.FROSTBOUND_STONE);
        stairBlockWithItem(ModBlocks.FROSTBOUND_STONE_STAIRS, ModBlocks.FROSTBOUND_STONE);
        slabBlockWithItem(ModBlocks.FROSTBOUND_STONE_SLAB, ModBlocks.FROSTBOUND_STONE);

        blockWithItem(ModBlocks.FROSTBOUND_COBBLESTONE);
        stairBlockWithItem(ModBlocks.FROSTBOUND_COBBLESTONE_STAIRS, ModBlocks.FROSTBOUND_COBBLESTONE);
        slabBlockWithItem(ModBlocks.FROSTBOUND_COBBLESTONE_SLAB, ModBlocks.FROSTBOUND_COBBLESTONE);
        wallBlock(ModBlocks.FROSTBOUND_COBBLESTONE_WALL, ModBlocks.FROSTBOUND_COBBLESTONE);

        blockWithItem(ModBlocks.CRYOBOUND_COBBLESTONE);
        stairBlockWithItem(ModBlocks.CRYOBOUND_COBBLESTONE_STAIRS, ModBlocks.CRYOBOUND_COBBLESTONE);
        slabBlockWithItem(ModBlocks.CRYOBOUND_COBBLESTONE_SLAB, ModBlocks.CRYOBOUND_COBBLESTONE);
        wallBlock(ModBlocks.CRYOBOUND_COBBLESTONE_WALL, ModBlocks.CRYOBOUND_COBBLESTONE);

        blockWithItem(ModBlocks.FROSTBOUND_STONE_BRICKS);
        stairBlockWithItem(ModBlocks.FROSTBOUND_STONE_BRICK_STAIRS, ModBlocks.FROSTBOUND_STONE_BRICKS);
        slabBlockWithItem(ModBlocks.FROSTBOUND_STONE_BRICK_SLAB, ModBlocks.FROSTBOUND_STONE_BRICKS);
        wallBlock(ModBlocks.FROSTBOUND_STONE_BRICK_WALL, ModBlocks.FROSTBOUND_STONE_BRICKS);

        blockWithItem(ModBlocks.ICE_BRICKS);
        stairBlockWithItem(ModBlocks.ICE_BRICK_STAIRS, ModBlocks.ICE_BRICKS);
        slabBlockWithItem(ModBlocks.ICE_BRICK_SLAB, ModBlocks.ICE_BRICKS);
        wallBlock(ModBlocks.ICE_BRICK_WALL, ModBlocks.ICE_BRICKS);

        torchBlock(ModBlocks.LUMINITE_TORCH);
        wallTorchBlock(ModBlocks.LUMINITE_WALL_TORCH.get(), ModBlocks.LUMINITE_TORCH);

        torchBlockWithLitProperty(ModBlocks.CRYOSTONE_TORCH);
        wallTorchBlockWithLitProperty(ModBlocks.CRYOSTONE_WALL_TORCH.get(), ModBlocks.CRYOSTONE_TORCH);
    }
}
