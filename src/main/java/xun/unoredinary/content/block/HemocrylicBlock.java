package xun.unoredinary.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import xun.unoredinary.registry.UOSounds;
import xun.unoredinary.util.BiomeUtils;

public class HemocrylicBlock extends Block {

    private static final int ICE_EFFECT_COOLDOWN_TICKS = 100;

    private final boolean canMelt;

    public HemocrylicBlock(Properties properties, boolean canMelt) {
        super(properties.sound(UOSounds.HEMOCRYLIC_BLOCK_SOUNDS));
        this.canMelt = canMelt;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (isInNether(level)) {
            ((ServerLevel) level).sendParticles(ParticleTypes.CLOUD, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.5, 0.5, 0.5, 0.0);

            level.playSound(null, pos, UOSounds.HEMOCRYLIC_BLOCK_EVAPORATE.get(), SoundSource.BLOCKS, 1f, 1f);
            level.removeBlock(pos, false);

        } else {
            level.scheduleTick(pos, this, 20);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

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

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        long currentTick = level.getGameTime();

        if (currentTick % ICE_EFFECT_COOLDOWN_TICKS == 0) {
            BlockPos.betweenClosed(pos.offset(-2, 0, -2), pos.offset(2, 0, 2)).forEach(adjacentPos -> {

                BlockState adjacentState = level.getBlockState(adjacentPos);

                if (adjacentState.is(Blocks.WATER)) {
                    level.setBlock(adjacentPos, Blocks.ICE.defaultBlockState(), 3);
                }

            });
        }

        if (shouldMelt(level, pos) && !isInNether(level)) {
            level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
        } else {
            level.scheduleTick(pos, this, 20);
        }
    }

    public boolean isInNether(Level level) {
        return level.dimension() == Level.NETHER;
    }

    private boolean shouldMelt(Level level, BlockPos pos) {
        return canMelt && (BiomeUtils.isInHotBiome(level, pos) || isNearLava(level, pos));
    }

    private boolean isNearLava(Level level, BlockPos pos) {
        for (BlockPos nearby : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            if (level.getBlockState(nearby).is(Blocks.LAVA)) {
                return true;
            }
        }
        return false;
    }
}