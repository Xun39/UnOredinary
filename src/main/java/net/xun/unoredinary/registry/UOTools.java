package net.xun.unoredinary.registry;

import net.xun.armory.api.item.tools.ToolSet;
import net.xun.armory.api.item.tools.ToolType;
import net.xun.armory.impl.item.tools.GenericAttributeHelper;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.item.tool.GlacialiteToolConfigurator;
import net.xun.unoredinary.item.tool.FroststeelToolConfigurator;
import net.xun.unoredinary.item.tool.LuminiumToolConfigurator;
import net.xun.unoredinary.item.tool.attribute_helper.FrostToolAttributeHelper;

import java.util.ArrayList;
import java.util.List;

public class UOTools {
    private static final List<ToolSet> TOOL_SETS = new ArrayList<>();

    public static final ToolSet FROSTSTEEL = register(new ToolSet.Builder("froststeel", UOToolTiers.FROSTSTEEL, new FrostToolAttributeHelper())
            .withCustomizer(new FroststeelToolConfigurator())
            .withVanillaBalance()
            .build()
    );

    public static final ToolSet GLACIALITE = register(new ToolSet.Builder("glacialite", UOToolTiers.GLACIALITE, new FrostToolAttributeHelper())
            .withCustomizer(new GlacialiteToolConfigurator())
            .withVanillaBalance()
            .withToolStats(ToolType.AXE, 5.0F, 1.0F)
            .withToolStats(ToolType.HOE, -4.0F, 4.0F)
            .build()
    );

    public static final ToolSet LUMINIUM = register(new ToolSet.Builder("luminium", UOToolTiers.LUMINIUM, new GenericAttributeHelper())
            .withCustomizer(new LuminiumToolConfigurator())
            .withVanillaBalance()
            .withToolStats(ToolType.AXE, 5.0F, 1.0F)
            .withToolStats(ToolType.HOE, -3.0F, 4.0F)
            .build()
    );

    public static final ToolSet SAPPHIRE = register(new ToolSet.Builder("sapphire", UOToolTiers.SAPPHIRE, new GenericAttributeHelper())
            .withVanillaBalance()
            .withToolStats(ToolType.AXE, 5.0F, 1.0F)
            .withToolStats(ToolType.HOE, -3.0F, 4.0F)
            .build()
    );

    public static final ToolSet RUBY = register(new ToolSet.Builder("ruby", UOToolTiers.RUBY, new GenericAttributeHelper())
            .withVanillaBalance()
            .withToolStats(ToolType.AXE, 5.0F, 1.0F)
            .withToolStats(ToolType.HOE, -3.0F, 4.0F)
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
            toolSet.getPiecesForRegistration(UnOredinary.MOD_ID).forEach((location, supplier) -> {
                UOItems.ITEMS.register(location.getPath(), supplier);
            });
        });
    }
}
