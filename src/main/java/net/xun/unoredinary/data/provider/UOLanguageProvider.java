package net.xun.unoredinary.data.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.xun.lib.common.api.item.armor.ArmorSet;
import net.xun.lib.common.api.item.tools.ToolSet;
import net.xun.unoredinary.UnOredinary;

import java.util.Locale;

public abstract class UOLanguageProvider extends LanguageProvider {
    public UOLanguageProvider(PackOutput output) {
        super(output, UnOredinary.MOD_ID, "en_us");
    }

    public void addToolSet(ToolSet toolSet) {
        String baseName = toolSet.getName();
        String formattedName = baseName.substring(0, 1).toUpperCase(Locale.ENGLISH) + baseName.substring(1).toLowerCase(Locale.ENGLISH);

        addItem(toolSet.getSword(), formattedName + " Sword");
        addItem(toolSet.getAxe(), formattedName + " Axe");
        addItem(toolSet.getPickaxe(), formattedName + " Pickaxe");
        addItem(toolSet.getHoe(), formattedName + " Hoe");
        addItem(toolSet.getShovel(), formattedName + " Shovel");
    }

    public void addArmorSet(ArmorSet armorSet) {
        String baseName = armorSet.getName();
        String formattedName = baseName.substring(0, 1).toUpperCase(Locale.ENGLISH) + baseName.substring(1).toLowerCase(Locale.ENGLISH);

        addItem(armorSet.getHelmet(), formattedName + " Helmet");
        addItem(armorSet.getChestplate(), formattedName + " Chestplate");
        addItem(armorSet.getLeggings(), formattedName + " Leggings");
        addItem(armorSet.getBoots(), formattedName + " Boots");
    }

    public void addEntityAndSpawnEgg(DeferredHolder<EntityType<?>, ? extends EntityType<?>> entity, String name) {
        addEntityType(entity, name);
        add("item.unoredinary." + entity.getKey().location().getPath() + "_spawn_egg", name + " Spawn Egg");
    }

    public void addAdvancement(String key, String name, String desc) {
        add("advancement.unoredinary." + key, name);
        add("advancement.unoredinary." + key + ".desc", desc);
    }

    public void addTrimMaterial(ResourceKey<TrimMaterial> material, String name) {
        add("trim_material" + ".unoredinary." + material.location().getPath(), name);
    }

    public void translateTag(TagKey<?> tag, String name) {
        this.add(String.format("tag.%s.%s.%s", tag.registry().location().getPath(), tag.location().getNamespace(), tag.location().getPath().replace('/', '.')), name);
    }
}
