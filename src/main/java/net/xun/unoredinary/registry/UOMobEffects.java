package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.effect.FrostbiteEffect;
import net.xun.unoredinary.effect.WarmthEffect;

public class UOMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, UnOredinary.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> FROSTBITE_EFFECT = MOB_EFFECTS.register("frostbite", FrostbiteEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> WARMTH_EFFECT = MOB_EFFECTS.register("warmth", WarmthEffect::new);
}
