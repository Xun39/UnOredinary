package net.xun.unoredinary.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xun.armory.api.item.armor.ArmorCustomizer;
import net.xun.armory.api.item.armor.ArmorType;
import net.xun.lib.common.api.util.EquipmentSlotsUtils;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.server.UOServerConfig;
import net.xun.unoredinary.registry.UOArmorMaterials;

public class LuminiumArmorCustomizer implements ArmorCustomizer {
    @Override
    public ArmorItem createArmorItem(ArmorType type, Holder<ArmorMaterial> material, int durabilityFactor, Item.Properties props) {
        return new ArmorItem(material, type.getArmorType(), props.durability(type.getArmorType().getDurability(durabilityFactor))) {

            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (!UOServerConfig.armorEffectConfig.luminiumConfig.enable.get())
                    return;

                handleArmorEffect(stack, entity);
            }
        };
    }

    private static void handleArmorEffect(ItemStack stack, Entity entity) {
        if (!(entity instanceof Player player) || !(stack.getItem() instanceof ArmorItem))
            return;

        if (UOServerConfig.armorEffectConfig.luminiumConfig.enableNightVision.get()) {
            handleNightVisionEffect(player);
        }
    }

    private static void handleNightVisionEffect(Player player) {
        if (EquipmentSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.HEAD, UOArmorMaterials.LUMINIUM)) {
            MobEffectUtils.applyEffect(
                    player,
                    MobEffectInstanceBuilder.of(MobEffects.NIGHT_VISION)
                            .duration(220)
                            .build(),
                    200,
                    false
            );
        }
    }
}
