package xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xun.unoredinary.data.provider.UOBlockStateProvider;
import xun.unoredinary.registry.UOBlocks;

public class UOBlockStates extends UOBlockStateProvider {

    public UOBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(UOBlocks.HEMOCRYLIC_BLOCK);
        blockWithItem(UOBlocks.HEMOCRYLIC_ORE);
        blockWithItem(UOBlocks.FROSTEEL_BLOCK);

        blockWithItem(UOBlocks.CRYOSTONE_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_CRYOSTONE_ORE);
        blockWithItem(UOBlocks.CRYOSTONE_BLOCK);

        blockWithItem(UOBlocks.LUMINITE_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_LUMINITE_ORE);
        blockWithItem(UOBlocks.LUMINITE_BLOCK);
        blockWithItem(UOBlocks.LUMINTHIUM_BLOCK);

        blockWithItem(UOBlocks.RUBY_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_RUBY_ORE);
        blockWithItem(UOBlocks.RUBY_BLOCK);

        blockWithItem(UOBlocks.SOLARITE_ORE);

        blockWithItem(UOBlocks.FROSTBOUND_STONE);
        stairBlockWithItem(UOBlocks.FROSTBOUND_STONE_STAIRS, UOBlocks.FROSTBOUND_STONE);
        slabBlockWithItem(UOBlocks.FROSTBOUND_STONE_SLAB, UOBlocks.FROSTBOUND_STONE);

        blockWithItem(UOBlocks.FROSTBOUND_COBBLESTONE);
        stairBlockWithItem(UOBlocks.FROSTBOUND_COBBLESTONE_STAIRS, UOBlocks.FROSTBOUND_COBBLESTONE);
        slabBlockWithItem(UOBlocks.FROSTBOUND_COBBLESTONE_SLAB, UOBlocks.FROSTBOUND_COBBLESTONE);
        wallBlock(UOBlocks.FROSTBOUND_COBBLESTONE_WALL, UOBlocks.FROSTBOUND_COBBLESTONE);

        blockWithItem(UOBlocks.CRYOBOUND_COBBLESTONE);
        stairBlockWithItem(UOBlocks.CRYOBOUND_COBBLESTONE_STAIRS, UOBlocks.CRYOBOUND_COBBLESTONE);
        slabBlockWithItem(UOBlocks.CRYOBOUND_COBBLESTONE_SLAB, UOBlocks.CRYOBOUND_COBBLESTONE);
        wallBlock(UOBlocks.CRYOBOUND_COBBLESTONE_WALL, UOBlocks.CRYOBOUND_COBBLESTONE);

        blockWithItem(UOBlocks.FROSTBOUND_STONE_BRICKS);
        stairBlockWithItem(UOBlocks.FROSTBOUND_STONE_BRICK_STAIRS, UOBlocks.FROSTBOUND_STONE_BRICKS);
        slabBlockWithItem(UOBlocks.FROSTBOUND_STONE_BRICK_SLAB, UOBlocks.FROSTBOUND_STONE_BRICKS);
        wallBlock(UOBlocks.FROSTBOUND_STONE_BRICK_WALL, UOBlocks.FROSTBOUND_STONE_BRICKS);

        blockWithItem(UOBlocks.ICE_BRICKS);
        stairBlockWithItem(UOBlocks.ICE_BRICK_STAIRS, UOBlocks.ICE_BRICKS);
        slabBlockWithItem(UOBlocks.ICE_BRICK_SLAB, UOBlocks.ICE_BRICKS);
        wallBlock(UOBlocks.ICE_BRICK_WALL, UOBlocks.ICE_BRICKS);

        torchBlock(UOBlocks.LUMINITE_TORCH);
        wallTorchBlock(UOBlocks.LUMINITE_WALL_TORCH.get(), UOBlocks.LUMINITE_TORCH);

        torchBlockWithLitProperty(UOBlocks.CRYOSTONE_TORCH);
        wallTorchBlockWithLitProperty(UOBlocks.CRYOSTONE_WALL_TORCH.get(), UOBlocks.CRYOSTONE_TORCH);
    }
}
