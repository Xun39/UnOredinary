package net.xun.unoredinary.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.xun.unoredinary.client.animation.FrozenCataAnimation;
import net.xun.unoredinary.entity.FrozenCataEntity;

public class FrozenCataModel<T extends FrozenCataEntity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart upper_body;
	private final ModelPart chest;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart left_lower_arm;
	private final ModelPart left_hand;
	private final ModelPart spear;
	private final ModelPart spearhead;
	private final ModelPart speartail;
	private final ModelPart right_arm;
	private final ModelPart right_lower_arm;
	private final ModelPart right_hand;
	private final ModelPart lower_body;
	private final ModelPart right_leg;
	private final ModelPart right_lower_leg;
	private final ModelPart right_foot;
	private final ModelPart left_leg;
	private final ModelPart left_lower_leg;
	private final ModelPart left_foot;

	public FrozenCataModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.upper_body = this.body.getChild("upper_body");
		this.chest = this.upper_body.getChild("chest");
		this.head = this.chest.getChild("head");
		this.left_arm = this.chest.getChild("left_arm");
		this.left_lower_arm = this.left_arm.getChild("left_lower_arm");
		this.left_hand = this.left_lower_arm.getChild("left_hand");
		this.spear = this.left_hand.getChild("spear");
		this.spearhead = this.spear.getChild("spearhead");
		this.speartail = this.spear.getChild("speartail");
		this.right_arm = this.chest.getChild("right_arm");
		this.right_lower_arm = this.right_arm.getChild("right_lower_arm");
		this.right_hand = this.right_lower_arm.getChild("right_hand");
		this.lower_body = this.body.getChild("lower_body");
		this.right_leg = this.lower_body.getChild("right_leg");
		this.right_lower_leg = this.right_leg.getChild("right_lower_leg");
		this.right_foot = this.right_lower_leg.getChild("right_foot");
		this.left_leg = this.lower_body.getChild("left_leg");
		this.left_lower_leg = this.left_leg.getChild("left_lower_leg");
		this.left_foot = this.left_lower_leg.getChild("left_foot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(98, 94).addBox(-13.0F, -17.0F, -7.0F, 26.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -34.0F, 0.0F));

		PartDefinition chest = upper_body.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 94).addBox(-17.0F, -16.0F, -1.0F, 33.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.0F, -7.0F));

		PartDefinition head = chest.addOrReplaceChild("head", CubeListBuilder.create().texOffs(98, 124).addBox(-8.0F, -25.0F, -9.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 7.0F));

		PartDefinition left_arm = chest.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 148).addBox(0.0F, -5.0F, -6.0F, 11.0F, 20.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(16.0F, -10.0F, 7.0F));

		PartDefinition left_lower_arm = left_arm.addOrReplaceChild("left_lower_arm", CubeListBuilder.create().texOffs(40, 180).addBox(-4.5F, -3.0F, -5.0F, 9.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 18.0F, 0.0F));

		PartDefinition left_hand = left_lower_arm.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(188, 0).addBox(-5.0F, -3.0F, -5.5F, 10.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition spear = left_hand.addOrReplaceChild("spear", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -40.0F, 4.0F, 4.0F, 90.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition spearhead = spear.addOrReplaceChild("spearhead", CubeListBuilder.create().texOffs(60, 126).addBox(3.5F, 33.5F, -54.0F, 5.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 126).addBox(2.0F, 36.5F, -60.0F, 8.0F, 0.0F, 22.0F, new CubeDeformation(0.0F))
				.texOffs(162, 124).addBox(6.0F, 31.5F, -60.0F, 0.0F, 9.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -39.0F, 0.0F));

		PartDefinition speartail = spear.addOrReplaceChild("speartail", CubeListBuilder.create(), PartPose.offset(-6.0F, -39.0F, 0.0F));

		PartDefinition cube_r1 = speartail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(188, 40).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(188, 55).addBox(-3.0F, -3.0F, -8.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 36.0F, 50.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition right_arm = chest.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(46, 148).addBox(-11.0F, -5.0F, -6.0F, 11.0F, 20.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-17.0F, -10.0F, 7.0F));

		PartDefinition right_lower_arm = right_arm.addOrReplaceChild("right_lower_arm", CubeListBuilder.create().texOffs(78, 184).addBox(-4.5F, -3.0F, -5.0F, 9.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 18.0F, 0.0F));

		PartDefinition right_hand = right_lower_arm.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(188, 20).addBox(-5.0F, -3.0F, -5.5F, 10.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition lower_body = body.addOrReplaceChild("lower_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg = lower_body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(93, 156).addBox(-11.0F, -3.0F, -1.0F, 11.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -32.0F, -5.0F));

		PartDefinition right_lower_leg = right_leg.addOrReplaceChild("right_lower_leg", CubeListBuilder.create().texOffs(178, 94).addBox(-10.5F, -1.0F, 0.0F, 10.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition right_foot = right_lower_leg.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(117, 184).addBox(-4.5F, 1.0F, -12.0F, 10.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 14.0F, 10.0F));

		PartDefinition left_leg = lower_body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(140, 156).addBox(0.0F, -3.0F, -6.0F, 11.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -32.0F, 0.0F));

		PartDefinition left_lower_leg = left_leg.addOrReplaceChild("left_lower_leg", CubeListBuilder.create().texOffs(0, 180).addBox(0.5F, -2.0F, -2.0F, 10.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -3.0F));

		PartDefinition left_foot = left_lower_leg.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(165, 184).addBox(-5.5F, 0.0F, -12.0F, 10.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 14.0F, 8.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(FrozenCataEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		//this.applyHeadRotation(netHeadYaw, headPitch);

		this.animate(entity.controller.getState("idle"), FrozenCataAnimation.IDLE, ageInTicks, 1f);
		this.animate(entity.controller.getState("arm_up"), FrozenCataAnimation.ARM_UP, ageInTicks, 1f);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30f, 30f);
		headPitch = Mth.clamp(headPitch, -25f, 45);

		this.head.yRot = headYaw * ((float)Math.PI / 180f);
		this.head.xRot = headPitch *  ((float)Math.PI / 180f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.root.render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}