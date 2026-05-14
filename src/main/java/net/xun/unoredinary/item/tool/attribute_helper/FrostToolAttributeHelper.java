package net.xun.unoredinary.item.tool.attribute_helper;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.xun.armory.api.item.tools.AttributeHelper;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOAttributes;

public class FrostToolAttributeHelper implements AttributeHelper {

    @Override
    public Item.Properties applyAttributes(Item.Properties properties, float damage, float speed) {
        return properties.attributes(createAttributeModifiers(damage, speed));
    }

    private static ItemAttributeModifiers createAttributeModifiers(float damage, float speed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                damage,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                speed - 4,
                                AttributeModifier.Operation.ADD_VALUE
                        ),EquipmentSlotGroup.MAINHAND)
                .add(UOAttributes.COLD_DAMAGE,
                        new AttributeModifier(
                                CommonUtils.modLoc("cold_damage"),
                                Math.round(damage/2),
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND)
                .build();
    }
}
