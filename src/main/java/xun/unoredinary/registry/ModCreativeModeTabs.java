package xun.unoredinary.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { CREATIVE_MODE_TABS.register(eventBus); }

    public static final Supplier<CreativeModeTab> UNOREDINARY_ITEM_TAB = CREATIVE_MODE_TABS.register("unoredinary_item_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HEMOCRYLIC_SHARD.get()))
                    .title(Component.translatable("creativemodetab.unoredinary.item"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.HEMOCRYLIC_SHARD);
                        output.accept(ModItems.HEMOCRYLIC_CRYSTAL);

                        output.accept(ModItems.CRYOSTONE_DUST);

                        output.accept(ModItems.FROSTBITTEN_PHALANGES);

                        output.accept(ModItems.ICE_SHARD);
                        output.accept(ModItems.ICE_BRICK);

                        output.accept(ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE);

                        output.accept(ModItems.FROSTEEL_INGOT);

                        output.accept(ModItems.FROSTEEL_SWORD);
                        output.accept(ModItems.FROSTEEL_PICKAXE);
                        output.accept(ModItems.FROSTEEL_AXE);
                        output.accept(ModItems.FROSTEEL_HOE);
                        output.accept(ModItems.FROSTEEL_SHOVEL);

                        output.accept(ModItems.FROST_ZOMBIE_SPAWN_EGG);

                    }).build());

    public static final Supplier<CreativeModeTab> UNOREDINARY_BLOCK_TAB = CREATIVE_MODE_TABS.register("unoredinary_block_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.HEMOCRYLIC_ORE.get()))
                    .title(Component.translatable("creativemodetab.unoredinary.block"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModBlocks.HEMOCRYLIC_ORE);
                        output.accept(ModBlocks.HEMOCRYLIC_BLOCK);

                        output.accept(ModBlocks.CRYOSTONE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_CRYOSTONE_ORE);

                        output.accept(ModBlocks.FROSTBOUND_STONE);

                        output.accept(ModBlocks.FROSTBOUND_STONE_STAIRS);
                        output.accept(ModBlocks.FROSTBOUND_STONE_SLAB);


                        output.accept(ModBlocks.FROSTBOUND_COBBLESTONE);

                        output.accept(ModBlocks.FROSTBOUND_COBBLESTONE_STAIRS);
                        output.accept(ModBlocks.FROSTBOUND_COBBLESTONE_SLAB);
                        output.accept(ModBlocks.FROSTBOUND_COBBLESTONE_WALL);


                        output.accept(ModBlocks.CRYOBOUND_COBBLESTONE);

                        output.accept(ModBlocks.CRYOBOUND_COBBLESTONE_STAIRS);
                        output.accept(ModBlocks.CRYOBOUND_COBBLESTONE_SLAB);
                        output.accept(ModBlocks.CRYOBOUND_COBBLESTONE_WALL);


                        output.accept(ModBlocks.FROSTBOUND_STONE_BRICKS);

                        output.accept(ModBlocks.FROSTBOUND_STONE_BRICK_STAIRS);
                        output.accept(ModBlocks.FROSTBOUND_STONE_BRICK_SLAB);
                        output.accept(ModBlocks.FROSTBOUND_STONE_BRICK_WALL);


                        output.accept(ModBlocks.ICE_BRICKS);

                        output.accept(ModBlocks.ICE_BRICK_STAIRS);
                        output.accept(ModBlocks.ICE_BRICK_SLAB);
                        output.accept(ModBlocks.ICE_BRICK_WALL);

                    }).build());
}
