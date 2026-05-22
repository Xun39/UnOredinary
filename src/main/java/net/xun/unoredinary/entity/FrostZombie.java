package net.xun.unoredinary.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xun.lib.common.api.world.effect.MobEffectInstanceBuilder;
import net.xun.unoredinary.registry.UOMobEffects;
import net.xun.unoredinary.registry.UOSounds;

public class FrostZombie extends Zombie {
    public FrostZombie(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.21F)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UOSounds.FROST_ZOMBIE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UOSounds.FROST_ZOMBIE_HURT.get();
    }

    @Override
    protected SoundEvent getStepSound() {
        return UOSounds.FROST_ZOMBIE_STEP.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UOSounds.FROST_ZOMBIE_DEATH.get();
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        boolean flag = super.doHurtTarget(entity);

        if (flag && entity instanceof LivingEntity living) {
            if (!living.canFreeze())
                return flag;

            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            living.addEffect(MobEffectInstanceBuilder.of(UOMobEffects.FROSTED_EFFECT).duration(160 * (int)f).amplifier(1).build(), this);
        }

        return flag;
    }

    @Override
    protected boolean convertsInWater() {
        return false;
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }
}
