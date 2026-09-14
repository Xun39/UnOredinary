package net.xun.unoredinary.entity.projectile;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.xun.unoredinary.registry.UOEntityTypes;
import net.xun.unoredinary.registry.UOParticleTypes;
import org.jetbrains.annotations.Nullable;

public class FrostShard extends AbstractHurtingProjectile {
    public FrostShard(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public FrostShard(LivingEntity owner, Vec3 movement, Level level) {
        super(UOEntityTypes.FROST_SHARD.get(), owner, movement, level);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!level().isClientSide) discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (level().isClientSide) return;
        Entity target = result.getEntity();

        if (getOwner() instanceof LivingEntity owner) {
            DamageSource damageSource = damageSources().mobProjectile(this, owner);
            target.hurt(damageSource, 4.0F);
        }
    }

    @Override
    protected @Nullable ParticleOptions getTrailParticle() {
        return UOParticleTypes.RIME.get();
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }
}
