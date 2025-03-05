package xun.unoredinary.content.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ArmorSet {

    private final String name;
    private final Holder<ArmorMaterial> armorMaterial;
    private final int durabilityFactor;
    private final Item.Properties armorProperties;

    private DeferredItem<ArmorItem> helmet;
    private DeferredItem<ArmorItem> chestplate;
    private DeferredItem<ArmorItem> leggings;
    private DeferredItem<ArmorItem> boots;

    public ArmorSet(String name, Holder<ArmorMaterial> armorMaterial, int durabilityFactor, Item.Properties armorProperties) {
        this.name = name;
        this.armorMaterial = armorMaterial;
        this.durabilityFactor = durabilityFactor;
        this.armorProperties = armorProperties;
    }

    public void register(DeferredRegister.Items registry) {
        helmet = registry.register(name + "_helmet", () -> createArmorItem(armorMaterial, ArmorItem.Type.HELMET, armorProperties));
        chestplate = registry.register(name + "_chestplate", () -> createArmorItem(armorMaterial, ArmorItem.Type.CHESTPLATE, armorProperties));
        leggings = registry.register(name + "_leggings", () -> createArmorItem(armorMaterial, ArmorItem.Type.LEGGINGS, armorProperties));
        boots = registry.register(name + "_boots", () -> createArmorItem(armorMaterial, ArmorItem.Type.BOOTS, armorProperties));
    }

    protected ArmorItem createArmorItem(Holder<ArmorMaterial> armorMaterial, ArmorItem.Type type, Item.Properties properties) {
        return new ArmorItem(armorMaterial, type, properties.durability(getDurabilityFactor()));
    }

    protected int getDurabilityFactor() {
        return durabilityFactor;
    }

    public DeferredItem<ArmorItem> helmet() { return helmet; }
    public DeferredItem<ArmorItem> chestplate() { return chestplate; }
    public DeferredItem<ArmorItem> leggings() { return leggings; }
    public DeferredItem<ArmorItem> boots() { return boots; }
}