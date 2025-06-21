package net.xun.unoredinary.registry;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.lib.common.api.item.UpgradeSmithingTemplateItem;
import net.xun.unoredinary.UnOredinary;

public class UOItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnOredinary.MOD_ID);

    public static final DeferredItem<Item> CRYIC_POWDER = ITEMS.register("cryic_powder", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLACIUM_SHARD = ITEMS.register("glacium_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GLACIUM_CRYSTAL = ITEMS.register("glacium_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTSTEEL_INGOT = ITEMS.register("froststeel_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLACIALITE_INGOT = ITEMS.register("glacialite_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLACIALITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("glacialite_upgrade_smithing_template",
            () -> new UpgradeSmithingTemplateItem("glacialite_upgrade", UpgradeSmithingTemplateItem.createTemplateUpgradeIconList(), UpgradeSmithingTemplateItem.createTemplateUpgradeMaterialList())
    );

    public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties()));

    // Spawn Eggs
    public static final DeferredItem<Item> FROST_ZOMBIE_SPAWN_EGG = ITEMS.register("frost_zombie_spawn_egg",
            () -> new DeferredSpawnEggItem(UOEntityTypes.FROST_ZOMBIE, 0x19b3c3, 0x669da5, new Item.Properties())
    );
}
