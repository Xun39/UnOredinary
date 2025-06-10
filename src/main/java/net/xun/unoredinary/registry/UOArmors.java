package net.xun.unoredinary.registry;

import net.xun.lib.common.api.item.armor.ArmorSet;

import java.util.ArrayList;
import java.util.List;

public class UOArmors {
    private static final List<ArmorSet> ARMOR_SETS = new ArrayList<>();

    public static final ArmorSet FROSTSTEEL = register(new ArmorSet.Builder("froststeel", UOArmorMaterials.FROSTSTEEL)
            .withDurabilityFactor(27)
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
