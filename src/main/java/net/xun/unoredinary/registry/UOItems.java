package net.xun.unoredinary.registry;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.lib.common.api.item.UpgradeSmithingTemplateItem;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.UnOredinary;

import java.util.function.Supplier;

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

    // Heads
    public static final DeferredItem<Item> FROST_ZOMBIE_HEAD = ITEMS.register("frost_zombie_head_item",
            () -> new StandingAndWallBlockItem(UOBlocks.FROST_ZOMBIE_HEAD.get(), UOBlocks.FROST_ZOMBIE_WALL_HEAD.get(), new Item.Properties().rarity(Rarity.UNCOMMON), Direction.DOWN)
    );

    // Spawn Eggs
    public static final DeferredItem<Item> FROST_ZOMBIE_SPAWN_EGG = ITEMS.register("frost_zombie_spawn_egg",
            () -> new DeferredSpawnEggItem(UOEntityTypes.FROST_ZOMBIE, 0x19b3c3, 0x669da5, new Item.Properties())
    );
}
