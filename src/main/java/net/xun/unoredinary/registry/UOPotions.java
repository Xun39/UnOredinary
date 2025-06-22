package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;

public class UOPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, UnOredinary.MOD_ID);

    public static final DeferredHolder<Potion, Potion> FROSTBITTEN = POTIONS.register("frostbitten", () -> new Potion(new MobEffectInstance(UOMobEffects.FROSTED_EFFECT, 900)));
    public static final DeferredHolder<Potion, Potion> LONG_FROSTBITTEN = POTIONS.register("long_frostbitten", () -> new Potion("frostbitten", new MobEffectInstance(UOMobEffects.FROSTED_EFFECT, 1800)));
    public static final DeferredHolder<Potion, Potion> STRONG_FROSTBITTEN = POTIONS.register("strong_frostbitten", () -> new Potion("frostbitten", new MobEffectInstance(UOMobEffects.FROSTED_EFFECT, 423, 1)));
}
