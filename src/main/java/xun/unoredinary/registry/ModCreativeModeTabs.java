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

    public static final Supplier<CreativeModeTab> UNOREDINARY_BLOCK_TAB = CREATIVE_MODE_TABS.register("unoredinary_block_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.HEMOCRYLIC_ORE.get()))
                    .title(Component.translatable("creativemodetab.unoredinary.block"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModBlocks.HEMOCRYLIC_ORE);

                        output.accept(ModBlocks.CRYOSTONE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_CRYOSTONE_ORE);

                        output.accept(ModBlocks.LUMINITE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_LUMINITE_ORE);

                        output.accept(ModBlocks.RUBY_ORE);
                        output.accept(ModBlocks.DEEPSLATE_RUBY_ORE);

                        output.accept(ModBlocks.SOLARITE_ORE);

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

                        output.accept(ModBlocks.HEMOCRYLIC_BLOCK);
                        output.accept(ModBlocks.FROSTEEL_BLOCK);
                        output.accept(ModBlocks.CRYOSTONE_BLOCK);
                        output.accept(ModBlocks.LUMINITE_BLOCK);
                        output.accept(ModBlocks.LUMINTHIUM_BLOCK);
                        output.accept(ModBlocks.RUBY_BLOCK);

                    }).build());

    public static final Supplier<CreativeModeTab> UNOREDINARY_EQUIPMENT_TAB = CREATIVE_MODE_TABS.register("unoredinary_equipment_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FROSTEEL_HOE.get()))
                    .title(Component.translatable("creativemodetab.unoredinary.equipment"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.FROSTEEL_HELMET);
                        output.accept(ModItems.FROSTEEL_CHESTPLATE);
                        output.accept(ModItems.FROSTEEL_LEGGINGS);
                        output.accept(ModItems.FROSTEEL_BOOTS);

                        output.accept(ModItems.FROSTEEL_SWORD);
                        output.accept(ModItems.FROSTEEL_PICKAXE);
                        output.accept(ModItems.FROSTEEL_AXE);
                        output.accept(ModItems.FROSTEEL_HOE);
                        output.accept(ModItems.FROSTEEL_SHOVEL);

                        output.accept(ModItems.LUMINTHIUM_HELMET);
                        output.accept(ModItems.LUMINTHIUM_CHESTPLATE);
                        output.accept(ModItems.LUMINTHIUM_LEGGINGS);
                        output.accept(ModItems.LUMINTHIUM_BOOTS);

                        output.accept(ModItems.LUMINTHIUM_SWORD);
                        output.accept(ModItems.LUMINTHIUM_PICKAXE);
                        output.accept(ModItems.LUMINTHIUM_AXE);
                        output.accept(ModItems.LUMINTHIUM_HOE);
                        output.accept(ModItems.LUMINTHIUM_SHOVEL);

                        output.accept(ModItems.SOLARITE_HELMET);
                        output.accept(ModItems.SOLARITE_CHESTPLATE);
                        output.accept(ModItems.SOLARITE_LEGGINGS);
                        output.accept(ModItems.SOLARITE_BOOTS);

                        output.accept(ModItems.SOLARITE_SWORD);
                        output.accept(ModItems.SOLARITE_PICKAXE);
                        output.accept(ModItems.SOLARITE_AXE);
                        output.accept(ModItems.SOLARITE_HOE);
                        output.accept(ModItems.SOLARITE_SHOVEL);

                        output.accept(ModItems.RUBY_HELMET);
                        output.accept(ModItems.RUBY_CHESTPLATE);
                        output.accept(ModItems.RUBY_LEGGINGS);
                        output.accept(ModItems.RUBY_BOOTS);

                        output.accept(ModItems.RUBY_SWORD);
                        output.accept(ModItems.RUBY_PICKAXE);
                        output.accept(ModItems.RUBY_AXE);
                        output.accept(ModItems.RUBY_HOE);
                        output.accept(ModItems.RUBY_SHOVEL);

                        output.accept(ModItems.RUBY_HORSE_ARMOR);

                    }).build());

    public static final Supplier<CreativeModeTab> UNOREDINARY_ITEM_TAB = CREATIVE_MODE_TABS.register("unoredinary_item_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HEMOCRYLIC_SHARD.get()))
                    .title(Component.translatable("creativemodetab.unoredinary.item"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.HEMOCRYLIC_SHARD);
                        output.accept(ModItems.HEMOCRYLIC_CRYSTAL);

                        output.accept(ModItems.CRYOSTONE_DUST);
                        output.accept(ModItems.CRYOSTONE_TORCH);

                        output.accept(ModItems.FROSTBITTEN_PHALANGES);

                        output.accept(ModItems.ICE_SHARD);
                        output.accept(ModItems.ICE_BRICK);

                        output.accept(ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE);

                        output.accept(ModItems.FROSTEEL_INGOT);
                        output.accept(ModItems.LUMINTHIUM_INGOT);


                        output.accept(ModItems.LUMINITE_CRYSTAL);
                        output.accept(ModItems.LUMINITE_TORCH);

                        output.accept(ModItems.RUBY);

                        output.accept(ModItems.SOLARITE_GEM);

                        output.accept(ModItems.FROST_ZOMBIE_SPAWN_EGG);

                    }).build());
}