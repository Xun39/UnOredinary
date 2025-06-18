package net.xun.unoredinary.client.event;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.client.model.UOModelLayers;
import net.xun.unoredinary.client.model.entity.FrostZombieModel;
import net.xun.unoredinary.client.model.renderer.FrostZombieRenderer;
import net.xun.unoredinary.client.particle.RimeParticle;
import net.xun.unoredinary.client.particle.SubzeroFrostParticle;
import net.xun.unoredinary.registry.UOEntityTypes;
import net.xun.unoredinary.registry.UOParticleTypes;

@EventBusSubscriber(modid = UnOredinary.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistrationEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(UOEntityTypes.FROST_ZOMBIE.get(), FrostZombieRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(UOParticleTypes.RIME.get(), RimeParticle.Provider::new);
        event.registerSpriteSet(UOParticleTypes.SUBZERO_FROST.get(), SubzeroFrostParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        LayerDefinition humanoidLayerdefinition = LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);
        LayerDefinition outerArmorLayerdefinition = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32);
        LayerDefinition innerArmorLayerdefinition = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32);

        event.registerLayerDefinition(UOModelLayers.FROST_ZOMBIE, () -> FrostZombieModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(UOModelLayers.FROST_ZOMBIE_OUTER_LAYER, () -> FrostZombieModel.createBodyLayer(new CubeDeformation(0.25F)));
        event.registerLayerDefinition(UOModelLayers.FROST_ZOMBIE_INNER_ARMOR, () -> innerArmorLayerdefinition);
        event.registerLayerDefinition(UOModelLayers.FROST_ZOMBIE_OUTER_ARMOR, () -> outerArmorLayerdefinition);
    }
}
