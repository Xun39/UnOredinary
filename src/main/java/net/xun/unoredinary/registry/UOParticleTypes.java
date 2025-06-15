package net.xun.unoredinary.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;

public class UOParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, UnOredinary.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RIME = register("rime", false);

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String name, boolean overrideLimiter) {
        return PARTICLE_TYPES.register(name, () -> new SimpleParticleType(overrideLimiter));
    }
}
