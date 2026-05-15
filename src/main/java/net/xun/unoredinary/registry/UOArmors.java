package net.xun.unoredinary.registry;

import net.xun.armory.api.item.armor.ArmorSet;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.item.armor.GlacialiteArmorCustomizer;
import net.xun.unoredinary.item.armor.FroststeelArmorCustomizer;
import net.xun.unoredinary.item.armor.LuminiumArmorCustomizer;

import java.util.ArrayList;
import java.util.List;

public class UOArmors {
    private static final List<ArmorSet> ARMOR_SETS = new ArrayList<>();

    public static final ArmorSet FROSTSTEEL = register(new ArmorSet.Builder("froststeel", UOArmorMaterials.FROSTSTEEL)
            .withCustomizer(new FroststeelArmorCustomizer())
            .withDurabilityFactor(21)
            .build()
    );

    public static final ArmorSet GLACIALITE = register(new ArmorSet.Builder("glacialite", UOArmorMaterials.GLACIALITE)
            .withCustomizer(new GlacialiteArmorCustomizer())
            .withDurabilityFactor(35)
            .build()
    );

    public static final ArmorSet LUMINIUM = register(new ArmorSet.Builder("luminium", UOArmorMaterials.LUMINIUM)
            .withCustomizer(new LuminiumArmorCustomizer())
            .withDurabilityFactor(30)
            .build()
    );

    public static final ArmorSet SAPPHIRE = register(new ArmorSet.Builder("sapphire", UOArmorMaterials.SAPPHIRE)
            .withDurabilityFactor(26)
            .build()
    );

    public static final ArmorSet RUBY = register(new ArmorSet.Builder("ruby", UOArmorMaterials.RUBY)
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
            armorSet.getPiecesForRegistration(UnOredinary.MOD_ID).forEach((location, armorItemSupplier) -> {
                UOItems.ITEMS.register(location.getPath(), armorItemSupplier);
            });
        });
    }
}
