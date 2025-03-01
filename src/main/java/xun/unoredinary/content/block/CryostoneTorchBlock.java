package xun.unoredinary.content.block;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import xun.unoredinary.client.particle.options.ModDustParticleOptions;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class CryostoneTorchBlock extends BaseTorchBlock {

    public static final MapCodec<CryostoneTorchBlock> CODEC = simpleCodec(CryostoneTorchBlock::new);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final Map<BlockGetter, List<CryostoneTorchBlock.Toggle>> RECENT_TOGGLES = new WeakHashMap<>();
    public static final int RECENT_TOGGLE_TIMER = 60;
    public static final int MAX_RECENT_TOGGLES = 8;
    public static final int RESTART_DELAY = 160;
    private static final int TOGGLE_DELAY = 2;

    @Override
    public MapCodec<? extends CryostoneTorchBlock> codec() {
        return CODEC;
    }

    public CryostoneTorchBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(true)));
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        for (Direction direction : Direction.values()) {
            level.updateNeighborsAt(pos.relative(direction), this);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!isMoving) {
            for (Direction direction : Direction.values()) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }
        }
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(LIT) && Direction.UP != side ? 15 : 0;
    }

    protected boolean hasNeighborSignal(Level level, BlockPos pos, BlockState state) {
        return level.hasSignal(pos.below(), Direction.DOWN);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean flag = this.hasNeighborSignal(level, pos, state);
        List<CryostoneTorchBlock.Toggle> list = RECENT_TOGGLES.get(level);

        while (list != null && !list.isEmpty() && level.getGameTime() - list.get(0).when > 60L) {
            list.remove(0);
        }

        if (state.getValue(LIT)) {
            if (flag) {
                level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3);
                if (isToggledTooFrequently(level, pos, true)) {
                    level.levelEvent(1502, pos, 0);
                    level.scheduleTick(pos, level.getBlockState(pos).getBlock(), 160);
                }
            }
        } else if (!flag && !isToggledTooFrequently(level, pos, false)) {
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 3);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (state.getValue(LIT) == this.hasNeighborSignal(level, pos, state) && !level.getBlockTicks().willTickThisTick(pos, this)) {
            level.scheduleTick(pos, this, 2);
        }
    }

    @Override
    protected int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return side == Direction.DOWN ? blockState.getSignal(blockAccess, pos, side) : 0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double d0 = (double)pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            double d1 = (double)pos.getY() + 0.7 + (random.nextDouble() - 0.5) * 0.2;
            double d2 = (double)pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            level.addParticle(ModDustParticleOptions.CRYOSTONE, d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    private static boolean isToggledTooFrequently(Level level, BlockPos pos, boolean logToggle) {
        List<CryostoneTorchBlock.Toggle> list = RECENT_TOGGLES.computeIfAbsent(level, getter -> Lists.newArrayList());
        if (logToggle) {
            list.add(new CryostoneTorchBlock.Toggle(pos.immutable(), level.getGameTime()));
        }

        int i = 0;

        for (CryostoneTorchBlock.Toggle cryostonetorchblock$toggle : list) {
            if (cryostonetorchblock$toggle.pos.equals(pos)) {
                if (++i >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    public static class Toggle {
        final BlockPos pos;
        final long when;

        public Toggle(BlockPos pos, long when) {
            this.pos = pos;
            this.when = when;
        }
    }
}
