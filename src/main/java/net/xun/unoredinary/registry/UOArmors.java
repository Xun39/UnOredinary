package net.xun.unoredinary.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.xun.lib.common.api.item.armor.ArmorSet;
import net.xun.lib.common.api.item.armor.VanillaArmorPieces;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.item.armor.GlacialiteArmorCustomizer;
import net.xun.unoredinary.item.armor.FroststeelArmorCustomizer;
import net.xun.unoredinary.item.armor.LuminiumArmorCustomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UOArmors {
    private static final List<ArmorSet> ARMOR_SETS = new ArrayList<>();

    public static final ArmorSet FROSTSTEEL = register(ArmorSet.builder("froststeel", UOArmorMaterials.FROSTSTEEL)
            .addPieces(VanillaArmorPieces.PLAYER)
            .withCustomizer(new FroststeelArmorCustomizer())
            .withDurabilityFactor(21)
            .build()
    );

    public static final ArmorSet GLACIALITE = register(ArmorSet.builder("glacialite", UOArmorMaterials.GLACIALITE)
            .addPieces(VanillaArmorPieces.PLAYER)
            .withCustomizer(new GlacialiteArmorCustomizer())
            .withDurabilityFactor(35)
            .build()
    );

    public static final ArmorSet LUMINIUM = register(ArmorSet.builder("luminium", UOArmorMaterials.LUMINIUM)
            .addPieces(VanillaArmorPieces.PLAYER)
            .withCustomizer(new LuminiumArmorCustomizer())
            .withDurabilityFactor(30)
            .build()
    );

    public static final ArmorSet SAPPHIRE = register(ArmorSet.builder("sapphire", UOArmorMaterials.SAPPHIRE)
            .addPieces(VanillaArmorPieces.PLAYER)
            .withDurabilityFactor(26)
            .build()
    );

    public static final ArmorSet RUBY = register(ArmorSet.builder("ruby", UOArmorMaterials.RUBY)
            .addPieces(VanillaArmorPieces.PLAYER)
            .withDurabilityFactor(28)
            .build()
    );

    public static List<ArmorSet> getArmors() {
        return new ArrayList<>(ARMOR_SETS);
    }

    private static ArmorSet register(ArmorSet armorSet) {
        ARMOR_SETS.add(armorSet);
        return armorSet;
    }

    public static void registerArmors() {
        getArmors().forEach(armorSet -> {
            for (Map.Entry<ResourceLocation, Function<Item.Properties, Item>> entry : armorSet.getPiecesForRegistration(UnOredinary.MOD_ID).entrySet()) {
                ResourceLocation id = entry.getKey();
                Function<Item.Properties, Item> factory = entry.getValue();

                var holder = UOItems.ITEMS.register(id.getPath(), () -> factory.apply(new Item.Properties()));
                armorSet.bind(id.getPath(), holder);
            }
        });
    }
}
