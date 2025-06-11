package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.UnOredinary;

public class UOSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, UnOredinary.MOD_ID);

    // Blocks
    public static final DeferredHolder<SoundEvent, SoundEvent> GLACIUM_BLOCK_BREAK = register("block.glacium.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> GLACIUM_BLOCK_STEP = register("block.glacium.step");
    public static final DeferredHolder<SoundEvent, SoundEvent> GLACIUM_BLOCK_PLACE = register("block.glacium.place");
    public static final DeferredHolder<SoundEvent, SoundEvent> GLACIUM_BLOCK_HIT = register("block.glacium.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> GLACIUM_BLOCK_FALL = register("block.glacium.fall");
    public static final DeferredHolder<SoundEvent, SoundEvent> GLACIUM_BLOCK_EVAPORATE = register("block.glacium.evaporate");

    // Items
    public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_CRYOSTEEL = register("item.armor.equip_cryosteel");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(CommonUtils.modLoc(name)));
    }
}
