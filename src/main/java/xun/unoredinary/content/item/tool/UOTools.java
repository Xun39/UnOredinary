package xun.unoredinary.content.item.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import xun.unoredinary.registry.UOItems;

import java.util.List;

public class UOTools {

    public static final FrosteelToolSet FROSTEEL = new FrosteelToolSet("frosteel", UOTiers.FROSTEEL, new Item.Properties());
    public static final SolariteToolSet SOLARITE = new SolariteToolSet("solarite", UOTiers.SOLARITE, new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON));
    public static final ToolSet LUMINTHIUM = new ToolSet("luminthium", UOTiers.LUMINTHIUM, new Item.Properties());
    public static final ToolSet RUBY = new ToolSet("ruby", UOTiers.RUBY, new Item.Properties());
    public static final ToolSet SAPPHIRE = new ToolSet("sapphire", UOTiers.SAPPHIRE, new Item.Properties());

    public static List<ToolSet> getTools() {
        return List.of(FROSTEEL, SOLARITE, LUMINTHIUM, RUBY, SAPPHIRE);
    }

    public static void register() {
        for (ToolSet set : getTools()) {
            set.register(UOItems.ITEMS);
        }
    }
}
