package net.xun.unoredinary.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.xun.lib.common.api.item.tools.ToolSet;
import net.xun.lib.common.api.item.tools.ToolStats;
import net.xun.lib.common.api.item.tools.VanillaToolPieces;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.item.tool.GlacialiteToolCustomizer;
import net.xun.unoredinary.item.tool.FroststeelToolCustomizer;
import net.xun.unoredinary.item.tool.LuminiumToolCustomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UOTools {
    private static final List<ToolSet> TOOL_SETS = new ArrayList<>();

    public static final ToolSet FROSTSTEEL = register(ToolSet.builder("froststeel", UOToolTiers.FROSTSTEEL)
            .addPieces(VanillaToolPieces.STANDARD)
            .globalAdditionalAttributes(builder -> builder.add(
                    UOAttributes.COLD_DAMAGE,
                    new AttributeModifier(
                            CommonUtils.modLoc("cold_damage"),
                            2.0F,
                            AttributeModifier.Operation.ADD_VALUE
                    ), EquipmentSlotGroup.MAINHAND
            ).build())
            .withCustomizer(new FroststeelToolCustomizer())
            .build()
    );

    public static final ToolSet GLACIALITE = register(ToolSet.builder("glacialite", UOToolTiers.GLACIALITE)
            .addPieces(VanillaToolPieces.STANDARD)
            .globalAdditionalAttributes(builder -> builder.add(
                    UOAttributes.COLD_DAMAGE,
                    new AttributeModifier(
                            CommonUtils.modLoc("cold_damage"),
                            2.0F,
                            AttributeModifier.Operation.ADD_VALUE
                    ), EquipmentSlotGroup.MAINHAND
            ).build())
            .withCustomizer(new GlacialiteToolCustomizer())
            .overrideStats(VanillaToolPieces.AXE, new ToolStats(5.0F, 1.0F))
            .overrideStats(VanillaToolPieces.HOE, new ToolStats(-4.0F, 4.0F))
            .build()
    );

    public static final ToolSet LUMINIUM = register(ToolSet.builder("luminium", UOToolTiers.LUMINIUM)
            .addPieces(VanillaToolPieces.STANDARD)
            .withCustomizer(new LuminiumToolCustomizer())
            .overrideStats(VanillaToolPieces.AXE, new ToolStats(5.0F, 1.0F))
            .overrideStats(VanillaToolPieces.HOE, new ToolStats(-3.0F, 4.0F))
            .build()
    );

    public static final ToolSet SAPPHIRE = register(ToolSet.builder("sapphire", UOToolTiers.SAPPHIRE)
            .addPieces(VanillaToolPieces.STANDARD)
            .overrideStats(VanillaToolPieces.AXE, new ToolStats(5.0F, 1.0F))
            .overrideStats(VanillaToolPieces.HOE, new ToolStats(-3.0F, 4.0F))
            .build()
    );

    public static final ToolSet RUBY = register(ToolSet.builder("ruby", UOToolTiers.RUBY)
            .addPieces(VanillaToolPieces.STANDARD)
            .overrideStats(VanillaToolPieces.AXE, new ToolStats(5.0F, 1.0F))
            .overrideStats(VanillaToolPieces.HOE, new ToolStats(-3.0F, 4.0F))
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
            for (Map.Entry<ResourceLocation, Function<Item.Properties, Item>> entry : toolSet.getPiecesForRegistration(UnOredinary.MOD_ID).entrySet()) {
                ResourceLocation id = entry.getKey();
                Function<Item.Properties, Item> factory = entry.getValue();

                var holder = UOItems.ITEMS.register(id.getPath(), () -> factory.apply(new Item.Properties()));
                toolSet.bind(id.getPath(), holder);
            }
        });
    }
}
