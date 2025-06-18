package net.xun.unoredinary.client.model.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.client.model.UOModelLayers;
import net.xun.unoredinary.client.model.entity.FrostZombieModel;
import net.xun.unoredinary.entity.FrostZombieEntity;

public class FrostZombieOuterLayer extends RenderLayer<FrostZombieEntity, FrostZombieModel> {

    private static final ResourceLocation FROST_ZOMBIE_OUTER_LAYER_LOCATION = CommonUtils.modLoc("textures/entity/frost_zombie/frost_zombie_outer_layer.png");

    private final FrostZombieModel model;

    public FrostZombieOuterLayer(RenderLayerParent<FrostZombieEntity, FrostZombieModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new FrostZombieModel(modelSet.bakeLayer(UOModelLayers.FROST_ZOMBIE_OUTER_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, FrostZombieEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        coloredCutoutModelCopyLayerRender(
                this.getParentModel(),
                this.model,
                FROST_ZOMBIE_OUTER_LAYER_LOCATION,
                poseStack,
                bufferSource,
                packedLight,
                livingEntity,
                limbSwing,
                limbSwingAmount,
                ageInTicks,
                netHeadYaw,
                headPitch,
                partialTick,
                -1
        );
    }
}
