package net.xun.unoredinary.item.tool;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.xun.armory.api.item.tools.ToolType;
import net.xun.armory.impl.item.tools.AbstractEffectToolCustomizer;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.lib.common.api.util.MobEffectUtils;
import net.xun.lib.common.api.world.effect.EffectStackingStrategies;
import net.xun.lib.common.api.world.effect.EffectStackingStrategy;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.server.UOServerConfig;
import net.xun.unoredinary.registry.*;

import java.util.List;

public class GlacialiteToolCustomizer extends AbstractEffectToolCustomizer {
    private static final int FROSTED_DURATION = 400;
    private static final int WEAKNESS_DURATION_NOVA = 60;
    private static final int WEAKNESS_AMPLIFIER_NOVA = 2;
    private static final int SLOW_DURATION = 60;
    private static final int SLOW_AMPLIFIER = 2;
    private static final int WEAKNESS_DURATION_SINGLE = 40;
    private static final int WEAKNESS_AMPLIFIER_SINGLE = 1;

    private static final float FROST_NOVA_RADIUS = 4.0F;

    @Override
    protected Item createSword(Tier tier, Item.Properties properties) {
        return new SwordItem(tier, properties) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                return onHit(
                        ToolType.SWORD,
                        super.hurtEnemy(stack, target, attacker),
                        target,
                        attacker
                );
            }

            @Override
            public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
                boolean flag = super.supportsEnchantment(stack, enchantment);

                if (stack.is(UOTools.GLACIALITE.getSword().get()))
                    return !enchantment.is(Enchantments.FIRE_ASPECT) && flag;

                return flag;
            }
        };
    }

    @Override
    protected void handleHitEffect(ToolType toolType, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player) || !UOServerConfig.toolEffectConfig.glacialiteConfig.enable.get()) {
            return;
        }

        boolean canNova = toolType == ToolType.SWORD || toolType == ToolType.AXE;

        boolean frostNovaEnabled = canNova && UOServerConfig.toolEffectConfig.glacialiteConfig.enableFrostNova.get();

        if (frostNovaEnabled) {
            applyFrostNovaEffect(target);
            return;
        }

        applySingleTargetEffects(target);
    }

    private static void applyFrostNovaEffect(LivingEntity target) {
        Level level = target.level();
        AABB area = BlockPosUtils.createAABBFromCenter(target.blockPosition(), FROST_NOVA_RADIUS);

        var config = UOServerConfig.toolEffectConfig.glacialiteConfig;

        if (config.doHitParticlesSpawn.get()) {
            spawnFrostParticles(target);
        }

        if (config.enableFrostNovaSound.get()) {
            level.playSound(
                    null,
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    UOSounds.FROST_NOVA.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }

        boolean affectPassive = config.frostNovaToPassive.get();

        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                area,
                entity -> entity.isAlive() && (affectPassive || entity instanceof Enemy)
        );

        for (LivingEntity entity : entities) {
            applyFrostEffects(entity);
        }
    }

    private static void applyFrostEffects(LivingEntity target) {
        List<MobEffectInstance> effects = List.of(
                buildEffectInstance(UOMobEffects.FROSTED_EFFECT, FROSTED_DURATION, 0),
                buildEffectInstance(MobEffects.WEAKNESS, WEAKNESS_DURATION_NOVA, WEAKNESS_AMPLIFIER_NOVA)
        );

        for (MobEffectInstance effect : effects) {
            MobEffectUtils.applyEffectWithStrategy(target, effect, EffectStackingStrategies.UPGRADE_EXISTING);
        }
    }

    private static void spawnFrostParticles(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return;

        double x = target.getX();
        double y = target.getBoundingBox().minY;
        double z = target.getZ();

        serverLevel.sendParticles(
                UOParticleTypes.FROST_NOVA.get(),
                x, y, z,
                1,
                0, 0, 0,
                FROST_NOVA_RADIUS
        );
    }

    private static void applySingleTargetEffects(LivingEntity target) {
        if (!UOServerConfig.toolEffectConfig.glacialiteConfig.enableNormalEffect.get())
            return;

        List<MobEffectInstance> effects = List.of(
                buildEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, SLOW_DURATION, SLOW_AMPLIFIER),
                buildEffectInstance(MobEffects.WEAKNESS, WEAKNESS_DURATION_SINGLE, WEAKNESS_AMPLIFIER_SINGLE)
        );

        for (MobEffectInstance effect : effects) {
            MobEffectUtils.applyEffectWithStrategy(target, effect, EffectStackingStrategies.UPGRADE_EXISTING);
        }
    }

    private static MobEffectInstance buildEffectInstance(Holder<MobEffect> effect, int duration, int amplifier) {
        return MobEffectInstanceBuilder.of(effect)
                .duration(duration)
                .amplifier(amplifier)
                .build();
    }
}
