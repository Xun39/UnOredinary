package net.xun.unoredinary.registry;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.util.UOTags;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class UOArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, UnOredinary.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> FROSTSTEEL = register("froststeel",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 16, 0.0F, 0.0F, () -> Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), SoundEvents.ARMOR_EQUIP_IRON
    );

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CRYOSTEEL = register("cryosteel",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }), 13, 3.0F, 0.1F, () -> Ingredient.of(UOTags.Items.INGOTS_CRYOSTEEL), UOSounds.ARMOR_EQUIP_CRYOSTEEL
    );

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name,
                                                                         Map<ArmorItem.Type, Integer> defense,
                                                                         int enchantmentValue,
                                                                         float toughness,
                                                                         float knockbackResistance,
                                                                         Supplier<Ingredient> repairIngredient,
                                                                         Holder<SoundEvent> equipSound) {
        return ARMOR_MATERIALS.register(name,
                () -> new ArmorMaterial(
                        defense,
                        enchantmentValue,
                        equipSound,
                        repairIngredient,
                        List.of(new ArmorMaterial.Layer(CommonUtils.modLoc(name))),
                        toughness,
                        knockbackResistance)
        );
    }
}
