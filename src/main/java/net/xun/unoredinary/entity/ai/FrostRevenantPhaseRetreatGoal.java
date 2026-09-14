package net.xun.unoredinary.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.xun.unoredinary.entity.FrostRevenant;

import java.util.EnumSet;

public class FrostRevenantPhaseRetreatGoal extends Goal {
    private static final double TRIGGER_DISTANCE_SQR = 5.0D * 5.0D;
    private static final double SAFE_DISTANCE_SQR = 12.0D * 12.0D;
    private static final double PHASE_SPEED = 0.35D;
    private static final int MAX_PHASE_TICKS = 60;
    private static final int COOLDOWN_TICKS = 100;

    private final FrostRevenant revenant;
    private LivingEntity threat;
    private int phaseTicks;
    private int losLostTicks;
    private int cooldown;

    private Vec3 lastAirPos;
    private boolean hitBlock;

    public FrostRevenantPhaseRetreatGoal(FrostRevenant revenant) {
        this.revenant = revenant;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.TARGET, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        }
        LivingEntity target = this.revenant.getTarget();
        if (target == null || !target.isAlive()) return false;
        if (this.revenant.distanceToSqr(target) > TRIGGER_DISTANCE_SQR) return false;

        this.threat = target;
        return true;
    }

    @Override
    public void start() {
        this.phaseTicks = 0;
        this.hitBlock = false;
        this.lastAirPos = this.revenant.position();
        this.revenant.beginPhase();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.threat == null || !this.threat.isAlive()) return false;
        if (this.phaseTicks >= MAX_PHASE_TICKS) return false;
        if (this.revenant.distanceToSqr(this.threat) >= SAFE_DISTANCE_SQR) return false;

        if (this.phaseTicks >= 8) {
            if (this.revenant.hasLineOfSight(this.threat)) {
                this.losLostTicks = 0;
            } else return ++this.losLostTicks < 3;
        }

        return true;
    }

    @Override
    public void tick() {
        this.phaseTicks++;
        this.revenant.getLookControl().setLookAt(this.threat, 30.0F, 30.0F);

        BlockPos current = this.revenant.blockPosition();
        if (!this.revenant.level().getBlockState(current).getCollisionShape(revenant.level(), current).isEmpty()) {
            this.hitBlock = true;
            return;
        }
        this.lastAirPos = this.revenant.position();

        Vec3 away = this.revenant.position().subtract(this.threat.position());
        Vec3 horizontal = new Vec3(away.x, 0.0D, away.z);
        if (horizontal.lengthSqr() > 1.0E-4D) {
            horizontal = horizontal.normalize().scale(PHASE_SPEED);
            this.revenant.setDeltaMovement(horizontal.x, 0.0D, horizontal.z);
        }
    }

    @Override
    public void stop() {
        if (this.hitBlock && this.lastAirPos != null) {
            this.revenant.teleportTo(this.lastAirPos.x, this.lastAirPos.y, this.lastAirPos.z);
        }
        this.revenant.endPhase();
        this.cooldown = COOLDOWN_TICKS;
        this.threat = null;
        this.lastAirPos = null;
        this.hitBlock = false;
    }
}
