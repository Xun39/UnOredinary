package net.xun.unoredinary.client.renderer.entity;

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
import net.minecraft.world.phys.Vec3;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.client.model.layer.UOModelLayers;
import net.xun.unoredinary.client.model.FrostShardModel;
import net.xun.unoredinary.entity.projectile.FrostShard;

public class FrostShardRenderer extends EntityRenderer<FrostShard> {
    private final FrostShardModel<FrostShard> model;

    public FrostShardRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new FrostShardModel<>(context.bakeLayer(UOModelLayers.FROST_SHARD));
        this.shadowRadius = 0.0F;
    }

    @Override
    public void render(FrostShard entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        Vec3 velocity = entity.getDeltaMovement();

        if (velocity.lengthSqr() > 1.0E-6) {
            velocity = velocity.normalize();

            float yaw = (float) Mth.atan2(velocity.x, velocity.z) * Mth.RAD_TO_DEG + 180F;
            float pitch = (float) Math.asin(Mth.clamp((float) velocity.y, -1.0F, 1.0F)) * Mth.RAD_TO_DEG;

            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
            poseStack.mulPose(Axis.XP.rotationDegrees(pitch));
        }

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
