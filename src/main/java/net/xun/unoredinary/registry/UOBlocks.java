package net.xun.unoredinary.registry;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.block.GlaciumBlock;

import java.util.function.Supplier;

public class UOBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(UnOredinary.MOD_ID);

    // Ores
    public static final DeferredBlock<Block> CRYIC_ORE = register("cryic_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F)
            )
    );

    public static final DeferredBlock<Block> DEEPSLATE_CRYIC_ORE = register("deepslate_cryic_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(2, 4),
                    BlockBehaviour.Properties.ofFullCopy(UOBlocks.CRYIC_ORE.get())
                            .mapColor(MapColor.DEEPSLATE)
                            .strength(4.5F, 3.0F)
                            .sound(SoundType.DEEPSLATE)
            )
    );

    public static final DeferredBlock<Block> SAPPHIRE_ORE = register("sapphire_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(3, 8),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F)
            )
    );

    public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(3, 8),
                    BlockBehaviour.Properties.ofFullCopy(UOBlocks.SAPPHIRE_ORE.get())
                            .mapColor(MapColor.DEEPSLATE)
                            .strength(4.5F, 3.0F)
                            .sound(SoundType.DEEPSLATE)
            )
    );

    public static final DeferredBlock<Block> GLACIUM_ORE = register("glacium_ore",
            () -> new GlaciumBlock(
                    UniformInt.of(4, 9),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.ICE)
                            .instrument(NoteBlockInstrument.FLUTE)
                            .requiresCorrectToolForDrops()
                            .strength(10.0F, 1000.0F)
                            .friction(0.98F)
                            .sound(UOSoundTypes.GLACIUM),
                    true
            )
    );

    public static final DeferredBlock<Block> PRIMAL_GLACIUM_ORE = register("primal_glacium_ore",
            () -> new GlaciumBlock(
                    UniformInt.of(4, 10),
                    BlockBehaviour.Properties.ofFullCopy(UOBlocks.GLACIUM_ORE.get())
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .strength(20.0F, 1000.0F),
                    true
            )
    );

    // Storage Blocks
    public static final DeferredBlock<Block> CRYIC_BLOCK = register("cryic_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.GLOW_LICHEN)
                            .instrument(NoteBlockInstrument.CHIME)
                            .requiresCorrectToolForDrops()
                            .strength(5.0F, 6.0F)
                            .sound(SoundType.METAL)
            )
    );

    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = register("sapphire_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .instrument(NoteBlockInstrument.XYLOPHONE)
                            .requiresCorrectToolForDrops()
                            .strength(5.0F, 6.0F)
                            .sound(SoundType.METAL)
            )
    );

    public static final DeferredBlock<Block> GLACIUM_BLOCK = register("glacium_block",
            () -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(UOBlocks.PRIMAL_GLACIUM_ORE.get())
                            .strength(30.0F, 1000.0F)
                            .sound(UOSoundTypes.GLACIUM)
            )
    );

    public static final DeferredBlock<Block> FROSTSTEEL_BLOCK = register("froststeel_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.ICE)
                            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                            .requiresCorrectToolForDrops()
                            .strength(5.0F, 6.0F)
                            .sound(SoundType.METAL)
            )
    );

    public static final DeferredBlock<Block> GLACIALITE_BLOCK = register("glacialite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .instrument(NoteBlockInstrument.HARP)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            )
    );

    // TODO: Change the instrument to frost zombie
    public static final DeferredBlock<Block> FROST_ZOMBIE_HEAD = register("frost_zombie_head",
            () -> new SkullBlock(SkullBlock.Types.ZOMBIE, BlockBehaviour.Properties.of()
                    .instrument(NoteBlockInstrument.ZOMBIE)
                    .strength(1.0F)
                    .pushReaction(PushReaction.DESTROY)
            )
    );

    public static final DeferredBlock<Block> FROST_ZOMBIE_WALL_HEAD = register("frost_zombie_wall_head",
            () -> new WallSkullBlock(SkullBlock.Types.ZOMBIE, BlockBehaviour.Properties.ofFullCopy(UOBlocks.FROST_ZOMBIE_HEAD.get())
                    .lootFrom(UOBlocks.FROST_ZOMBIE_HEAD)
            )
    );

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> supplier) {
        DeferredBlock<T> registered = BLOCKS.register(name, supplier);
        registerBlockItem(name, registered);

        return registered;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> deferredBlock) {
        UOItems.ITEMS.register(name, () -> new BlockItem(deferredBlock.get(), new Item.Properties()));
    }
}
