package net.xun.unoredinary.registry;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;

public class UOItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnOredinary.MOD_ID);

    public static final DeferredItem<Item> GLACIUM_SHARDS = ITEMS.register("glacium_shards", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GLACIUM_CRYSTAL = ITEMS.register("glacium_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTSTEEL_INGOT = ITEMS.register("froststeel_ingot", () -> new Item(new Item.Properties()));
}
