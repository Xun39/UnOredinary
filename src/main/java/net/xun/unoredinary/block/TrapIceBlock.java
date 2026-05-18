package net.xun.unoredinary.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class TrapIceBlock extends Block {

    public TrapIceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

        if (level.isClientSide)
            return;

        if (!(entity instanceof Player player))
            return;

        if (player.isCreative())
            return;

        Vec3 motion = player.getDeltaMovement();
        player.setDeltaMovement(0, motion.y, 0);

        player.setSprinting(false);
        player.fallDistance = 0;

        player.hurtMarked = true;

        if (!state.isAir())
            level.destroyBlock(pos, false);
    }
}
