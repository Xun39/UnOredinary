package net.xun.unoredinary.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.client.UOModelLayers;
import net.xun.unoredinary.client.entity.model.FrostShardModel;
import net.xun.unoredinary.entity.projectile.FrostShard;

public class FrostShardRenderer extends EntityRenderer<FrostShard> {
    private final FrostShardModel<FrostShard> model;

    public FrostShardRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new FrostShardModel<>(context.bakeLayer(UOModelLayers.FROST_SHARD));
    }

    @Override
    public void render(FrostShard entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        float yRot = Mth.lerp(partialTick, entity.yRotO, entity.getYRot());
        float xRot = Mth.lerp(partialTick, entity.xRotO, entity.getXRot());

        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(180 + xRot));

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(getTextureLocation(entity)));
        this.model.renderToBuffer(
                poseStack,
                consumer,
                LightTexture.FULL_BRIGHT,
                OverlayTexture.NO_OVERLAY,
                0xDDFFFFFF
        );
        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FrostShard entity) {
        return CommonUtils.modLoc("textures/entity/frost_shard.png");
    }
}
