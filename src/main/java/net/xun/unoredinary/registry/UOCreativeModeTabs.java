package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.lib.common.api.item.armor.ArmorSet;
import net.xun.lib.common.api.item.tools.ToolSet;
import net.xun.unoredinary.UnOredinary;

public class UOCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnOredinary.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> UO_BLOCKS = CREATIVE_MODE_TABS.register("uo_blocks",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_mode_tab.unoredinary.block"))
                    .icon(UOBlocks.GLACIUM_ORE::toStack)
                    .displayItems((parameters, output) ->  {

                        output.accept(UOBlocks.CRYIC_ORE);
                        output.accept(UOBlocks.DEEPSLATE_CRYIC_ORE);

                        output.accept(UOBlocks.SAPPHIRE_ORE);
                        output.accept(UOBlocks.DEEPSLATE_SAPPHIRE_ORE);

                        output.accept(UOBlocks.GLACIUM_ORE);
                        output.accept(UOBlocks.PRIMAL_GLACIUM_ORE);

                        output.accept(UOBlocks.CRYIC_BLOCK);
                        output.accept(UOBlocks.SAPPHIRE_BLOCK);
                        output.accept(UOBlocks.GLACIUM_BLOCK);
                        output.accept(UOBlocks.FROSTSTEEL_BLOCK);
                        output.accept(UOBlocks.GLACIALITE_BLOCK);

                    })
                    .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> UO_ITEMS = CREATIVE_MODE_TABS.register("uo_items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_mode_tab.unoredinary.item"))
                    .icon(UOItems.GLACIUM_CRYSTAL::toStack)
                    .displayItems((parameters, output) ->  {

                        output.accept(UOItems.CRYIC_POWDER);

                        output.accept(UOItems.SAPPHIRE);

                        output.accept(UOItems.GLACIUM_SHARD);
                        output.accept(UOItems.GLACIUM_CRYSTAL);

                        output.accept(UOItems.FROSTSTEEL_INGOT);
                        output.accept(UOItems.GLACIALITE_INGOT);

                        output.accept(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE);

                    })
                    .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> UO_EQUIPMENTS = CREATIVE_MODE_TABS.register("uo_equipments",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_mode_tab.unoredinary.equipment"))
                    .icon(() -> new ItemStack(UOTools.FROSTSTEEL.getSword().get()))
                    .displayItems((parameters, output) -> {

                        for (int i = 0; i < UOArmors.getArmors().size(); i++) {
                            ArmorSet armorSet = UOArmors.getArmors().get(i);
                            for (Item item : armorSet.getAll()) {
                                output.accept(item);
                            }

                            ToolSet toolSet = UOTools.getTools().get(i);
                            for (Item item : toolSet.getAll()) {
                                output.accept(item);
                            }
                        }

                    })
                    .build()
    );
}
