package xun.unoredinary.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import xun.unoredinary.registry.UOSounds;

public class SolariteOreBlock extends DropExperienceBlock {

    public SolariteOreBlock(IntProvider xpRange, Properties properties) {
        super(xpRange, properties.sound(UOSounds.SOLARITE)
                .lightLevel((state) -> 13));
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {

        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().hotFloor(), 2.0F);
            entity.setDeltaMovement(-0.5 + Math.random(), -0.5 + Math.random(), -0.5 + Math.random());
        }

        super.stepOn(level, pos, state, entity);
    }
}
