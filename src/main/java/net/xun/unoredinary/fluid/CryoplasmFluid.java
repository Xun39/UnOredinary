package net.xun.unoredinary.fluid;

import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.registry.UOFluidTypes;
import net.xun.unoredinary.registry.UOFluids;
import net.xun.unoredinary.registry.UOItems;

public abstract class CryoplasmFluid extends BaseFlowingFluid {
    protected static final BaseFlowingFluid.Properties PROPERTIES =
            new BaseFlowingFluid.Properties(UOFluidTypes.CRYOPLASM, UOFluids.CRYOPLASM, UOFluids.FLOWING_CRYOPLASM)
                    .block(UOBlocks.CRYOPLASM)
                    .bucket(UOItems.CRYOPLASM_BUCKET)
                    .tickRate(15)
                    .slopeFindDistance(4)
                    .levelDecreasePerBlock(1);

    public CryoplasmFluid() {
        super(PROPERTIES);
    }

    public static class Flowing extends CryoplasmFluid {
        public Flowing() {
            this.registerDefaultState((this.getStateDefinition().any()).setValue(LEVEL, 7));
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return false;
        }
    }

    public static class Source extends CryoplasmFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
