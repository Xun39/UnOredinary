package xun.unoredinary.content.item.armor;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModItems;
import xun.unoredinary.registry.ModSounds;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final Holder<ArmorMaterial> FROSTEEL = register("frosteel", Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
        attribute.put(ArmorItem.Type.BOOTS, 2);
        attribute.put(ArmorItem.Type.LEGGINGS, 5);
        attribute.put(ArmorItem.Type.CHESTPLATE, 7);
        attribute.put(ArmorItem.Type.HELMET, 3);
        attribute.put(ArmorItem.Type.BODY, 9);

    }), 16, ModSounds.ARMOR_EQUIP_FROSTEEL, 1.0F, 0.0F, () -> Ingredient.of(ModItems.FROSTEEL_INGOT));

    public static final Holder<ArmorMaterial> RUBY = register("ruby", Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
        attribute.put(ArmorItem.Type.BOOTS, 3);
        attribute.put(ArmorItem.Type.LEGGINGS, 7);
        attribute.put(ArmorItem.Type.CHESTPLATE, 7);
        attribute.put(ArmorItem.Type.HELMET, 3);
        attribute.put(ArmorItem.Type.BODY, 12);

    }), 11, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(ModItems.RUBY));

    public static final Holder<ArmorMaterial> LUMINTHIUM = register("luminthium", Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
        attribute.put(ArmorItem.Type.BOOTS, 2);
        attribute.put(ArmorItem.Type.LEGGINGS, 4);
        attribute.put(ArmorItem.Type.CHESTPLATE, 5);
        attribute.put(ArmorItem.Type.HELMET, 3);
        attribute.put(ArmorItem.Type.BODY, 7);

    }), 20, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.LUMINTHIUM_INGOT));


    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngridient
    ) {
        ResourceLocation location = UnOredinary.modLoc(name);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, defense.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngridient, layers, toughness, knockbackResistance)
        );
    }
}
