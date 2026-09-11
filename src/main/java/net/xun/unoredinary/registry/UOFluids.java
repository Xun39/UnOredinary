package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.fluid.CryoplasmFluid;

public class UOFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, UnOredinary.MOD_ID);

    public static final DeferredHolder<Fluid, CryoplasmFluid> CRYOPLASM = FLUIDS.register("cryoplasm", CryoplasmFluid.Source::new);
    public static final DeferredHolder<Fluid, CryoplasmFluid> FLOWING_CRYOPLASM = FLUIDS.register("flowing_cryoplasm", CryoplasmFluid.Flowing::new);

    public static void registerFluidInteractions() {
        FluidInteractionRegistry.addInteraction(NeoForgeMod.WATER_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
                CRYOPLASM.get().getFluidType(),
                fluidState -> {
                    if (fluidState.isSource())
                        return Blocks.BLUE_ICE.defaultBlockState();
                    else
                        return Blocks.PACKED_ICE.defaultBlockState();
                }
        ));

        FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
                CRYOPLASM.get().getFluidType(),
                fluidState -> {
                    if (fluidState.isSource())
                        return Blocks.CRYING_OBSIDIAN.defaultBlockState();
                    else
                        return Blocks.BASALT.defaultBlockState();
                }
        ));
    }
}
