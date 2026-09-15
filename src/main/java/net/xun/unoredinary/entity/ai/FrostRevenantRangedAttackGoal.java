package net.xun.unoredinary.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.xun.unoredinary.entity.FrostRevenant;

import java.util.EnumSet;

public class FrostRevenantRangedAttackGoal extends Goal {
    private final FrostRevenant mob;
    private final double speedModifier;
    private final int attackInterval;
    private final int warmupTicks;
    private final float attackRadiusSqr;

    private int attackCooldown = -1;
    private int warmupTimer = -1;
    private int seeTime;

    public FrostRevenantRangedAttackGoal(FrostRevenant mob, double speedModifier, int attackInterval, int warmupTicks, float attackRadius) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.attackInterval = attackInterval;
        this.warmupTicks = warmupTicks;
        this.attackRadiusSqr = attackRadius * attackRadius;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        return target != null && target.isAlive() && !this.mob.isPhasing();
    }

    @Override
    public boolean canContinueToUse() {
        return (this.canUse() || !this.mob.getNavigation().isDone()) && !this.mob.isPhasing();
    }

    @Override
    public void stop() {
        this.seeTime = 0;
        this.attackCooldown = -1;
        this.warmupTimer = -1;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target == null) return;

        double distSqr = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
        boolean canSee = this.mob.getSensing().hasLineOfSight(target);

        this.seeTime = canSee ? this.seeTime + 1 : 0;

        if (distSqr <= (double) this.attackRadiusSqr && this.seeTime >= 5) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(target, this.speedModifier);
        }

        this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (this.warmupTimer > 0) {
            this.warmupTimer--;
            if (this.warmupTimer == 0) {
                float distanceFactor = (float) Math.sqrt(distSqr) / (float) Math.sqrt(this.attackRadiusSqr);
                this.mob.performRangedAttack(target, distanceFactor);
                this.attackCooldown = this.attackInterval;
            }
        } else {
            if (this.attackCooldown > 0) {
                this.attackCooldown--;
            }

            if (this.attackCooldown <= 0 && distSqr <= (double) this.attackRadiusSqr && canSee) {
                this.warmupTimer = this.warmupTicks;
                this.mob.level().broadcastEntityEvent(mob, (byte) 4); // temporary
            }
        }
    }
}
