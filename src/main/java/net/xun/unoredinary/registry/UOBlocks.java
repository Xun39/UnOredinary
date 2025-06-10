package net.xun.unoredinary.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.content.block.GlaciumBlock;

import java.util.function.Supplier;

public class UOBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(UnOredinary.MOD_ID);

    public static final DeferredBlock<Block> GLACIUM_ORE = register("glacium_ore",
            () -> new GlaciumBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.ICE)
                            .instrument(NoteBlockInstrument.FLUTE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 2.0F)
                            .friction(0.98F)
                            .sound(UOSoundTypes.GLACIUM),
                    true
            )
    );

    public static final DeferredBlock<Block> GLACIAL_CORE = register("glacial_core",
            () -> new GlaciumBlock(
                    BlockBehaviour.Properties.ofFullCopy(UOBlocks.GLACIUM_ORE.get())
                            .strength(4.0F, 3.0F),
                    true
            )
    );

    public static final DeferredBlock<Block> GLACIUM_BLOCK = register("glacium_block",
            () -> new GlaciumBlock(
                    BlockBehaviour.Properties.ofFullCopy(UOBlocks.GLACIUM_ORE.get())
                            .strength(5.0F, 6.0F),
                    false
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
