package net.xun.unoredinary.client.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.block.entity.TransenchantingTableBlockEntity;
import net.xun.unoredinary.registry.UOParticleTypes;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class TransenchantTableRenderer implements BlockEntityRenderer<TransenchantingTableBlockEntity> {
    public static final Material BOOK_LOCATION = new Material(
            InventoryMenu.BLOCK_ATLAS, CommonUtils.modLoc("entity/transenchanting_table_book")
    );

    private static final float INPUT_RADIUS = 0.30F;
    private static final float INPUT_HEIGHT = 1.0F;

    private static final float CENTER_HEIGHT = 0.95F;

    private static final float OUTPUT_HEIGHT = 1.25F;

    private static final float INPUT_SCALE = 0.55F;
    private static final float OUTPUT_SCALE = 0.85F;

    private final BookModel bookModel;
    private final ItemRenderer itemRenderer;

    public TransenchantTableRenderer(BlockEntityRendererProvider.Context context) {
        this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(TransenchantingTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        renderBook(blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
        renderItems(blockEntity, partialTick, poseStack, bufferSource, packedLight);

        if (!blockEntity.isOutputReady()) {
            renderParticleStream(blockEntity, partialTick);
        }
    }

    private void renderBook(TransenchantingTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.75F, 0.5F);
        float f = (float) blockEntity.time + partialTick;
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

    private Vector3f getItemPosition(
            ItemStack stack, TransenchantingTableBlockEntity blockEntity, float partialTick,
            float normalX, float normalY, float normalZ
    ) {
        float time = blockEntity.time + partialTick;
        float openness = Mth.lerp(partialTick, blockEntity.oOpen, blockEntity.open);

        BakedModel model = itemRenderer.getModel(stack, blockEntity.getLevel(), null, 0);
        float modelYScale = model.getTransforms().getTransform(ItemDisplayContext.GROUND).scale.y();

        float x = Mth.lerp(openness, 0.0F, normalX);
        float y = Mth.lerp(openness, CENTER_HEIGHT, normalY);
        float z = Mth.lerp(openness, 0.0F, normalZ);

        float hoverOffset = Mth.sin(time / 10.0F) * 0.1F + 0.1F;
        y += hoverOffset + 0.25F * modelYScale * openness - 0.15F * (1.0F - openness);

        return new Vector3f(x, y, z);
    }

    private void renderItems(TransenchantingTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        ItemStack transenchanter = blockEntity.getInventory().getStackInSlot(TransenchantingTableBlockEntity.TRANSENCHANTER_SLOT);
        ItemStack target = blockEntity.getInventory().getStackInSlot(TransenchantingTableBlockEntity.TARGET_SLOT);
        ItemStack output = blockEntity.getInventory().getStackInSlot(TransenchantingTableBlockEntity.OUTPUT_SLOT);

        float time = blockEntity.time + partialTick;

        float orbit = time * 0.025F;

        // transenchanter
        float transenchanterX = Mth.cos(orbit) * INPUT_RADIUS;
        float transenchanterZ = Mth.sin(orbit) * INPUT_RADIUS;
        renderFloatingItem(
                transenchanter, blockEntity, partialTick, poseStack, bufferSource, packedLight,
                transenchanterX, INPUT_HEIGHT, transenchanterZ, INPUT_SCALE
        );

        // target
        float targetX = Mth.cos(orbit + Mth.PI) * INPUT_RADIUS;
        float targetZ = Mth.sin(orbit + Mth.PI) * INPUT_RADIUS;
        renderFloatingItem(
                target, blockEntity, partialTick, poseStack, bufferSource, packedLight,
                targetX, INPUT_HEIGHT, targetZ, INPUT_SCALE
        );

        // output
        if (!output.isEmpty() && blockEntity.isOutputReady()) {
            renderFloatingItem(
                    output, blockEntity, partialTick, poseStack, bufferSource, packedLight,
                    0.0F, OUTPUT_HEIGHT, 0.0F, OUTPUT_SCALE
            );
        }
    }

    private void renderFloatingItem(
            ItemStack stack, TransenchantingTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
            float normalX, float normalY, float normalZ, float scale
    ) {
        if (!(blockEntity.open != 0.0F || blockEntity.oOpen != 0.0F))
            return;

        float time = blockEntity.time + partialTick;
        Vector3f pos = getItemPosition(stack, blockEntity, partialTick, normalX, normalY, normalZ);

        poseStack.pushPose();
        poseStack.translate(0.5F + pos.x(), pos.y(), 0.5F + pos.z());
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Axis.YP.rotationDegrees(time * 3.0F));

        BakedModel model = itemRenderer.getModel(stack, blockEntity.getLevel(), null, 0);
        itemRenderer.render(
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

    private void renderParticleStream(TransenchantingTableBlockEntity blockEntity, float partialTick) {
        if (!(blockEntity.open != 0.0F || blockEntity.oOpen != 0.0F))
            return;

        ItemStack transenchanter = blockEntity.getInventory().getStackInSlot(TransenchantingTableBlockEntity.TRANSENCHANTER_SLOT);
        ItemStack target = blockEntity.getInventory().getStackInSlot(TransenchantingTableBlockEntity.TARGET_SLOT);

        if (transenchanter.isEmpty() || target.isEmpty()) {
            return;
        }

        Level level = blockEntity.getLevel();
        if (level == null || !level.isClientSide) {
            return;
        }

        float time = blockEntity.time + partialTick;
        float orbit = time * 0.025F;

        float transenchanterNormalX = Mth.cos(orbit) * INPUT_RADIUS;
        float transenchanterNormalZ = Mth.sin(orbit) * INPUT_RADIUS;

        float targetNormalX = Mth.cos(orbit + Mth.PI) * INPUT_RADIUS;
        float targetNormalZ = Mth.sin(orbit + Mth.PI) * INPUT_RADIUS;

        Vector3f startPos = getItemPosition(transenchanter, blockEntity, partialTick, transenchanterNormalX, INPUT_HEIGHT, transenchanterNormalZ);
        Vector3f endPos = getItemPosition(target, blockEntity, partialTick, targetNormalX, INPUT_HEIGHT, targetNormalZ);

        if (level.getRandom().nextFloat() > 0.03F) {
            return;
        }

        BlockPos blockPos = blockEntity.getBlockPos();
        spawnEnchantGlyph(level, blockPos, startPos.x, startPos.y, startPos.z, endPos.x, endPos.y, endPos.z);
        spawnEnchantGlyph(level, blockPos, endPos.x, endPos.y, endPos.z, startPos.x, startPos.y, startPos.z);
    }

    private void spawnEnchantGlyph(Level level, BlockPos pos, float fromX, float fromY, float fromZ, float toX, float toY, float toZ) {
        double spawnX = pos.getX() + 0.5 + fromX;
        double spawnY = pos.getY() + fromY;
        double spawnZ = pos.getZ() + 0.5 + fromZ;

        double dx = (toX - fromX);
        double dy = (toY - fromY);
        double dz = (toZ - fromZ);

        level.addParticle(UOParticleTypes.TRANSENCHANT.get(), spawnX, spawnY, spawnZ, dx, dy, dz);
    }

    @Override
    public net.minecraft.world.phys.AABB getRenderBoundingBox(TransenchantingTableBlockEntity blockEntity) {
        net.minecraft.core.BlockPos pos = blockEntity.getBlockPos();
        return new net.minecraft.world.phys.AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1., pos.getY() + 1.5, pos.getZ() + 1.);
    }
}
