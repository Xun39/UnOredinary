package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.content.effect.FrostedEffect;

public class UOMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, UnOredinary.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> FROSTED_EFFECT = MOB_EFFECTS.register("frosted", FrostedEffect::new);
}
