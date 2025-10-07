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
import net.xun.lib.common.api.item.armor.ArmorConfigurator;
import net.xun.lib.common.api.item.armor.ArmorType;
import net.xun.lib.common.api.util.ArmorSlotsUtils;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.common.UOCommonConfig;
import net.xun.unoredinary.registry.UOArmorMaterials;

import java.util.List;

public class LuminiumArmorConfigurator implements ArmorConfigurator {
    @Override
    public ArmorItem createArmor(ArmorType type, Holder<ArmorMaterial> material, int durabilityFactor, Item.Properties props) {
        return new ArmorItem(material, type.getType(), props.durability(type.getType().getDurability(durabilityFactor))) {

            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (!UOCommonConfig.armorEffectConfig.luminiumConfig.enable.get())
                    return;

                handleArmorEffect(stack, level, entity, slotId, isSelected);
            }
        };
    }

    private static void handleArmorEffect(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!(entity instanceof Player player) || !(stack.getItem() instanceof ArmorItem))
            return;

        if (UOCommonConfig.armorEffectConfig.luminiumConfig.enableNightVision.get()) {
            handleNightVisionEffect(player);
        }
    }

    private static void handleNightVisionEffect(Player player) {

        if (ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.HEAD.getIndex(), UOArmorMaterials.LUMINIUM)) {
            MobEffectUtils.applySmartEffects(
                    player,
                    List.of(
                            MobEffectInstanceBuilder.of(MobEffects.NIGHT_VISION)
                                    .withDuration(220)
                                    .ambient()
                                    .build()
                    ),
                    200,
                    false
            );
        }
    }
}
