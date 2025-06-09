package net.xun.unoredinary.registry;

import net.minecraft.world.item.Item;
import net.xun.lib.common.api.item.tools.GenericAttributeHelper;
import net.xun.lib.common.api.item.tools.ToolSet;

import java.util.ArrayList;
import java.util.List;

public class UOTools {
    private static final List<ToolSet> TOOL_SETS = new ArrayList<>();

    public static final ToolSet FROSTSTEEL = register(new ToolSet.Builder("froststeel", UOToolTiers.FROSTSTEEL, new GenericAttributeHelper())
            .withItemProperties(new Item.Properties())
            .withVanillaBalance()
            .build()
    );

    public static List<ToolSet> getTools() {
        return new ArrayList<>(TOOL_SETS);
    }

    private static ToolSet register(ToolSet toolSet) {
        TOOL_SETS.add(toolSet);

        toolSet.getItemsForRegistration().forEach((location, supplier) -> {
            UOItems.ITEMS.register(location.getPath(), supplier);
        });

        return toolSet;
    }
}
