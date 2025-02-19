package xun.unoredinary.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.block.CryoStoneOreBlock;
import xun.unoredinary.content.block.HemocrylicBlock;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { BLOCKS.register(eventBus); }

    public static final DeferredBlock<Block> HEMOCRYLIC_BLOCK = registerBlock("hemocrylic_block",
            () -> new HemocrylicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)
                    .strength(5.0F, 64.0F)
                    .requiresCorrectToolForDrops(), false));

    public static final DeferredBlock<Block> HEMOCRYLIC_ORE = registerBlock("hemocrylic_ore",
            () -> new HemocrylicBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.HEMOCRYLIC_BLOCK.get())
                    .strength(3.0F, 2.0F)
                    .lightLevel((state) -> 1), true));

    public static final DeferredBlock<Block> CRYOSTONE_ORE = registerBlock("cryostone_ore",
            () -> new CryoStoneOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_CRYOSTONE_ORE = registerBlock("deepslate_cryostone_ore",
            () -> new CryoStoneOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_REDSTONE_ORE)));

    public static final DeferredBlock<Block> FROSTBOUND_STONE = registerBlock("frostbound_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1.0F, 5.0F)));
    public static final DeferredBlock<StairBlock> FROSTBOUND_STONE_STAIRS = registerBlock("frostbound_stone_stairs",
            () -> new StairBlock(ModBlocks.FROSTBOUND_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)));
    public static final DeferredBlock<SlabBlock> FROSTBOUND_STONE_SLAB = registerBlock("frostbound_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));

    public static final DeferredBlock<Block> FROSTBOUND_STONE_BRICKS = registerBlock("frostbound_stone_bricks",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<StairBlock> FROSTBOUND_STONE_BRICK_STAIRS = registerBlock("frostbound_stone_brick_stairs",
            () -> new StairBlock(ModBlocks.FROSTBOUND_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)));
    public static final DeferredBlock<SlabBlock> FROSTBOUND_STONE_BRICK_SLAB = registerBlock("frostbound_stone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB)));
    public static final DeferredBlock<WallBlock> FROSTBOUND_STONE_BRICK_WALL = registerBlock("frostbound_stone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));

    public static final DeferredBlock<Block> FROSTBOUND_COBBLESTONE = registerBlock("frostbound_cobblestone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).strength(1.5F, 5.0F)));
    public static final DeferredBlock<StairBlock> FROSTBOUND_COBBLESTONE_STAIRS = registerBlock("frostbound_cobblestone_stairs",
            () -> new StairBlock(ModBlocks.FROSTBOUND_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS)));
    public static final DeferredBlock<SlabBlock> FROSTBOUND_COBBLESTONE_SLAB = registerBlock("frostbound_cobblestone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB)));
    public static final DeferredBlock<WallBlock> FROSTBOUND_COBBLESTONE_WALL = registerBlock("frostbound_cobblestone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)));

    public static final DeferredBlock<Block> CRYOBOUND_COBBLESTONE = registerBlock("cryobound_cobblestone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).strength(1.8F, 4.5F)));
    public static final DeferredBlock<StairBlock> CRYOBOUND_COBBLESTONE_STAIRS = registerBlock("cryobound_cobblestone_stairs",
            () -> new StairBlock(ModBlocks.CRYOBOUND_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS)));
    public static final DeferredBlock<SlabBlock> CRYOBOUND_COBBLESTONE_SLAB = registerBlock("cryobound_cobblestone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB)));
    public static final DeferredBlock<WallBlock> CRYOBOUND_COBBLESTONE_WALL = registerBlock("cryobound_cobblestone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)));

    public static final DeferredBlock<Block> ICE_BRICKS = registerBlock("ice_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final DeferredBlock<StairBlock> ICE_BRICK_STAIRS = registerBlock("ice_brick_stairs",
            () -> new StairBlock(ModBlocks.ICE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS)));
    public static final DeferredBlock<SlabBlock> ICE_BRICK_SLAB = registerBlock("ice_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB)));
    public static final DeferredBlock<WallBlock> ICE_BRICK_WALL = registerBlock("ice_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
