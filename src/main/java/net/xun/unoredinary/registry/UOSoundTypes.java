package net.xun.unoredinary.registry;

import net.neoforged.neoforge.common.util.DeferredSoundType;

public class UOSoundTypes {

    public static final DeferredSoundType GLACIUM = new DeferredSoundType(
            1F,
            1F,
            UOSounds.GLACIUM_BLOCK_BREAK,
            UOSounds.GLACIUM_BLOCK_STEP,
            UOSounds.GLACIUM_BLOCK_PLACE,
            UOSounds.GLACIUM_BLOCK_HIT,
            UOSounds.GLACIUM_BLOCK_FALL
    );

    public static final DeferredSoundType SUNSTONE = new DeferredSoundType(
            1F,
            1F,
            UOSounds.SUNSTONE_BREAK,
            UOSounds.SUNSTONE_STEP,
            UOSounds.SUNSTONE_PLACE,
            UOSounds.SUNSTONE_HIT,
            UOSounds.SUNSTONE_FALL
    );
}
