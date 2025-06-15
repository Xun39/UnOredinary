package net.xun.unoredinary.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.xun.lib.common.api.util.BlockPosUtils;
import net.xun.unoredinary.registry.UOSounds;

public class GlaciumBlock extends Block {
    private final boolean canMelt;

    public GlaciumBlock(Properties properties, boolean canMelt) {
        super(properties);
        this.canMelt = canMelt;
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
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (level.isClientSide)
            return;

        if (!canMelt)
            return;

        if (level.dimension() == Level.NETHER) {
            ((ServerLevel) level).sendParticles(ParticleTypes.CLOUD, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.5, 0.5, 0.5, 0.0);

            level.playSound(null, pos, UOSounds.GLACIUM_BLOCK_EVAPORATE.get(), SoundSource.BLOCKS);
            level.removeBlock(pos, false);
        } else {
            level.scheduleTick(pos, this, 20);
        }
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
