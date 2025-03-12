package xun.unoredinary.client.event;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.client.entity.model.FrostZombieModel;
import xun.unoredinary.client.entity.renderer.FrostZombieRenderer;
import xun.unoredinary.registry.UOEntityTypes;
import xun.unoredinary.util.UOModelLayers;

@EventBusSubscriber(modid = UnOredinary.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    public static final CubeDeformation OUTER_ARMOR_DEFORMATION = new CubeDeformation(1.0F);
    public static final CubeDeformation INNER_ARMOR_DEFORMATION = new CubeDeformation(0.5F);

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(UOEntityTypes.FROST_ZOMBIE.get(), FrostZombieRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        LayerDefinition humanoidLayerdefinition = LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);
        LayerDefinition outerArmorLayerdefinition = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(OUTER_ARMOR_DEFORMATION), 64, 32);
        LayerDefinition innerArmorLayerdefinition = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(INNER_ARMOR_DEFORMATION), 64, 32);

        event.registerLayerDefinition(UOModelLayers.FROST_ZOMBIE, () -> FrostZombieModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(UOModelLayers.FROST_ZOMBIE_OUTER_LAYER, () -> FrostZombieModel.createBodyLayer(new CubeDeformation(0.25F)));
        event.registerLayerDefinition(UOModelLayers.FROST_ZOMBIE_INNER_ARMOR, () -> innerArmorLayerdefinition);
        event.registerLayerDefinition(UOModelLayers.FROST_ZOMBIE_OUTER_ARMOR, () -> outerArmorLayerdefinition);
    }
}