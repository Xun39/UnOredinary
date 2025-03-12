package xun.unoredinary.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.entity.FrostZombieEntity;

import java.util.function.Supplier;

public class UOEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { ENTITY_TYPES.register(eventBus); }

    public static final Supplier<EntityType<FrostZombieEntity>> FROST_ZOMBIE =
            ENTITY_TYPES.register("frost_zombie", () -> EntityType.Builder.of(FrostZombieEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 2f).build("frost_zombie"));
}
