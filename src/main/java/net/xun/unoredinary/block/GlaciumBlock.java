package net.xun.unoredinary.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.unoredinary.registry.UOSounds;

public class GlaciumBlock extends DropExperienceBlock {
    public GlaciumBlock(IntProvider xpRange, Properties properties) {
        super(xpRange, properties);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int affectedRadius = 2;

        BlockPosUtils.getDisc(pos, affectedRadius).forEach(adjacentPos -> {
            if (!level.getBlockState(adjacentPos).is(Blocks.WATER))
                return;

            level.setBlock(adjacentPos, Blocks.ICE.defaultBlockState(), Block.UPDATE_ALL);
        });
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextDouble() < 0.3) {
            level.addParticle(
                    ParticleTypes.SNOWFLAKE,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + random.nextDouble(),
                    pos.getZ() + random.nextDouble(),
                    0.0, 0.1, 0.0
            );
        }
    }
}
