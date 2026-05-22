package net.xun.unoredinary.item.armor;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.Level;
import net.xun.lib.common.api.util.EquipmentSlotsUtils;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.config.server.UOServerConfig;
import net.xun.unoredinary.registry.UOArmorMaterials;

public class LuminiumArmorCustomizer extends AbstractEffectArmorCustomizer {
    @Override
    protected void armorEffectTick(Level level, LivingEntity entity, ArmorItem item) {
        if (UOServerConfig.armorEffectConfig.luminiumConfig.enableNightVision.get()) {
            handleNightVisionEffect(entity);
        }
    }

    @Override
    protected boolean shouldApplyArmorEffect() {
        return UOServerConfig.armorEffectConfig.luminiumConfig.enable.get();
    }

    private static void handleNightVisionEffect(LivingEntity entity) {
        if (!EquipmentSlotsUtils.isArmorMaterialInSlot(entity, EquipmentSlot.HEAD, UOArmorMaterials.LUMINIUM))
            return;

        MobEffectUtils.applyEffect(
                entity,
                MobEffectInstanceBuilder.of(MobEffects.NIGHT_VISION)
                        .duration(220)
                        .build(),
                220,
                false
        );
    }
}
