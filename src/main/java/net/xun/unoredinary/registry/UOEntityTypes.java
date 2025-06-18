package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.entity.FrostZombieEntity;

public class UOEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, UnOredinary.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<FrostZombieEntity>> FROST_ZOMBIE = ENTITY_TYPES.register("frost_zombie",
            () -> EntityType.Builder.of(FrostZombieEntity::new, MobCategory.MONSTER).sized(0.6F, 2F).build("frost_zombie")
    );
}
