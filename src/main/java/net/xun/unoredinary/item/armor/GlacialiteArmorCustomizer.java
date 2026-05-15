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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.xun.armory.api.item.armor.ArmorCustomizer;
import net.xun.armory.api.item.armor.ArmorType;
import net.xun.lib.common.api.util.ArmorSlotsUtils;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.server.UOServerConfig;
import net.xun.unoredinary.registry.UOArmorMaterials;
import net.xun.unoredinary.registry.UOParticleTypes;

@EventBusSubscriber(modid = UnOredinary.MOD_ID)
public class GlacialiteArmorCustomizer implements ArmorCustomizer {
    @Override
    public ArmorItem createArmorItem(ArmorType type, Holder<ArmorMaterial> material, int durabilityFactor, Item.Properties props) {

        return new ArmorItem(material, type.getArmorType(), props.durability(type.getArmorType().getDurability(durabilityFactor))) {

            @Override
            public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                if (!(entity instanceof Player player) || !(stack.getItem() instanceof ArmorItem))
                    return;

                if (UOServerConfig.armorEffectConfig.glacialiteConfig.enable.get()) {

                    if (UOServerConfig.armorEffectConfig.glacialiteConfig.enableSlownessImmunity.get()) {
                        handleSlownessImmunity(player);
                    }

                    if (UOServerConfig.armorEffectConfig.glacialiteConfig.enableFrostWalker.get()) {
                        handleFrostWalkerEffect(player, level);
                    }
                }
            }

            @Override
            public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
                if (!UOServerConfig.armorEffectConfig.glacialiteConfig.enable.get())
                    return false;

                return UOServerConfig.armorEffectConfig.glacialiteConfig.canWalkOnPowderSnow.get();
            }
        };
    }

    // Make the player immune to hot floor damage
    @SubscribeEvent
    public static void onInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity living))
            return;

        if (!(living instanceof Player player))
            return;

        if (UOServerConfig.armorEffectConfig.glacialiteConfig.enableHotFloorDamage.get())
            return;

        if (ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET.getIndex(), UOArmorMaterials.GLACIALITE)) {
            event.setInvulnerable(event.getSource().is(DamageTypeTags.BURN_FROM_STEPPING));
        }
    }

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event) {
        Entity attacker = event.getSource().getDirectEntity();
        LivingEntity receiver = event.getEntity();

        if (UOServerConfig.armorEffectConfig.glacialiteConfig.enable.get()) {

            if (UOServerConfig.armorEffectConfig.glacialiteConfig.enableThornsEffect.get()) {
                 handleThornsEffect(event, attacker, receiver);
            }
        }
    }

    private static void handleFrostWalkerEffect(Player player, Level level) {
        if (!ArmorSlotsUtils.isArmorMaterialInSlot(player, EquipmentSlot.FEET.getIndex(), UOArmorMaterials.GLACIALITE))
            return;

        if (level.isClientSide || !player.onGround())
            return;

        BlockPos groundPos = player.getBlockPosBelowThatAffectsMyMovement();

        int radius = UOServerConfig.armorEffectConfig.glacialiteConfig.frostWalkerRadius.getAsInt();

        BlockPosUtils.getDisc(groundPos, radius).forEach(pos -> {
            if (pos.closerToCenterThan(player.position(), radius)) {
                freezeNearbyBlock(level, pos, player);
            }
        });
    }

    private static void freezeNearbyBlock(Level level, BlockPos pos, Player player) {
        BlockState currentState = level.getBlockState(pos);
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        if (currentState.is(Blocks.WATER) && currentState.getFluidState().isSource()) {
            if (aboveState.isAir() || aboveState.canBeReplaced()) {

                level.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
                level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);

                level.scheduleTick(pos, Blocks.FROSTED_ICE, level.getRandom().nextInt(60) + 20);
            }
        }
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

    private static void handleThornsEffect(LivingDamageEvent.Pre event, Entity attacker, LivingEntity receiver) {
        if (!(receiver instanceof Player player))
            return;

        if (!ArmorSlotsUtils.hasFullArmorSetOfMaterial(player, UOArmorMaterials.GLACIALITE))
            return;

        if (attacker instanceof LivingEntity) {
            attacker.hurt(event.getSource(), event.getOriginalDamage());
        }

        if (UOServerConfig.armorEffectConfig.glacialiteConfig.doDamageParticlesSpawn.get()) {
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
