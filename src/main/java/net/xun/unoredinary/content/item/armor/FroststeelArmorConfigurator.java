package net.xun.unoredinary.content.item.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.xun.lib.common.api.item.armor.ArmorConfigurator;
import net.xun.lib.common.api.item.armor.ArmorType;
import net.xun.lib.common.api.util.ArmorSlotsUtils;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.registry.UOArmorMaterials;

@EventBusSubscriber(modid = UnOredinary.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class FroststeelArmorConfigurator implements ArmorConfigurator {
    @Override
    public ArmorItem createArmor(ArmorType type, Holder<ArmorMaterial> material, int durabilityFactor, Item.Properties props) {
        return new ArmorItem(material, type.getType(), props) {

            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

                if (!(entity instanceof Player player) || !(stack.getItem() instanceof ArmorItem))
                    return;

                if (ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET.getIndex(), UOArmorMaterials.FROSTSTEEL)) {
                    BlockPos groundPos = player.getBlockPosBelowThatAffectsMyMovement();

                    BlockPosUtils.getDisc(groundPos, 2).forEach(pos -> {
                        if (!level.getBlockState(pos).is(Blocks.WATER))
                            return;

                        BlockPos abovePos = pos.above();

                        if (level.getBlockState(abovePos).isAir() && level.isUnobstructed(Blocks.FROSTED_ICE.defaultBlockState(), pos, CollisionContext.empty())) {
                            level.setBlock(pos, Blocks.FROSTED_ICE.defaultBlockState(), Block.UPDATE_ALL);
                            level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
                        }
                    });
                }
            }
        };
    }

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        if (!ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET.getIndex(), UOArmorMaterials.FROSTSTEEL))
            return;

        if (event.getSource().is(DamageTypeTags.BURN_FROM_STEPPING)) {
            event.setNewDamage(0.0F);
        }
    }

    // TODO: Add attribute modifiers
    private static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.BURNING_TIME,
                        new AttributeModifier(
                                ResourceLocation.withDefaultNamespace("burning_time"),
                                -0.4,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.ARMOR)
                .build();
    }
}
