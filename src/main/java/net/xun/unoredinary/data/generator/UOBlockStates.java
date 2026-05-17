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

        existingBlockWithItem(UOBlocks.TRANSENCHANTING_TABLE);

        blockWithItem(UOBlocks.CRYIC_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_CRYIC_ORE);

        blockWithItem(UOBlocks.SAPPHIRE_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_SAPPHIRE_ORE);

        blockWithItem(UOBlocks.NETHER_RUBY_ORE);

        blockWithItem(UOBlocks.GLACIUM_ORE);
        blockWithItem(UOBlocks.PRIMAL_GLACIUM_ORE);

        blockWithItem(UOBlocks.LUMINITE_ORE);
        blockWithItem(UOBlocks.DEEPSLATE_LUMINITE_ORE);

        blockWithItem(UOBlocks.CRYIC_BLOCK);
        blockWithItem(UOBlocks.SAPPHIRE_BLOCK);
        blockWithItem(UOBlocks.RUBY_BLOCK);
        blockWithItem(UOBlocks.GLACIUM_BLOCK);
        blockWithItem(UOBlocks.LUMINITE_BLOCK);
        blockWithItem(UOBlocks.FROSTSTEEL_BLOCK);
        blockWithItem(UOBlocks.GLACIALITE_BLOCK);
        blockWithItem(UOBlocks.LUMINIUM_BLOCK);

        // Building Blocks
        blockWithItem(UOBlocks.POLAR_STONE);
        stairBlockWithItem(UOBlocks.POLAR_STONE_STAIRS, UOBlocks.POLAR_STONE);
        slabBlockWithItem(UOBlocks.POLAR_STONE_SLAB, UOBlocks.POLAR_STONE);

        blockWithItem(UOBlocks.POLAR_COBBLESTONE);
        stairBlockWithItem(UOBlocks.POLAR_COBBLESTONE_STAIRS, UOBlocks.POLAR_COBBLESTONE);
        slabBlockWithItem(UOBlocks.POLAR_COBBLESTONE_SLAB, UOBlocks.POLAR_COBBLESTONE);
        wallBlockWithItem(UOBlocks.POLAR_COBBLESTONE_WALL, UOBlocks.POLAR_COBBLESTONE);

        blockWithItem(UOBlocks.POLAR_STONE_BRICKS);
        stairBlockWithItem(UOBlocks.POLAR_STONE_BRICKS_STAIRS, UOBlocks.POLAR_STONE_BRICKS);
        slabBlockWithItem(UOBlocks.POLAR_STONE_BRICKS_SLAB, UOBlocks.POLAR_STONE_BRICKS);
        wallBlockWithItem(UOBlocks.POLAR_STONE_BRICKS_WALL, UOBlocks.POLAR_STONE_BRICKS);

        doorBlockWithItem(UOBlocks.ICE_DOOR, "cutout");
    }
}
