package xun.unoredinary.content.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LuminthiumArmorItem extends UOArmorItem{

    public LuminthiumArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player) {

            if (hasFullSuitOfArmorOn(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION));
            }
        }
    }
}
