package net.xun.unoredinary.client.entity.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.client.UOModelLayers;
import net.xun.unoredinary.client.entity.model.FrostRevenantModel;
import net.xun.unoredinary.entity.FrostRevenant;

public class FrostRevenantRenderer extends MobRenderer<FrostRevenant, FrostRevenantModel<FrostRevenant>> {
    public FrostRevenantRenderer(EntityRendererProvider.Context context) {
        super(context, new FrostRevenantModel<>(context.bakeLayer(UOModelLayers.FROST_REVENANT)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(FrostRevenant entity) {
        return CommonUtils.modLoc("textures/entity/frost_revenant/frost_revenant.png");
    }

    @Override
    public void render(FrostRevenant entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        if (entity.isPhasing()) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.35F);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
