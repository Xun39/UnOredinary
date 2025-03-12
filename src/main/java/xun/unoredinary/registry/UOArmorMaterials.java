package xun.unoredinary.registry;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;

import java.util.EnumMap;
import java.util.List;

public class UOArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { ARMOR_MATERIALS.register(eventBus); }

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> FROSTEEL = register("frosteel",
            3, 7 ,5, 2, 9, 16,
            1.0F, 0.0F,
            UOSounds.ARMOR_EQUIP_FROSTEEL, UOItems.FROSTEEL_INGOT);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> LUMINTHIUM = register("luminthium",
            3, 5 ,4, 2, 7, 20,
            0.5F, 0.0F,
            SoundEvents.ARMOR_EQUIP_IRON, UOItems.LUMINTHIUM_INGOT);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SOLARITE = register("solarite",
            4, 9 ,7, 4, 15, 12,
            5.0F, 0.2F,
            SoundEvents.ARMOR_EQUIP_NETHERITE, UOItems.SOLARITE_GEM);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> RUBY = register("ruby",
            3, 8 ,6, 3, 12, 11,
            2.5F, 0.0F,
            SoundEvents.ARMOR_EQUIP_NETHERITE, UOItems.RUBY);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SAPPHIRE = register("sapphire",
            3, 8 ,6, 3, 11, 12,
            3.0F, 0.0F,
            SoundEvents.ARMOR_EQUIP_NETHERITE, UOItems.SAPPHIRE);

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(
            String name, int helmetDefense, int chestplateDefense, int leggingsDefense, int bootsDefense, int bodyDefense, int enchantmentValue,
            float armorToughness, float knockbackResistance,
            Holder<SoundEvent> equipSound, ItemLike repairIngredient) {

        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.HELMET, helmetDefense);
            map.put(ArmorItem.Type.CHESTPLATE, chestplateDefense);
            map.put(ArmorItem.Type.LEGGINGS, leggingsDefense);
            map.put(ArmorItem.Type.BOOTS, bootsDefense);
            map.put(ArmorItem.Type.BODY, bodyDefense);

        }), enchantmentValue, equipSound, () -> Ingredient.of(repairIngredient),
                List.of(new ArmorMaterial.Layer(UnOredinary.modLoc(name))), armorToughness, knockbackResistance));
    }
}
