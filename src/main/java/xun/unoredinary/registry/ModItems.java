package xun.unoredinary.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.*;
import xun.unoredinary.content.item.armor.*;
import xun.unoredinary.content.item.tool.*;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnOredinary.MOD_ID);

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
    public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ICE_SHARD = ITEMS.register("ice_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ICE_BRICK = ITEMS.register("ice_brick", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTBITTEN_PHALANGES = ITEMS.register("frostbitten_phalanges", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTEEL_INGOT = ITEMS.register("frosteel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LUMINTHIUM_INGOT = ITEMS.register("luminthium_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SOLARITE_GEM = ITEMS.register("solarite_gem", () -> new Item(new Item.Properties().fireResistant()));

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

    public static final ArmorSet SAPPHIRE_ARMORS = new ArmorSet("sapphire", ModArmorMaterials.SAPPHIRE, 32, new Item.Properties());

    public static final DeferredItem<AnimalArmorItem> RUBY_HORSE_ARMOR = ITEMS.register("ruby_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.RUBY, AnimalArmorItem.BodyType.EQUESTRIAN, false,
                    new Item.Properties().stacksTo(1))
    );


    public static final DeferredItem<Item> FROSTEEL_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("frosteel_upgrade_smithing_template",
            FrosteelUpgradeSmithingTemplateItem::createFrosteelUpgradeTemplate);


    public static final DeferredItem<Item> FROST_ZOMBIE_SPAWN_EGG = ITEMS.register("frost_zombie_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntityTypes.FROST_ZOMBIE, 0x19b3c3, 0x669da5,
                    new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ModTools.register();
        ITEMS.register(eventBus);
    }
}