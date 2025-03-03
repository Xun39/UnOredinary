package xun.unoredinary.content.item.armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class LuminthiumArmorItem extends UOArmorItem{

    public LuminthiumArmorItem(Type type) {
        super(ModArmorMaterials.LUMINTHIUM, type,
                new Properties().durability(type.getDurability(31)));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player) {

            if (hasCorrectArmorInSlot(player, HEAD_SLOT, ModArmorMaterials.LUMINTHIUM)) {

                addEffectToPlayerIfEffectEndWithin(220, player,
                        List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0, false, false)));
            }
        }
    }
}
