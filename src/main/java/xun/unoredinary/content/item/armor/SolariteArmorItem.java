package xun.unoredinary.content.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SolariteArmorItem extends UOArmorItem{

    public SolariteArmorItem(Type type) {
        super(ModArmorMaterials.SOLARITE, type,
                new Properties().durability(type.getDurability(42)).fireResistant());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player) {

            if (hasFullSuitOfArmorOn(player) && hasPlayerCorrectArmorOn(player, ModArmorMaterials.SOLARITE)) {
                player.getAbilities().invulnerable = true;
            } else {
                player.getAbilities().invulnerable = false;
            }
        }
    }
}
