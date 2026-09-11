package net.xun.unoredinary.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.xun.unoredinary.world.UOCauldronInteractions;

public class CryoplasmCauldronBlock extends AbstractCauldronBlock {
    public static final MapCodec<CryoplasmCauldronBlock> CODEC = simpleCodec(CryoplasmCauldronBlock::new);

    @Override
    protected MapCodec<CryoplasmCauldronBlock> codec() {
        return CODEC;
    }

    public CryoplasmCauldronBlock(Properties properties) {
        super(properties, UOCauldronInteractions.CRYOPLASM);
    }

    @Override
    protected double getContentHeight(BlockState blockState) {
        return 0.9375;
    }

    @Override
    public boolean isFull(BlockState blockState) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return 3;
    }
}
