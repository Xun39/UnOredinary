package xun.unoredinary.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;

import java.util.function.Supplier;

public class UOSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }

    public static final Supplier<SoundEvent> HEMOCRYLIC_BREAK = registerSoundEvent("block.hemocrylic.break");
    public static final Supplier<SoundEvent> HEMOCRYLIC_STEP = registerSoundEvent("block.hemocrylic.step");
    public static final Supplier<SoundEvent> HEMOCRYLIC_PLACE = registerSoundEvent("block.hemocrylic.place");
    public static final Supplier<SoundEvent> HEMOCRYLIC_HIT = registerSoundEvent("block.hemocrylic.hit");
    public static final Supplier<SoundEvent> HEMOCRYLIC_FALL = registerSoundEvent("block.hemocrylic.fall");

    public static final Supplier<SoundEvent> HEMOCRYLIC_BLOCK_EVAPORATE = registerSoundEvent("block.hemocrylic_block.evaporate");

    public static final DeferredSoundType HEMOCRYLIC_BLOCK_SOUNDS = new DeferredSoundType(1f, 1f,
            UOSounds.HEMOCRYLIC_BREAK, UOSounds.HEMOCRYLIC_STEP, UOSounds.HEMOCRYLIC_PLACE,
            UOSounds.HEMOCRYLIC_HIT, UOSounds.HEMOCRYLIC_FALL);

    public static final Supplier<SoundEvent> SOLARITE_BREAK = registerSoundEvent("block.solarite.break");
    public static final Supplier<SoundEvent> SOLARITE_STEP = registerSoundEvent("block.solarite.step");
    public static final Supplier<SoundEvent> SOLARITE_PLACE = registerSoundEvent("block.solarite.place");
    public static final Supplier<SoundEvent> SOLARITE_HIT = registerSoundEvent("block.solarite.hit");
    public static final Supplier<SoundEvent> SOLARITE_FALL = registerSoundEvent("block.solarite.fall");

    public static final DeferredSoundType SOLARITE = new DeferredSoundType(1f, 1f,
            UOSounds.SOLARITE_BREAK, UOSounds.SOLARITE_STEP, UOSounds.SOLARITE_PLACE,
            UOSounds.SOLARITE_HIT, UOSounds.SOLARITE_FALL);

    public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_FROSTEEL = registerSoundEvent("item.armor.equip_frosteel");

    public static final Supplier<SoundEvent> FROST_ZOMBIE_AMBIENT = registerSoundEvent("entity.frost_zombie.ambient");
    public static final Supplier<SoundEvent> FROST_ZOMBIE_HURT = registerSoundEvent("entity.frost_zombie.hurt");
    public static final Supplier<SoundEvent> FROST_ZOMBIE_DEATH = registerSoundEvent("entity.frost_zombie.death");
    public static final Supplier<SoundEvent> FROST_ZOMBIE_STEP = registerSoundEvent("entity.frost_zombie.step");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(UnOredinary.modLoc(name)));
    }
}
