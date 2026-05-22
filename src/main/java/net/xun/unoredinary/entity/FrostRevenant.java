package net.xun.unoredinary.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.xun.lib.common.api.util.BlockPosUtils;

import java.util.List;

public class FrostRevenant extends FrostZombie {
    private static final double FREEZING_AURA_RADIUS = 3.5D;

    public FrostRevenant(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 50.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MAX_HEALTH, 36.0F)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (level().isClientSide)
            return;

        if (tickCount % 20 == 0) {
            applyFreezingAura();
        }
    }

    private void applyFreezingAura() {
        AABB affectingArea = BlockPosUtils.createAABBFromCenter(this.blockPosition(), FREEZING_AURA_RADIUS);

        List<LivingEntity> entities = level().getEntitiesOfClass(
                LivingEntity.class,
                affectingArea,
                entity -> entity.isAlive() && entity != this
        );

        for (LivingEntity entity : entities) {

            if (!entity.canFreeze())
                continue;

            entity.setTicksFrozen(Math.max(entity.getTicksFrozen(), entity.getTicksFrozen() + 60));

            if (entity.invulnerableTime > 0)
                continue;

            entity.hurt(damageSources().freeze(), 0.5F);
        }
    }
}
