package xun.unoredinary.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;

import java.util.function.Supplier;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }

    public static final Supplier<SoundEvent> HEMOCRYLIC_BLOCK_BREAK = registerSoundEvent("block.hemocrylic_block.break");
    public static final Supplier<SoundEvent> HEMOCRYLIC_BLOCK_STEP = registerSoundEvent("block.hemocrylic_block.step");
    public static final Supplier<SoundEvent> HEMOCRYLIC_BLOCK_PLACE = registerSoundEvent("block.hemocrylic_block.place");
    public static final Supplier<SoundEvent> HEMOCRYLIC_BLOCK_HIT = registerSoundEvent("block.hemocrylic_block.hit");
    public static final Supplier<SoundEvent> HEMOCRYLIC_BLOCK_FALL = registerSoundEvent("block.hemocrylic_block.fall");

    public static final Supplier<SoundEvent> HEMOCRYLIC_BLOCK_EVAPORATE = registerSoundEvent("block.hemocrylic_block.evaporate");

    public static final DeferredSoundType HEMOCRYLIC_BLOCK_SOUNDS = new DeferredSoundType(1f, 1f,
            ModSounds.HEMOCRYLIC_BLOCK_BREAK, ModSounds.HEMOCRYLIC_BLOCK_STEP, ModSounds.HEMOCRYLIC_BLOCK_PLACE,
            ModSounds.HEMOCRYLIC_BLOCK_HIT, ModSounds.HEMOCRYLIC_BLOCK_FALL);


    public static final Supplier<SoundEvent> FROST_ZOMBIE_AMBIENT = registerSoundEvent("entity.frost_zombie.ambient");
    public static final Supplier<SoundEvent> FROST_ZOMBIE_HURT = registerSoundEvent("entity.frost_zombie.hurt");
    public static final Supplier<SoundEvent> FROST_ZOMBIE_DEATH = registerSoundEvent("entity.frost_zombie.death");
    public static final Supplier<SoundEvent> FROST_ZOMBIE_STEP = registerSoundEvent("entity.frost_zombie.step");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(UnOredinary.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
