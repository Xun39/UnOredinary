package xun.unoredinary.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.*;
import xun.unoredinary.content.item.armor.FrosteelArmorItem;
import xun.unoredinary.content.item.armor.ModArmorMaterials;
import xun.unoredinary.content.item.tool.FrosteelPickaxeItem;
import xun.unoredinary.content.item.tool.FrosteelSwordItem;
import xun.unoredinary.content.item.tool.ModToolTiers;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }

    public static final DeferredItem<Item> HEMOCRYLIC_SHARD = ITEMS.register("hemocrylic_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HEMOCRYLIC_CRYSTAL = ITEMS.register("hemocrylic_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRYOSTONE_DUST = ITEMS.register("cryostone_dust", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LUMINITE_CRYSTAL = ITEMS.register("luminite_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LUMINITE_TORCH = ITEMS.register("luminite_torch_item",
            () -> new StandingAndWallBlockItem(ModBlocks.LUMINITE_TORCH.get(), ModBlocks.LUMINITE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN)
    );

    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ICE_SHARD = ITEMS.register("ice_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ICE_BRICK = ITEMS.register("ice_brick", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTBITTEN_PHALANGES = ITEMS.register("frostbitten_phalanges", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FROSTEEL_INGOT = ITEMS.register("frosteel_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<SwordItem> FROSTEEL_SWORD = ITEMS.register("frosteel_sword",
            () -> new FrosteelSwordItem(ModToolTiers.FROSTEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.FROSTEEL, 3, -2.4F)))
    );
    public static final DeferredItem<PickaxeItem> FROSTEEL_PICKAXE = ITEMS.register("frosteel_pickaxe",
            () -> new FrosteelPickaxeItem(ModToolTiers.FROSTEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.FROSTEEL, 1.0F, -2.8F)))
    );
    public static final DeferredItem<AxeItem> FROSTEEL_AXE = ITEMS.register("frosteel_axe",
            () -> new AxeItem(ModToolTiers.FROSTEEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.FROSTEEL, 5.5F, -3.0F)))
    );
    public static final DeferredItem<HoeItem> FROSTEEL_HOE = ITEMS.register("frosteel_hoe",
            () -> new HoeItem(ModToolTiers.FROSTEEL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.FROSTEEL, -2.5F, 0.5F)))
    );
    public static final DeferredItem<ShovelItem> FROSTEEL_SHOVEL = ITEMS.register("frosteel_shovel",
            () -> new ShovelItem(ModToolTiers.FROSTEEL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.FROSTEEL, 1.5F, -3.0F)))
    );


    public static final DeferredItem<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new FrosteelSwordItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.RUBY, 3, -2.4F)))
    );
    public static final DeferredItem<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
            () -> new FrosteelPickaxeItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.RUBY, 1.0F, -2.8F)))
    );
    public static final DeferredItem<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe",
            () -> new AxeItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.RUBY, 5.5F, -3.0F)))
    );
    public static final DeferredItem<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe",
            () -> new HoeItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.RUBY, -2.5F, 0.5F)))
    );
    public static final DeferredItem<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
            () -> new ShovelItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.RUBY, 1.5F, -3.0F)))
    );


    public static final DeferredItem<ArmorItem> FROSTEEL_HELMET = ITEMS.register("frosteel_helmet",
            () -> new FrosteelArmorItem(ModArmorMaterials.FROSTEEL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(23)))
    );
    public static final DeferredItem<ArmorItem> FROSTEEL_CHESTPLATE = ITEMS.register("frosteel_chestplate",
            () -> new FrosteelArmorItem(ModArmorMaterials.FROSTEEL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(23)))
    );
    public static final DeferredItem<ArmorItem> FROSTEEL_LEGGINGS = ITEMS.register("frosteel_leggings",
            () -> new FrosteelArmorItem(ModArmorMaterials.FROSTEEL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(23)))
    );
    public static final DeferredItem<ArmorItem> FROSTEEL_BOOTS = ITEMS.register("frosteel_boots",
            () -> new FrosteelArmorItem(ModArmorMaterials.FROSTEEL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(23)))
    );


    public static final DeferredItem<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet",
            () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(29)))
    );
    public static final DeferredItem<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(29)))
    );
    public static final DeferredItem<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings",
            () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(29)))
    );
    public static final DeferredItem<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots",
            () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(29)))
    );


    public static final DeferredItem<Item> FROSTEEL_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("frosteel_upgrade_smithing_template",
            FrosteelUpgradeSmithingTemplateItem::createFrosteelUpgradeTemplate);


    public static final DeferredItem<Item> FROST_ZOMBIE_SPAWN_EGG = ITEMS.register("frost_zombie_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntityTypes.FROST_ZOMBIE, 0x19b3c3, 0x669da5,
                    new Item.Properties()));
}
