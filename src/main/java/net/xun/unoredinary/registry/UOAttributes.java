package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;

public class UOAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, UnOredinary.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> COLD_DAMAGE = ATTRIBUTES.register(
            "cold_damage",
            () -> new RangedAttribute("unoredinary.attribute.cold_damage", 0.0, 0.0, 2048.0).setSyncable(true)
    );
}
