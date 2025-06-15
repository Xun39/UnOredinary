package net.xun.unoredinary.registry;

import net.xun.lib.common.api.item.tools.GenericAttributeHelper;
import net.xun.lib.common.api.item.tools.ToolSet;
import net.xun.lib.common.api.item.tools.ToolType;
import net.xun.unoredinary.content.item.tool.CryosteelToolConfigurator;
import net.xun.unoredinary.content.item.tool.FroststeelToolConfigurator;

import java.util.ArrayList;
import java.util.List;

public class UOTools {
    private static final List<ToolSet> TOOL_SETS = new ArrayList<>();

    public static final ToolSet FROSTSTEEL = register(new ToolSet.Builder("froststeel", UOToolTiers.FROSTSTEEL, new GenericAttributeHelper())
            .withConfiguration(new FroststeelToolConfigurator())
            .withVanillaBalance()
            .build()
    );

    public static final ToolSet CRYOSTEEL = register(new ToolSet.Builder("cryosteel", UOToolTiers.CRYOSTEEL, new GenericAttributeHelper())
            .withConfiguration(new CryosteelToolConfigurator())
            .withVanillaBalance()
            .withToolStats(ToolType.AXE, 5.0F, 1.0F)
            .withToolStats(ToolType.HOE, -4.0F, 0.0F)
            .build()
    );

    public static List<ToolSet> getTools() {
        return new ArrayList<>(TOOL_SETS);
    }

    private static ToolSet register(ToolSet toolSet) {
        TOOL_SETS.add(toolSet);
        return toolSet;
    }

    public static void registerTools() {
        getTools().forEach(toolSet -> {
            toolSet.getItemsForRegistration().forEach((location, supplier) -> {
                UOItems.ITEMS.register(location.getPath(), supplier);
            });
        });
    }
}
