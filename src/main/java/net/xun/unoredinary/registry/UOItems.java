package net.xun.unoredinary.registry;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.lib.common.api.item.UpgradeSmithingTemplateItem;
import net.xun.unoredinary.UnOredinary;

public class UOItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnOredinary.MOD_ID);

    public static final DeferredItem<Item> CRYIC_POWDER = ITEMS.register("cryic_powder", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLACIUM_SHARDS = ITEMS.register("glacium_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GLACIUM_CRYSTAL = ITEMS.register("glacium_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTSTEEL_INGOT = ITEMS.register("froststeel_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRYOSTEEL_INGOT = ITEMS.register("cryosteel_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRYOSTEEL_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("cryosteel_upgrade_smithing_template",
            () -> new UpgradeSmithingTemplateItem("cryosteel_upgrade", UpgradeSmithingTemplateItem.createTemplateUpgradeIconList(), UpgradeSmithingTemplateItem.createTemplateUpgradeMaterialList())
    );
}
