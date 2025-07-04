package net.xun.unoredinary.item.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.client.UOClientConfig;
import net.xun.unoredinary.config.common.UOCommonConfig;
import net.xun.unoredinary.registry.UOArmorMaterials;
import net.xun.unoredinary.registry.UOParticleTypes;

@EventBusSubscriber(modid = UnOredinary.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class GlacialiteArmorConfigurator implements ArmorConfigurator {
    @Override
    public ArmorItem createArmor(ArmorType type, Holder<ArmorMaterial> material, int durabilityFactor, Item.Properties props) {
        return new ArmorItem(material, type.getType(), props) {
            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (!(entity instanceof Player player) || !(stack.getItem() instanceof ArmorItem))
                    return;

                if (UOCommonConfig.armorEffectConfig.glacialiteConfig.enable.get()) {

                    if (UOCommonConfig.armorEffectConfig.glacialiteConfig.enableSlownessImmunity.get()) {
                        handleSlownessImmunity(player);
                    }

                    if (UOCommonConfig.armorEffectConfig.glacialiteConfig.enableFrostWalker.get()) {
                        handleFrostWalkerEffect(player, level);
                    }
                }
            }

            @Override
            public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
                if (!UOCommonConfig.armorEffectConfig.glacialiteConfig.enable.get())
                    return false;

                return UOCommonConfig.armorEffectConfig.glacialiteConfig.canWalkOnPowderSnow.get();
            }
        };
    }

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event) {
        Entity attacker = event.getSource().getDirectEntity();
        LivingEntity receiver = event.getEntity();

        if (UOCommonConfig.armorEffectConfig.glacialiteConfig.enable.get()) {

            if (!UOCommonConfig.armorEffectConfig.glacialiteConfig.enableHotFloorDamage.get()) {
               immuneHotFloorDamage(event, receiver);
            }

            if (UOCommonConfig.armorEffectConfig.glacialiteConfig.enableThornsEffect.get()) {
                 handleThornsEffect(event, attacker, receiver);
            }
        }
    }

    private static void handleFrostWalkerEffect(Player player, Level level) {
        if (!ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET.getIndex(), UOArmorMaterials.GLACIALITE))
            return;

        BlockPos groundPos = player.getBlockPosBelowThatAffectsMyMovement();

        BlockPosUtils.getDisc(groundPos, 7).forEach(pos -> {
            if (!level.getBlockState(pos).is(Blocks.WATER))
                return;

            BlockPos abovePos = pos.above();

            if (level.getBlockState(abovePos).isAir() && level.isUnobstructed(Blocks.FROSTED_ICE.defaultBlockState(), pos, CollisionContext.empty())) {
                level.setBlock(pos, Blocks.FROSTED_ICE.defaultBlockState(), Block.UPDATE_ALL);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
            }
        });
    }

    private static void handleSlownessImmunity(Player player) {
        if (!ArmorSlotsUtils.hasFullArmorSetOfMaterial(player, UOArmorMaterials.GLACIALITE))
            return;

        if (player.getEffect(MobEffects.MOVEMENT_SLOWDOWN) != null) {
            MobEffectInstance effectInstance = player.getEffect(MobEffects.MOVEMENT_SLOWDOWN);
            if (effectInstance == null)
                return;

            MobEffectUtils.clearEffect(player, effectInstance);
        }
    }

    private static void immuneHotFloorDamage(LivingDamageEvent.Pre event, LivingEntity living) {
        if (!(living instanceof Player player))
            return;

        if (ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET.getIndex(), UOArmorMaterials.GLACIALITE))
            return;

        if (event.getSource().is(DamageTypeTags.BURN_FROM_STEPPING)) {
            event.setNewDamage(0.0F);
        }
    }

    private static void handleThornsEffect(LivingDamageEvent.Pre event, Entity attacker, LivingEntity receiver) {
        if (!(receiver instanceof Player player))
            return;

        if (ArmorSlotsUtils.hasFullArmorSetOfMaterial(player, UOArmorMaterials.GLACIALITE))
            return;

        if (attacker instanceof LivingEntity) {
            attacker.hurt(event.getSource(), event.getOriginalDamage());
        }

        if (UOClientConfig.armorEffectConfig.glacialiteConfig.doHurtParticlesSpawn.get()) {
            spawnHurtParticles(player);
        }
    }

    private static void spawnHurtParticles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return;

        if (target.level().getRandom().nextFloat() < 0.3F) {
            double centerX = target.getX() + target.level().getRandom().nextFloat();
            double centerY = target.getY() + target.getBbHeight() / 2.0;
            double centerZ = target.getZ() + target.level().getRandom().nextFloat();

            double halfWidth = target.getBbWidth() / 2.0;
            double halfHeight = target.getBbHeight() / 2.0;

            serverLevel.sendParticles(
                    UOParticleTypes.RIME.get(),
                    centerX, centerY, centerZ,
                    20,
                    halfWidth, halfHeight, halfWidth,
                    0.02
            );
        }
    }
}
