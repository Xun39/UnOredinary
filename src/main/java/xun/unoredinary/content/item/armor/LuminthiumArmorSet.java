package xun.unoredinary.content.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import xun.unoredinary.registry.UOArmorMaterials;

import java.util.List;

import static xun.unoredinary.util.InventoryUtils.*;
import static xun.unoredinary.util.PlayerUtils.*;

public class LuminthiumArmorSet extends ArmorSet {

    public LuminthiumArmorSet(String name, Holder<ArmorMaterial> armorMaterial, int durabilityFactor, Item.Properties armorProperties) {
        super(name, armorMaterial, durabilityFactor, armorProperties);
    }

    @Override
    protected ArmorItem createArmorItem(Holder<ArmorMaterial> armorMaterial, ArmorItem.Type type, Item.Properties properties) {
        return new ArmorItem(armorMaterial, type, properties.durability(type.getDurability(getDurabilityFactor()))) {

            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (entity instanceof Player player) {

                    if (hasCorrectArmorInSlot(player, HEAD_SLOT, UOArmorMaterials.LUMINTHIUM)) {

                        addEffectToPlayerIfEffectEndWithin(220, player,
                                List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 240, 0, false, false)));

                        if (hasPlayerCorrectArmorOn(player, UOArmorMaterials.LUMINTHIUM)) {

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
        };
    }
}
