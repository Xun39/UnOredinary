package xun.unoredinary.content.item.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import xun.unoredinary.registry.ModItems;

public class ModTools {

    public static final FrosteelToolSet FROSTEEL_TOOLS = new FrosteelToolSet("frosteel", ModToolTiers.FROSTEEL, new Item.Properties());
    public static final SolariteToolSet SOLARITE_TOOLS = new SolariteToolSet("solarite", ModToolTiers.SOLARITE, new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON));
    public static final ToolSet LUMINTHIUM_TOOLS = new ToolSet("luminthium", ModToolTiers.LUMINTHIUM, new Item.Properties());
    public static final ToolSet RUBY_TOOLS = new ToolSet("ruby", ModToolTiers.RUBY, new Item.Properties());
    public static final ToolSet SAPPHIRE_TOOLS = new ToolSet("sapphire", ModToolTiers.SAPPHIRE, new Item.Properties());

    public static void register() {
        FROSTEEL_TOOLS.register(ModItems.ITEMS);
        SOLARITE_TOOLS.register(ModItems.ITEMS);
        LUMINTHIUM_TOOLS.register(ModItems.ITEMS);
        RUBY_TOOLS.register(ModItems.ITEMS);
        SAPPHIRE_TOOLS.register(ModItems.ITEMS);
    }
}
