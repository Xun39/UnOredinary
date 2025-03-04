package xun.unoredinary.content.item.armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

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
                        List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 240, 0, false, false)));

                if (hasPlayerCorrectArmorOn(player, ModArmorMaterials.LUMINTHIUM)) {

                    double radius = 15.0;
                    List<LivingEntity> nearbyMobs = level.getEntitiesOfClass(LivingEntity.class,
                            new AABB(player.getX() - radius, player.getY() - radius, player.getZ() - radius,
                                    player.getX() + radius, player.getY() + radius, player.getZ() + radius),
                            e -> e instanceof Monster && e != player);

                    for (LivingEntity mob : nearbyMobs) {
                        mob.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0, false, false));
                    }
                }
            }
        }
    }
}
