package net.xun.unoredinary.client.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.block.entity.TransenchantingTableBlockEntity;

@OnlyIn(Dist.CLIENT)
public class TransenchantTableRenderer implements BlockEntityRenderer<TransenchantingTableBlockEntity> {
    public static final Material BOOK_LOCATION = new Material(
            InventoryMenu.BLOCK_ATLAS, CommonUtils.modLoc("entity/transenchanting_table_book")
    );

    private final BookModel bookModel;
    private final ItemRenderer itemRenderer;

    public TransenchantTableRenderer(BlockEntityRendererProvider.Context context) {
        this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(TransenchantingTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        renderBook(
                blockEntity,
                partialTick,
                poseStack,
                bufferSource,
                packedLight,
                packedOverlay
        );

        renderItem(
                blockEntity.getInventory().getStackInSlot(2),
                blockEntity,
                partialTick,
                poseStack,
                bufferSource,
                packedLight
        );
    }

    private void renderBook(TransenchantingTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.75F, 0.5F);
        float f = (float)blockEntity.time + partialTick;
        poseStack.translate(0.0F, 0.1F + Mth.sin(f * 0.1F) * 0.01F, 0.0F);
        float f1 = blockEntity.rot - blockEntity.oRot;

        while (f1 >= (float) Math.PI) {
            f1 -= (float) (Math.PI * 2);
        }

        while (f1 < (float) -Math.PI) {
            f1 += (float) (Math.PI * 2);
        }

        float f2 = blockEntity.oRot + f1 * partialTick;
        poseStack.mulPose(Axis.YP.rotation(-f2));
        poseStack.mulPose(Axis.ZP.rotationDegrees(80.0F));
        float f3 = Mth.lerp(partialTick, blockEntity.oFlip, blockEntity.flip);
        float f4 = Mth.frac(f3 + 0.25F) * 1.6F - 0.3F;
        float f5 = Mth.frac(f3 + 0.75F) * 1.6F - 0.3F;
        float f6 = Mth.lerp(partialTick, blockEntity.oOpen, blockEntity.open);
        this.bookModel.setupAnim(f, Mth.clamp(f4, 0.0F, 1.0F), Mth.clamp(f5, 0.0F, 1.0F), f6);
        VertexConsumer vertexconsumer = BOOK_LOCATION.buffer(bufferSource, RenderType::entitySolid);
        this.bookModel.render(poseStack, vertexconsumer, packedLight, packedOverlay, -1);
        poseStack.popPose();
    }

    private void renderItem(ItemStack stack, TransenchantingTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (stack.isEmpty())
            return;

        poseStack.pushPose();

        poseStack.translate(0.5F, 1.0F, 0.5F);

        BakedModel model = Minecraft.getInstance()
                .getItemRenderer()
                .getModel(stack, blockEntity.getLevel(), null, 0);

        float hoverOffset =
                Mth.sin((blockEntity.time + partialTick) / 10.0F) * 0.1F + 0.1F;

        float modelYScale =
                model.getTransforms()
                        .getTransform(ItemDisplayContext.GROUND)
                        .scale.y();

        float openness =
                Mth.lerp(partialTick, blockEntity.oOpen, blockEntity.open);

        poseStack.translate(
                0.0,
                hoverOffset + 0.25F * modelYScale * openness
                        - 0.15F * (1.0F - openness),
                0.0
        );

        float scale = openness * 0.8F + 0.2F;

        poseStack.scale(scale, scale, scale);

        poseStack.mulPose(
                Axis.YP.rotationDegrees(
                        ((blockEntity.time + partialTick) * 3F)
                )
        );

        this.itemRenderer.render(
                stack,
                ItemDisplayContext.GROUND,
                false,
                poseStack,
                bufferSource,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                model
        );

        poseStack.popPose();
    }

    @Override
    public net.minecraft.world.phys.AABB getRenderBoundingBox(TransenchantingTableBlockEntity blockEntity) {
        net.minecraft.core.BlockPos pos = blockEntity.getBlockPos();
        return new net.minecraft.world.phys.AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1., pos.getY() + 1.5, pos.getZ() + 1.);
    }
}
