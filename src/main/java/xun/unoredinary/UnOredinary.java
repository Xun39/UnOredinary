package xun.unoredinary;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import xun.unoredinary.content.item.armor.UOArmors;
import xun.unoredinary.content.item.tool.UOTools;
import xun.unoredinary.registry.*;

@Mod(UnOredinary.MOD_ID)
public class UnOredinary {

	public static final String MOD_ID = "unoredinary";
	private static final Logger LOGGER = LogUtils.getLogger();

	public static ResourceLocation modLoc(String path) { return ResourceLocation.fromNamespaceAndPath(MOD_ID, path); }

	public UnOredinary(IEventBus modEventBus, ModContainer modContainer) {

		IEventBus neoforgeEventBus = NeoForge.EVENT_BUS;

		UOTools.register();
		UOArmors.register();

		UOItems.register(modEventBus);
		UOBlocks.register(modEventBus);
		UOSounds.register(modEventBus);
		UOCreativeModeTabs.register(modEventBus);

		UOArmorMaterials.register(modEventBus);

		UOStructureTypes.register(modEventBus);
		UOEntityTypes.register(modEventBus);

		UOGlobalLootModifierSerializers.register(modEventBus);

		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(this::addCreative);

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