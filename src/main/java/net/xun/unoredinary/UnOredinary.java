package net.xun.unoredinary;

import net.xun.lib.common.api.ModSetup;
import net.xun.unoredinary.registry.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(UnOredinary.MOD_ID)
public class UnOredinary {
    public static final String MOD_ID = "unoredinary";
    private static final Logger LOGGER = LogUtils.getLogger();

    public UnOredinary(IEventBus modEventBus, ModContainer modContainer) {

        ModSetup.setModId(MOD_ID);

        UOTools.registerTools();
        UOArmors.registerArmors();
        UOItems.ITEMS.register(modEventBus);
        UOBlocks.BLOCKS.register(modEventBus);
        UOSounds.SOUND_EVENTS.register(modEventBus);
        UOMobEffects.MOB_EFFECTS.register(modEventBus);
        UOParticleTypes.PARTICLE_TYPES.register(modEventBus);
        UOArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        UOCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        UOStructureTypes.STRUCTURE_TYPES.register(modEventBus);
        UOStructurePieceTypes.STRUCTURE_PIECE_TYPES.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, UnOredinaryConfig.SPEC);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
