package xun.unoredinary.content.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import xun.unoredinary.registry.ModSounds;

public class FrostZombieEntity extends Zombie {

    public FrostZombieEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.22F)
                .add(Attributes.ATTACK_DAMAGE, 3.25D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.FROST_ZOMBIE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.FROST_ZOMBIE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.FROST_ZOMBIE_DEATH.get();
    }

    @Override
    protected SoundEvent getStepSound() {
        return ModSounds.FROST_ZOMBIE_STEP.get();
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        boolean flag = super.doHurtTarget(entity);
        if (flag && this.getMainHandItem().isEmpty() && entity instanceof LivingEntity) {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120 * (int)f, 1), this);
        }
        return flag;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return super.hurt(source, amount);
    }

    @Override
    protected boolean convertsInWater() {
        return true;
    }

    @Override
    protected void doUnderWaterConversion() {
        if (!net.neoforged.neoforge.event.EventHooks.canLivingConvert(this, EntityType.DROWNED, (timer) -> this.conversionTime = timer)) return;
        this.convertToZombieType(EntityType.DROWNED);
        if (!this.isSilent()) {
            this.level().levelEvent(null, 1041, this.blockPosition(), 0);
        }
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }
}
