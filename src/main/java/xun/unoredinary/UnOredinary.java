package xun.unoredinary;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import xun.unoredinary.registry.*;

@Mod(UnOredinary.MOD_ID)
public class UnOredinary {

    public static final String MOD_ID = "unoredinary";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation modLoc(String path) { return ResourceLocation.fromNamespaceAndPath(MOD_ID, path); }

    public UnOredinary(IEventBus modEventBus, ModContainer modContainer) {

        IEventBus neoforgeEventBus = NeoForge.EVENT_BUS;

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        ModStructureTypes.register(modEventBus);
        ModEntityTypes.register(modEventBus);

        neoforgeEventBus.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
