package net.xun.unoredinary.registry;

import net.xun.lib.common.api.item.armor.ArmorSet;
import net.xun.unoredinary.item.armor.GlacialiteArmorConfigurator;
import net.xun.unoredinary.item.armor.FroststeelArmorConfigurator;
import net.xun.unoredinary.item.armor.LuminiumArmorConfigurator;

import java.util.ArrayList;
import java.util.List;

public class UOArmors {
    private static final List<ArmorSet> ARMOR_SETS = new ArrayList<>();

    public static final ArmorSet FROSTSTEEL = register(new ArmorSet.Builder("froststeel", UOArmorMaterials.FROSTSTEEL)
            .withConfiguration(new FroststeelArmorConfigurator())
            .withDurabilityFactor(21)
            .build()
    );

    public static final ArmorSet GLACIALITE = register(new ArmorSet.Builder("glacialite", UOArmorMaterials.GLACIALITE)
            .withConfiguration(new GlacialiteArmorConfigurator())
            .withDurabilityFactor(35)
            .build()
    );

    public static final ArmorSet LUMINIUM = register(new ArmorSet.Builder("luminium", UOArmorMaterials.LUMINIUM)
            .withConfiguration(new LuminiumArmorConfigurator())
            .withDurabilityFactor(30)
            .build()
    );

    public static final ArmorSet SAPPHIRE = register(new ArmorSet.Builder("sapphire", UOArmorMaterials.SAPPHIRE)
            .withDurabilityFactor(26)
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
            armorSet.getItemsForRegistration().forEach((location, armorItemSupplier) -> {
                UOItems.ITEMS.register(location.getPath(), armorItemSupplier);
            });
        });
    }
}
