package xun.unoredinary.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.armor.ArmorSet;
import xun.unoredinary.content.item.armor.UOArmors;
import xun.unoredinary.content.item.tool.ToolSet;
import xun.unoredinary.content.item.tool.UOTools;

import java.util.function.Supplier;

public class UOCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { CREATIVE_MODE_TABS.register(eventBus); }

    public static final Supplier<CreativeModeTab> UNOREDINARY_BLOCK_TAB = CREATIVE_MODE_TABS.register("unoredinary_block_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(UOBlocks.HEMOCRYLIC_ORE.get()))
                    .title(Component.translatable("creativemodetab.unoredinary.block"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(UOBlocks.HEMOCRYLIC_ORE);

                        output.accept(UOBlocks.CRYOSTONE_ORE);
                        output.accept(UOBlocks.DEEPSLATE_CRYOSTONE_ORE);

                        output.accept(UOBlocks.LUMINITE_ORE);
                        output.accept(UOBlocks.DEEPSLATE_LUMINITE_ORE);

                        output.accept(UOBlocks.RUBY_ORE);
                        output.accept(UOBlocks.DEEPSLATE_RUBY_ORE);

                        output.accept(UOBlocks.SOLARITE_ORE);

                        output.accept(UOBlocks.FROSTBOUND_STONE);

                        output.accept(UOBlocks.FROSTBOUND_STONE_STAIRS);
                        output.accept(UOBlocks.FROSTBOUND_STONE_SLAB);

                        output.accept(UOBlocks.FROSTBOUND_COBBLESTONE);

                        output.accept(UOBlocks.FROSTBOUND_COBBLESTONE_STAIRS);
                        output.accept(UOBlocks.FROSTBOUND_COBBLESTONE_SLAB);
                        output.accept(UOBlocks.FROSTBOUND_COBBLESTONE_WALL);

                        output.accept(UOBlocks.CRYOBOUND_COBBLESTONE);

                        output.accept(UOBlocks.CRYOBOUND_COBBLESTONE_STAIRS);
                        output.accept(UOBlocks.CRYOBOUND_COBBLESTONE_SLAB);
                        output.accept(UOBlocks.CRYOBOUND_COBBLESTONE_WALL);

                        output.accept(UOBlocks.FROSTBOUND_STONE_BRICKS);

                        output.accept(UOBlocks.FROSTBOUND_STONE_BRICK_STAIRS);
                        output.accept(UOBlocks.FROSTBOUND_STONE_BRICK_SLAB);
                        output.accept(UOBlocks.FROSTBOUND_STONE_BRICK_WALL);

                        output.accept(UOBlocks.ICE_BRICKS);

                        output.accept(UOBlocks.ICE_BRICK_STAIRS);
                        output.accept(UOBlocks.ICE_BRICK_SLAB);
                        output.accept(UOBlocks.ICE_BRICK_WALL);

                        output.accept(UOBlocks.HEMOCRYLIC_BLOCK);
                        output.accept(UOBlocks.FROSTEEL_BLOCK);
                        output.accept(UOBlocks.CRYOSTONE_BLOCK);
                        output.accept(UOBlocks.LUMINITE_BLOCK);
                        output.accept(UOBlocks.LUMINTHIUM_BLOCK);
                        output.accept(UOBlocks.RUBY_BLOCK);

                    }).build());

    public static final Supplier<CreativeModeTab> UNOREDINARY_EQUIPMENT_TAB = CREATIVE_MODE_TABS.register("unoredinary_equipment_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(UOTools.FROSTEEL.hoe().get()))
                    .title(Component.translatable("creativemodetab.unoredinary.equipment"))
                    .displayItems((itemDisplayParameters, output) -> {

                        for (int i = 0; i < UOArmors.getArmors().size(); i++) {
                            ArmorSet armorSet = UOArmors.getArmors().get(i);
                            for (Item item : armorSet.get()) {
                                output.accept(item);
                            }

                            ToolSet toolSet = UOTools.getTools().get(i);
                            for (Item item : toolSet.get()) {
                                output.accept(item);
                            }
                        }

                        output.accept(UOItems.RUBY_HORSE_ARMOR);

                    }).build());

    public static final Supplier<CreativeModeTab> UNOREDINARY_ITEM_TAB = CREATIVE_MODE_TABS.register("unoredinary_item_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(UOItems.HEMOCRYLIC_SHARD.get()))
                    .title(Component.translatable("creativemodetab.unoredinary.item"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(UOItems.HEMOCRYLIC_SHARD);
                        output.accept(UOItems.HEMOCRYLIC_CRYSTAL);

                        output.accept(UOItems.CRYOSTONE_DUST);
                        output.accept(UOItems.CRYOSTONE_TORCH);

                        output.accept(UOItems.FROSTBITTEN_PHALANGES);

                        output.accept(UOItems.ICE_SHARD);
                        output.accept(UOItems.ICE_BRICK);

                        output.accept(UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE);

                        output.accept(UOItems.FROSTEEL_INGOT);
                        output.accept(UOItems.LUMINTHIUM_INGOT);


                        output.accept(UOItems.LUMINITE_CRYSTAL);
                        output.accept(UOItems.LUMINITE_TORCH);

                        output.accept(UOItems.RUBY);
                        output.accept(UOItems.SAPPHIRE);

                        output.accept(UOItems.SOLARITE_GEM);

                        output.accept(UOItems.FROST_ZOMBIE_SPAWN_EGG);

                    }).build());
}