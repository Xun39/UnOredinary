package net.xun.unoredinary.registry;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.block.TransenchantingTableBlock;
import net.xun.unoredinary.block.GlaciumBlock;

import java.util.function.Supplier;

public class UOBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(UnOredinary.MOD_ID);

    // Entity Blocks
    public static final DeferredBlock<Block> TRANSENCHANTING_TABLE = register("transenchanting_table",
            () -> new TransenchantingTableBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_GREEN)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .lightLevel(state -> 7)
                            .strength(5.0F, 1200.0F)
            )
    );

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

    public static final DeferredBlock<Block> NETHER_RUBY_ORE = register("nether_ruby_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(3, 7),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.NETHER)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F)
                            .sound(SoundType.NETHER_ORE)
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
                            .sound(UOSoundTypes.GLACIUM)
            )
    );
    public static final DeferredBlock<Block> PRIMAL_GLACIUM_ORE = register("primal_glacium_ore",
            () -> new GlaciumBlock(
                    UniformInt.of(4, 10),
                    BlockBehaviour.Properties.ofFullCopy(UOBlocks.GLACIUM_ORE.get())
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .strength(20.0F, 1000.0F)
            )
    );

    public static final DeferredBlock<Block> LUMINITE_ORE = register("luminite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(3, 6),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .lightLevel(state -> 8)
                            .strength(3.0F, 3.0F)
                            .emissiveRendering((state, level, pos) -> true)
            )
    );
    public static final DeferredBlock<Block> DEEPSLATE_LUMINITE_ORE = register("deepslate_luminite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(3, 6),
                    BlockBehaviour.Properties.ofFullCopy(UOBlocks.LUMINITE_ORE.get())
                            .mapColor(MapColor.DEEPSLATE)
                            .strength(4.5F, 3.0F)
                            .sound(SoundType.DEEPSLATE)
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
    public static final DeferredBlock<Block> RUBY_BLOCK = register("ruby_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_RED)
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
    public static final DeferredBlock<Block> LUMINITE_BLOCK = register("luminite_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_ORANGE)
                            .instrument(NoteBlockInstrument.BELL)
                            .requiresCorrectToolForDrops()
                            .strength(5.0F, 6.0F)
                            .sound(SoundType.METAL)
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
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLUE)
                            .instrument(NoteBlockInstrument.HARP)
                            .requiresCorrectToolForDrops()
                            .strength(5.0F, 6.0F)
                            .sound(SoundType.METAL)
            )
    );
    public static final DeferredBlock<Block> LUMINIUM_BLOCK = register("luminium_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_ORANGE)
                            .instrument(NoteBlockInstrument.FLUTE)
                            .requiresCorrectToolForDrops()
                            .strength(5.0F, 6.0F)
                            .sound(SoundType.METAL)
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
