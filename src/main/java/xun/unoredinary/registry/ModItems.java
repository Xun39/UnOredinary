package xun.unoredinary.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.*;
import xun.unoredinary.content.item.armor.*;
import xun.unoredinary.content.item.tool.*;
import xun.unoredinary.content.item.tool.axe.FrosteelAxeItem;
import xun.unoredinary.content.item.tool.axe.SolariteAxeItem;
import xun.unoredinary.content.item.tool.axe.UOAxeItem;
import xun.unoredinary.content.item.tool.hoe.FrosteelHoeItem;
import xun.unoredinary.content.item.tool.hoe.SolariteHoeItem;
import xun.unoredinary.content.item.tool.hoe.UOHoeItem;
import xun.unoredinary.content.item.tool.pickaxe.FrosteelPickItem;
import xun.unoredinary.content.item.tool.pickaxe.SolaritePickItem;
import xun.unoredinary.content.item.tool.pickaxe.UOPickItem;
import xun.unoredinary.content.item.tool.shovel.FrosteelShovelItem;
import xun.unoredinary.content.item.tool.shovel.SolariteShovelItem;
import xun.unoredinary.content.item.tool.shovel.UOShovelItem;
import xun.unoredinary.content.item.tool.sword.FrosteelSwordItem;
import xun.unoredinary.content.item.tool.sword.SolariteSwordItem;
import xun.unoredinary.content.item.tool.sword.UOSwordItem;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }

    public static final DeferredItem<Item> HEMOCRYLIC_SHARD = ITEMS.register("hemocrylic_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HEMOCRYLIC_CRYSTAL = ITEMS.register("hemocrylic_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRYOSTONE_DUST = ITEMS.register("cryostone_dust",
            () -> new ItemNameBlockItem(ModBlocks.CRYOSTONE_WIRE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRYOSTONE_TORCH = ITEMS.register("cryostone_torch_item",
            () -> new StandingAndWallBlockItem(ModBlocks.CRYOSTONE_TORCH.get(), ModBlocks.CRYOSTONE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN)
    );

    public static final DeferredItem<Item> LUMINITE_CRYSTAL = ITEMS.register("luminite_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LUMINITE_TORCH = ITEMS.register("luminite_torch_item",
            () -> new StandingAndWallBlockItem(ModBlocks.LUMINITE_TORCH.get(), ModBlocks.LUMINITE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN)
    );

    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ICE_SHARD = ITEMS.register("ice_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ICE_BRICK = ITEMS.register("ice_brick", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTBITTEN_PHALANGES = ITEMS.register("frostbitten_phalanges", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTEEL_INGOT = ITEMS.register("frosteel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LUMINTHIUM_INGOT = ITEMS.register("luminthium_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SOLARITE_GEM = ITEMS.register("solarite_gem", () -> new Item(new Item.Properties().fireResistant()));

    // Frosteel tools
    public static final DeferredItem<SwordItem> FROSTEEL_SWORD = ITEMS.register("frosteel_sword", FrosteelSwordItem::new);
    public static final DeferredItem<PickaxeItem> FROSTEEL_PICKAXE = ITEMS.register("frosteel_pickaxe", FrosteelPickItem::new);
    public static final DeferredItem<AxeItem> FROSTEEL_AXE = ITEMS.register("frosteel_axe", FrosteelAxeItem::new);
    public static final DeferredItem<HoeItem> FROSTEEL_HOE = ITEMS.register("frosteel_hoe", FrosteelHoeItem::new);
    public static final DeferredItem<ShovelItem> FROSTEEL_SHOVEL = ITEMS.register("frosteel_shovel", FrosteelShovelItem::new);

    // Luminthium tools
    public static final DeferredItem<SwordItem> LUMINTHIUM_SWORD = ITEMS.register("luminthium_sword", () -> new UOSwordItem(ModToolTiers.LUMINTHIUM));
    public static final DeferredItem<PickaxeItem> LUMINTHIUM_PICKAXE = ITEMS.register("luminthium_pickaxe", () -> new UOPickItem(ModToolTiers.LUMINTHIUM));
    public static final DeferredItem<AxeItem> LUMINTHIUM_AXE = ITEMS.register("luminthium_axe", () -> new UOAxeItem(ModToolTiers.LUMINTHIUM));
    public static final DeferredItem<HoeItem> LUMINTHIUM_HOE = ITEMS.register("luminthium_hoe", () -> new UOHoeItem(ModToolTiers.LUMINTHIUM));
    public static final DeferredItem<ShovelItem> LUMINTHIUM_SHOVEL = ITEMS.register("luminthium_shovel", () -> new UOShovelItem(ModToolTiers.LUMINTHIUM));

    // Solarite tools
    public static final DeferredItem<SwordItem> SOLARITE_SWORD = ITEMS.register("solarite_sword", SolariteSwordItem::new);
    public static final DeferredItem<PickaxeItem> SOLARITE_PICKAXE = ITEMS.register("solarite_pickaxe", SolaritePickItem::new);
    public static final DeferredItem<AxeItem> SOLARITE_AXE = ITEMS.register("solarite_axe", SolariteAxeItem::new);
    public static final DeferredItem<HoeItem> SOLARITE_HOE = ITEMS.register("solarite_hoe", SolariteHoeItem::new);
    public static final DeferredItem<ShovelItem> SOLARITE_SHOVEL = ITEMS.register("solarite_shovel", SolariteShovelItem::new);

    // Ruby tools
    public static final DeferredItem<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new UOSwordItem(ModToolTiers.RUBY));
    public static final DeferredItem<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new UOPickItem(ModToolTiers.RUBY));
    public static final DeferredItem<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe", () -> new UOAxeItem(ModToolTiers.RUBY));
    public static final DeferredItem<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new UOHoeItem(ModToolTiers.RUBY));
    public static final DeferredItem<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new UOShovelItem(ModToolTiers.RUBY));

    // Frosteel armors
    public static final DeferredItem<ArmorItem> FROSTEEL_HELMET = ITEMS.register("frosteel_helmet", () -> new FrosteelArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<ArmorItem> FROSTEEL_CHESTPLATE = ITEMS.register("frosteel_chestplate", () -> new FrosteelArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<ArmorItem> FROSTEEL_LEGGINGS = ITEMS.register("frosteel_leggings", () -> new FrosteelArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<ArmorItem> FROSTEEL_BOOTS = ITEMS.register("frosteel_boots", () -> new FrosteelArmorItem(ArmorItem.Type.BOOTS));

    // Luminthium armors
    public static final DeferredItem<ArmorItem> LUMINTHIUM_HELMET = ITEMS.register("luminthium_helmet", () -> new LuminthiumArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<ArmorItem> LUMINTHIUM_CHESTPLATE = ITEMS.register("luminthium_chestplate", () -> new LuminthiumArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<ArmorItem> LUMINTHIUM_LEGGINGS = ITEMS.register("luminthium_leggings", () -> new LuminthiumArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<ArmorItem> LUMINTHIUM_BOOTS = ITEMS.register("luminthium_boots", () -> new LuminthiumArmorItem(ArmorItem.Type.BOOTS));

    // Solarite armors
    public static final DeferredItem<ArmorItem> SOLARITE_HELMET = ITEMS.register("solarite_helmet", () -> new SolariteArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<ArmorItem> SOLARITE_CHESTPLATE = ITEMS.register("solarite_chestplate", () -> new SolariteArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<ArmorItem> SOLARITE_LEGGINGS = ITEMS.register("solarite_leggings", () -> new SolariteArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<ArmorItem> SOLARITE_BOOTS = ITEMS.register("solarite_boots", () -> new SolariteArmorItem(ArmorItem.Type.BOOTS));

    // Ruby armors
    public static final DeferredItem<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new UOArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET, 29));
    public static final DeferredItem<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new UOArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, 29));
    public static final DeferredItem<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new UOArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, 29));
    public static final DeferredItem<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new UOArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS, 29));

    public static final DeferredItem<AnimalArmorItem> RUBY_HORSE_ARMOR = ITEMS.register("ruby_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.RUBY, AnimalArmorItem.BodyType.EQUESTRIAN, false,
                    new Item.Properties().stacksTo(1))
    );


    public static final DeferredItem<Item> FROSTEEL_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("frosteel_upgrade_smithing_template",
            FrosteelUpgradeSmithingTemplateItem::createFrosteelUpgradeTemplate);


    public static final DeferredItem<Item> FROST_ZOMBIE_SPAWN_EGG = ITEMS.register("frost_zombie_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntityTypes.FROST_ZOMBIE, 0x19b3c3, 0x669da5,
                    new Item.Properties()));
}
