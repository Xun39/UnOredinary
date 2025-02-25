package xun.unoredinary.content.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class UOArmorItem extends ArmorItem {

    public UOArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    protected ItemStack getWearingArmorInSlot(Player player, int slotIndex) {
        return player.getInventory().getArmor(slotIndex);
    }

    protected boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = getWearingArmorInSlot(player, 0);
        ItemStack leggings = getWearingArmorInSlot(player, 1);
        ItemStack chestplate = getWearingArmorInSlot(player, 2);
        ItemStack helmet = getWearingArmorInSlot(player, 3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}
