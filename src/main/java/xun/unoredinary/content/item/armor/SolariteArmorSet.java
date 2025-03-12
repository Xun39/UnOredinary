package xun.unoredinary.content.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import xun.unoredinary.registry.UOArmorMaterials;

import static xun.unoredinary.util.InventoryUtils.*;

public class SolariteArmorSet extends ArmorSet {

    public SolariteArmorSet(String name, Holder<ArmorMaterial> armorMaterial, int durabilityFactor, Item.Properties armorProperties) {
        super(name, armorMaterial, durabilityFactor, armorProperties);
    }

    @Override
    protected ArmorItem createArmorItem(Holder<ArmorMaterial> armorMaterial, ArmorItem.Type type, Item.Properties properties) {
        return new ArmorItem(armorMaterial, type, properties.durability(type.getDurability(getDurabilityFactor()))) {

            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (entity instanceof Player player) {

                    if (hasFullSuitOfArmorOn(player) && hasPlayerCorrectArmorOn(player, UOArmorMaterials.SOLARITE)) {
                        player.getAbilities().invulnerable = true;
                    } else {
                        player.getAbilities().invulnerable = false;
                    }
                }
            }
        };
    }
}
