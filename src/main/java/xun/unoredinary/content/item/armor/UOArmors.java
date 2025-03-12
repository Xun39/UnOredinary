package xun.unoredinary.content.item.armor;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import xun.unoredinary.registry.UOArmorMaterials;
import xun.unoredinary.registry.UOItems;

import java.util.List;

public class UOArmors {

    public static final ArmorSet FROSTEEL = new FrosteelArmorSet("frosteel", UOArmorMaterials.FROSTEEL, 35, new Item.Properties());
    public static final ArmorSet SOLARITE = new SolariteArmorSet("solarite", UOArmorMaterials.SOLARITE, 42, new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON));
    public static final ArmorSet LUMINTHIUM = new LuminthiumArmorSet("luminthium", UOArmorMaterials.LUMINTHIUM, 31, new Item.Properties());
    public static final ArmorSet RUBY = new ArmorSet("ruby", UOArmorMaterials.RUBY, 29, new Item.Properties());
    public static final ArmorSet SAPPHIRE = new ArmorSet("sapphire", UOArmorMaterials.SAPPHIRE, 32, new Item.Properties());

    public static List<ArmorSet> getArmors() {
        return List.of(FROSTEEL, SOLARITE, LUMINTHIUM, RUBY, SAPPHIRE);
    }

    public static void register() {
        for (ArmorSet set : getArmors()) {
            set.register(UOItems.ITEMS);
        }
    }
}
