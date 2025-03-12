package xun.unoredinary.util;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class InventoryUtils {

    public static final int HEAD_SLOT = 3;
    public static final int CHEST_SLOT = 2;
    public static final int LEG_SLOT = 1;
    public static final int FEET_SLOT = 0;

    private InventoryUtils() {}

    public static ItemStack getWearingArmorInSlot(Player player, int slotIndex) {
        return player.getInventory().getArmor(slotIndex);
    }

    public static boolean hasWearingArmorInSlot(Player player, int slotIndex) {
        return !getWearingArmorInSlot(player, slotIndex).isEmpty();
    }

    public static boolean evaluateArmorInSlot(Player player, int slotIndex, Holder<ArmorMaterial> armorMaterial) {
        if (!(player.getInventory().getArmor(slotIndex).getItem() instanceof ArmorItem)) return false;
        return ((ArmorItem) player.getInventory().getArmor(slotIndex).getItem()).getMaterial() == armorMaterial;
    }

    public static boolean hasCorrectArmorInSlot(Player player, int slotIndex, Holder<ArmorMaterial> armorMaterial) {
        return hasWearingArmorInSlot(player, slotIndex) && evaluateArmorInSlot(player, slotIndex, armorMaterial);
    }

    public static boolean hasPlayerCorrectArmorOn(Player player, Holder<ArmorMaterial> armorMaterial) {

        return evaluateArmorInSlot(player, HEAD_SLOT, armorMaterial) &&
                evaluateArmorInSlot(player, CHEST_SLOT, armorMaterial) &&
                evaluateArmorInSlot(player, LEG_SLOT, armorMaterial) &&
                evaluateArmorInSlot(player, FEET_SLOT, armorMaterial);
    }

    public static boolean hasFullSuitOfArmorOn(Player player) {

        ItemStack helmet = getWearingArmorInSlot(player, HEAD_SLOT);
        ItemStack chestplate = getWearingArmorInSlot(player, CHEST_SLOT);
        ItemStack leggings = getWearingArmorInSlot(player, LEG_SLOT);
        ItemStack boots = getWearingArmorInSlot(player, FEET_SLOT);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}
