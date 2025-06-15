package net.xun.unoredinary.client.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.client.particle.RimeParticle;
import net.xun.unoredinary.client.particle.SubzeroFrostParticle;
import net.xun.unoredinary.registry.UOParticleTypes;

@EventBusSubscriber(modid = UnOredinary.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistrationEvents {

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(UOParticleTypes.RIME.get(), RimeParticle.Provider::new);
        event.registerSpriteSet(UOParticleTypes.SUBZERO_FROST.get(), SubzeroFrostParticle.Provider::new);
    }
}
