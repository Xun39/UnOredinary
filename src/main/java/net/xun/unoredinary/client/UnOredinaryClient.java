package net.xun.unoredinary.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.xun.lib.common.api.client.gui.XunConfigScreen;
import net.xun.lib.common.api.config.XunConfigTheme;
import net.xun.unoredinary.UnOredinary;

@Mod(value = UnOredinary.MOD_ID, dist = Dist.CLIENT)
public class UnOredinaryClient {
    public UnOredinaryClient(IEventBus modEventBus, ModContainer modContainer) {
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> ((container, modListScreen) ->
                        new XunConfigScreen(modListScreen, UnOredinary.MOD_ID, XunConfigTheme.DEFAULT)
                )
        );
    }
}
