package net.xun.unoredinary.registry;

import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.xun.unoredinary.UnOredinary;

public class UOFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, UnOredinary.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> CRYOPLASM =
            FLUID_TYPES.register("cryoplasm", () ->
                    new FluidType(
                            FluidType.Properties.create()
                                    .density(1500)
                                    .viscosity(2000)
                                    .temperature(50)
                                    .canPushEntity(true)
                                    .canSwim(false)
                                    .canDrown(false)
                    )
            );
}
