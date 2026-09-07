package net.xun.unoredinary.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xun.lib.common.api.item.armor.ArmorContext;
import net.xun.lib.common.api.item.armor.ArmorCustomizer;
import net.xun.lib.common.api.item.armor.ArmorPieceType;
import net.xun.unoredinary.config.server.UOServerConfig;

public abstract class AbstractEffectArmorCustomizer implements ArmorCustomizer {

    @Override
    public Item create(ArmorPieceType piece, ArmorContext context, Item.Properties properties) {
        ArmorItem.Type vanillaType = piece.vanillaType();
        Item.Properties finalProps = context.applyProperties(piece, properties.durability(vanillaType.getDurability(context.durabilityFactor())));

        return new ArmorItem(context.material(), vanillaType, finalProps) {
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
