package net.xun.unoredinary.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.xun.unoredinary.client.animation.AnimationController;
import net.xun.unoredinary.client.animation.FrostRevenantAnimation;
import net.xun.unoredinary.entity.ai.FrostRevenantPhaseRetreatGoal;
import net.xun.unoredinary.entity.ai.FrostRevenantRangedAttackGoal;
import net.xun.unoredinary.entity.projectile.FrostShard;
import net.xun.unoredinary.registry.UOSounds;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class FrostRevenant extends Monster implements RangedAttackMob {
    private static final EntityDataAccessor<Boolean> PHASING = SynchedEntityData.defineId(FrostRevenant.class, EntityDataSerializers.BOOLEAN);

    public final AnimationController<FrostRevenant> controller = AnimationController.builder(this)
            .animation("idle", FrostRevenantAnimation.IDLE)
            .animation("attack", FrostRevenantAnimation.ATTACK)
            .build();

    public FrostRevenant(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(PHASING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FrostRevenantPhaseRetreatGoal(this));
        this.goalSelector.addGoal(2, new FrostRevenantRangedAttackGoal(this, 1.0, 40, 5, 12.0F));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0F)
                .add(Attributes.ARMOR, 10.0F)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    public boolean isPhasing() {
        return this.entityData.get(PHASING);
    }

    private void setPhasing(boolean value) {
        this.entityData.set(PHASING, value);
    }

    public void beginPhase() {
        this.setPhasing(true);
        this.noPhysics = true;
        this.setNoGravity(true);
        this.getNavigation().stop();
        this.setDeltaMovement(Vec3.ZERO);
    }

    public void endPhase() {
        this.setPhasing(false);
        this.noPhysics = false;
        this.setNoGravity(false);
        this.setDeltaMovement(Vec3.ZERO);
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (this.isPhasing() && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level().isClientSide && this.isPhasing()) {
            for (int i = 0; i < 2; i++) {
                this.level().addParticle(
                        ParticleTypes.SOUL_FIRE_FLAME,
                        this.getRandomX(0.6D),
                        this.getRandomY(),
                        this.getRandomZ(0.6D),
                        0.0D, 0.02D, 0.0D
                );
            }
        }
    }

    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        float shardSpeed = 1.5F;
        Vec3 origin = this.getEyePosition();
        Vec3 targetPos = target.getEyePosition();

        double distance = Math.sqrt(origin.distanceToSqr(targetPos));
        double travelTicks = distance / shardSpeed;
        Vec3 predicted = targetPos.add(target.getDeltaMovement().scale(travelTicks * 0.6D));

        Vec3 direction = predicted.subtract(origin);

        FrostShard shard = new FrostShard(this, direction.normalize(), this.level());
        shard.setPos(origin.x, origin.y - 0.1D, origin.z);

        float inaccuracy = Mth.clamp(1.5F - velocity, 0.5F, 1.5F);
        shard.shoot(direction.x, direction.y, direction.z, shardSpeed, inaccuracy);

        this.playSound(SoundEvents.GLASS_BREAK, 1.0F, 1.2F / (this.getRandom().nextFloat() * 0.2F + 0.9F));
        this.level().addFreshEntity(shard);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return UOSounds.FROST_REVENANT_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.STRAY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.STRAY_DEATH;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            controller.tick("idle");
            controller.tickOneShot("attack");
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            controller.playOneShot("attack");
        } else {
            super.handleEntityEvent(id);
        }
    }
}
