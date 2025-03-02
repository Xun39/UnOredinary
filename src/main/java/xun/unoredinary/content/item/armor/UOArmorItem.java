package xun.unoredinary.content.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class UOArmorItem extends ArmorItem {

    protected static final int HEAD_SLOT = 3;
    protected static final int CHEST_SLOT = 2;
    protected static final int LEG_SLOT = 1;
    protected static final int FEET_SLOT = 0;

    public UOArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    protected boolean hasWearingArmorInSlot(Player player, int slotIndex) {
        return !getWearingArmorInSlot(player, slotIndex).isEmpty();
    }

    private ItemStack getWearingArmorInSlot(Player player, int slotIndex) {
        return player.getInventory().getArmor(slotIndex);
    }

    protected boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = getWearingArmorInSlot(player, FEET_SLOT);
        ItemStack leggings = getWearingArmorInSlot(player, LEG_SLOT);
        ItemStack chestplate = getWearingArmorInSlot(player, CHEST_SLOT);
        ItemStack helmet = getWearingArmorInSlot(player, HEAD_SLOT);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}
