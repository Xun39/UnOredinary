package net.xun.unoredinary.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.EventBusSubscriber;
import net.xun.armory.api.item.armor.ArmorCustomizer;
import net.xun.armory.api.item.armor.ArmorType;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.server.UOServerConfig;

public abstract class AbstractEffectArmorCustomizer implements ArmorCustomizer {
    @Override
    public ArmorItem createArmorItem(ArmorType type, Holder<ArmorMaterial> material, int durabilityFactor, Item.Properties props) {
        return new ArmorItem(material, type.getArmorType(), props.durability(type.getArmorType().getDurability(durabilityFactor))) {
            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (level.isClientSide)
                    return;

                if (!(entity instanceof LivingEntity living) || !(stack.getItem() instanceof ArmorItem item))
                    return;

                if (!shouldApplyArmorEffect())
                    return;

                boolean playersOnly = UOServerConfig.armorEffectConfig.onlyPlayer.get();

                if (playersOnly && !(living instanceof Player))
                    return;

                armorEffectTick(level, living, item);
            }

            @Override
            public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
                if (!shouldApplyArmorEffect())
                    return false;

                return AbstractEffectArmorCustomizer.this.canWalkOnPowderedSnow();
            }
        };
    }

    protected void armorEffectTick(Level level, LivingEntity entity, ArmorItem item) {

    }

    protected boolean shouldApplyArmorEffect() {
        return true;
    }

    protected boolean canWalkOnPowderedSnow() {
        return false;
    }
}
