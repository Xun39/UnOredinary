package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.entity.FrostRevenantEntity;
import net.xun.unoredinary.entity.FrostZombieEntity;
import net.xun.unoredinary.entity.FrozenCataEntity;
import net.xun.unoredinary.entity.projectile.FrostShardEntity;

public class UOEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, UnOredinary.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<FrostZombieEntity>> FROST_ZOMBIE = ENTITY_TYPES.register("frost_zombie",
            () -> EntityType.Builder.of(FrostZombieEntity::new, MobCategory.MONSTER).sized(0.6F, 2F).build("frost_zombie")
    );
    public static final DeferredHolder<EntityType<?>, EntityType<FrostRevenantEntity>> FROST_REVENANT = ENTITY_TYPES.register("frost_revenant",
            () -> EntityType.Builder.of(FrostRevenantEntity::new, MobCategory.MONSTER).sized(0.6F, 2.1F).build("frost_revenant")
    );
    public static final DeferredHolder<EntityType<?>, EntityType<FrozenCataEntity>> FROZEN_CATA = ENTITY_TYPES.register("frozen_cata",
            () -> EntityType.Builder.of(FrozenCataEntity::new, MobCategory.MONSTER).sized(2.0F, 4.0F).build("frozen_cata")
    );

    // Projectiles
    public static final DeferredHolder<EntityType<?>, EntityType<FrostShardEntity>> FROST_SHARD = ENTITY_TYPES.register("frost_shard",
            () -> EntityType.Builder.<FrostShardEntity>of(FrostShardEntity::new, MobCategory.MISC).sized(0.3F, 0.3F).build("frost_shard"));
}
