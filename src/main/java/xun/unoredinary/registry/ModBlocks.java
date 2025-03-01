package xun.unoredinary.registry;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.block.*;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { BLOCKS.register(eventBus); }

    // Hemocrylic
    public static final DeferredBlock<Block> HEMOCRYLIC_BLOCK = registerBlock("hemocrylic_block",
            () -> new HemocrylicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops(), false));
    public static final DeferredBlock<Block> HEMOCRYLIC_ORE = registerBlock("hemocrylic_ore",
            () -> new HemocrylicBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.HEMOCRYLIC_BLOCK.get())
                    .strength(3.0F, 2.0F)
                    .lightLevel((state) -> 2), true));

    public static final DeferredBlock<Block> FROSTEEL_BLOCK = registerBlock("frosteel_block",
            () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final DeferredBlock<Block> CRYOSTONE_ORE = registerBlock("cryostone_ore",
            () -> new CryoStoneOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_CRYOSTONE_ORE = registerBlock("deepslate_cryostone_ore",
            () -> new CryoStoneOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_REDSTONE_ORE)));
    public static final DeferredBlock<Block> CRYOSTONE_BLOCK = registerBlock("cryostone_block",
            () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final DeferredBlock<CryostoneTorchBlock> CRYOSTONE_TORCH = registerBlock("cryostone_torch",
            () -> new CryostoneTorchBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_TORCH)
                            .lightLevel(litBlockEmission(6))));

    public static final DeferredBlock<CryostoneWallTorchBlock> CRYOSTONE_WALL_TORCH = registerBlock("cryostone_wall_torch",
            () -> new CryostoneWallTorchBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WALL_TORCH)
                            .lootFrom(ModBlocks.CRYOSTONE_TORCH)
                            .lightLevel(litBlockEmission(6))));

    public static final DeferredBlock<CryostoneWireBlock> CRYOSTONE_WIRE = registerBlock("cryostone_wire",
            () -> new CryostoneWireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE)));

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


    // Luminite
    public static final DeferredBlock<Block> LUMINITE_ORE = registerBlock("luminite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                            .strength(4.5F, 3.0F)
                            .lightLevel((state) -> 9)));

    public static final DeferredBlock<Block> DEEPSLATE_LUMINITE_ORE = registerBlock("deepslate_luminite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .strength(4.5F, 3.0F)
                            .lightLevel((state) -> 9)));

    public static final DeferredBlock<Block> LUMINITE_BLOCK = registerBlock("luminite_block",
            () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F).sound(SoundType.AMETHYST)
                    .lightLevel((state) -> 9)));

    public static final DeferredBlock<Block> LUMINTHIUM_BLOCK = registerBlock("luminthium_block",
            () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F).sound(SoundType.METAL)
                    .lightLevel((state) -> 12)));

    public static final DeferredBlock<TorchBlock> LUMINITE_TORCH = registerBlock("luminite_torch",
            () -> new TorchBlock(
                    ParticleTypes.FLAME,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)
                            .lightLevel((state) -> 15)));

    public static final DeferredBlock<WallTorchBlock> LUMINITE_WALL_TORCH = registerBlock("luminite_wall_torch",
            () -> new WallTorchBlock(
                    ParticleTypes.FLAME,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH)
                            .lootFrom(ModBlocks.LUMINITE_TORCH)
                            .lightLevel((state) -> 15)));


    // Ruby
    public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(4, 8),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                            .strength(4.5F, 3.0F)));

    public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(4, 8),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .strength(4.5F, 3.0F)));

    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F).sound(SoundType.METAL)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
