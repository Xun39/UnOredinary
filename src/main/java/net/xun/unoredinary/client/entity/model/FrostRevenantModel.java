package net.xun.unoredinary.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.xun.unoredinary.client.entity.animation.FrostRevenantAnimation;
import net.xun.unoredinary.entity.FrostRevenant;

public class FrostRevenantModel<T extends FrostRevenant> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart cloak;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public FrostRevenantModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.body = this.root.getChild("body");
		this.cloak = this.root.getChild("cloak");
		this.left_arm = this.root.getChild("left_arm");
		this.right_arm = this.root.getChild("right_arm");
		this.left_leg = this.root.getChild("left_leg");
		this.right_leg = this.root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition cloak = root.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 20.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(0, 31).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(20, 49).mirror().addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(52, 57).addBox(-1.0F, 8.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.25F))
				.texOffs(54, 50).addBox(0.0F, -2.0F, -2.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.25F))
				.texOffs(57, 48).mirror().addBox(-1.0F, -4.0F, -0.5F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(20, 49).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(52, 57).mirror().addBox(-2.0F, 8.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(54, 50).mirror().addBox(-2.0F, -2.0F, -2.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(57, 48).addBox(-1.0F, -4.0F, -0.5F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-1.0F, 0.0F, -2.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(8, 49).mirror().addBox(-1.0F, 0.0F, -2.1F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(2.0F, -12.0F, 0.1F));

		PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, 0.0F, -2.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 49).addBox(-2.0F, 0.0F, -2.1F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(-2.0F, -12.0F, 0.1F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animateWalk(FrostRevenantAnimation.WALK, limbSwing, limbSwingAmount, 2.0F, 2.5F);
		this.animate(entity.idleAnimationState, FrostRevenantAnimation.IDLE, ageInTicks, 1f);
		this.animate(entity.attackAnimationState, FrostRevenantAnimation.ATTACK, ageInTicks, 1f);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30f, 30f);
		headPitch = Mth.clamp(headPitch, -25f, 45);

		this.head.yRot = headYaw * ((float)Math.PI / 180f);
		this.head.xRot = headPitch *  ((float)Math.PI / 180f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		root.render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
