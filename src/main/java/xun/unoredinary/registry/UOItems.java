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

public class UOItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }

    public static final DeferredItem<Item> HEMOCRYLIC_SHARD = ITEMS.register("hemocrylic_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HEMOCRYLIC_CRYSTAL = ITEMS.register("hemocrylic_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRYOSTONE_DUST = ITEMS.register("cryostone_dust",
            () -> new ItemNameBlockItem(UOBlocks.CRYOSTONE_WIRE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRYOSTONE_TORCH = ITEMS.register("cryostone_torch_item",
            () -> new StandingAndWallBlockItem(UOBlocks.CRYOSTONE_TORCH.get(), UOBlocks.CRYOSTONE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN)
    );

    public static final DeferredItem<Item> LUMINITE_CRYSTAL = ITEMS.register("luminite_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LUMINITE_TORCH = ITEMS.register("luminite_torch_item",
            () -> new StandingAndWallBlockItem(UOBlocks.LUMINITE_TORCH.get(), UOBlocks.LUMINITE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN)
    );

    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ICE_SHARD = ITEMS.register("ice_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ICE_BRICK = ITEMS.register("ice_brick", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTBITTEN_PHALANGES = ITEMS.register("frostbitten_phalanges", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROSTEEL_INGOT = ITEMS.register("frosteel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LUMINTHIUM_INGOT = ITEMS.register("luminthium_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SOLARITE_GEM = ITEMS.register("solarite_gem", () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<AnimalArmorItem> RUBY_HORSE_ARMOR = ITEMS.register("ruby_horse_armor",
            () -> new AnimalArmorItem(UOArmorMaterials.RUBY, AnimalArmorItem.BodyType.EQUESTRIAN, false,
                    new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> FROSTEEL_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("frosteel_upgrade_smithing_template",
            FrosteelUpgradeSmithingTemplateItem::createFrosteelUpgradeTemplate);


    public static final DeferredItem<Item> FROST_ZOMBIE_SPAWN_EGG = ITEMS.register("frost_zombie_spawn_egg",
            () -> new DeferredSpawnEggItem(UOEntityTypes.FROST_ZOMBIE, 0x19b3c3, 0x669da5,
                    new Item.Properties()));
}