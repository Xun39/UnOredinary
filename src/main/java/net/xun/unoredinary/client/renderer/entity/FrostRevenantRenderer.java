package net.xun.unoredinary.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.client.model.layer.UOModelLayers;
import net.xun.unoredinary.client.model.FrostRevenantModel;
import net.xun.unoredinary.entity.FrostRevenant;
import org.jetbrains.annotations.Nullable;

public class FrostRevenantRenderer extends MobRenderer<FrostRevenant, FrostRevenantModel<FrostRevenant>> {
    public FrostRevenantRenderer(EntityRendererProvider.Context context) {
        super(context, new FrostRevenantModel<>(context.bakeLayer(UOModelLayers.FROST_REVENANT)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(FrostRevenant entity) {
        return entity.isPhasing()
                ? CommonUtils.modLoc("textures/entity/frost_revenant/frost_revenant_phasing.png")
                : CommonUtils.modLoc("textures/entity/frost_revenant/frost_revenant.png");
    }

    @Override
    protected @Nullable RenderType getRenderType(FrostRevenant livingEntity, boolean bodyVisible, boolean translucent, boolean glowing) {
        ResourceLocation texture = this.getTextureLocation(livingEntity);
        if (livingEntity.isPhasing()) {
            return RenderType.entityTranslucent(texture);
        }

        return super.getRenderType(livingEntity, bodyVisible, translucent, glowing);
    }

    @Override
    public void render(FrostRevenant entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
