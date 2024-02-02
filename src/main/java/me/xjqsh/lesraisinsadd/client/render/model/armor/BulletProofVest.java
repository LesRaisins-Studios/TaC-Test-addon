package me.xjqsh.lesraisinsadd.client.render.model.armor;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BulletProofVest<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer BulletProofVest;
	private final ModelRenderer main;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;

	public BulletProofVest() {
		super(0);
		texWidth = 64;
		texHeight = 64;

		BulletProofVest = new ModelRenderer(this);
		BulletProofVest.setPos(0.0F, 24.0F, 0.0F);

		main = new ModelRenderer(this);
		main.setPos(0.0F, 0.0F, 0.0F);
		BulletProofVest.addChild(main);
		main.texOffs(37, 31).addBox(-1.9826F, -16.6875F, -4.0622F, 2.0F, 4.0F, 2.0F, -0.1875F, false);
		main.texOffs(44, 27).addBox(-1.9826F, -15.6875F, -4.9372F, 2.0F, 3.0F, 2.0F, -0.25F, false);
		main.texOffs(35, 38).addBox(-3.6977F, -16.6875F, -3.9348F, 2.0F, 4.0F, 2.0F, -0.1875F, false);
		main.texOffs(33, 45).addBox(-3.6977F, -15.6875F, -4.8098F, 2.0F, 3.0F, 2.0F, -0.25F, false);
		main.texOffs(0, 14).addBox(-4.0F, -20.0F, -3.5F, 8.0F, 8.0F, 2.0F, 0.0F, false);
		main.texOffs(0, 0).addBox(-4.0F, -24.0F, 2.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		main.texOffs(24, 11).addBox(4.0F, -18.0F, -2.5F, 1.0F, 6.0F, 5.0F, 0.0F, false);
		main.texOffs(0, 25).addBox(3.4375F, -17.6875F, -2.0F, 3.0F, 4.0F, 4.0F, -0.1875F, false);
		main.texOffs(19, 0).addBox(4.125F, -17.375F, -2.5F, 2.0F, 5.0F, 5.0F, -0.125F, false);
		main.texOffs(16, 20).addBox(-5.0F, -18.0F, -2.5F, 1.0F, 6.0F, 5.0F, 0.0F, false);
		main.texOffs(34, 5).addBox(-6.3125F, -18.0F, -3.75F, 1.0F, 6.0F, 2.0F, -0.1875F, false);
		main.texOffs(37, 14).addBox(-4.0625F, -18.0F, -3.75F, 1.0F, 6.0F, 2.0F, -0.1875F, false);
		main.texOffs(49, 50).addBox(-5.6875F, -12.625F, -3.75F, 2.0F, 1.0F, 2.0F, -0.1875F, false);
		main.texOffs(44, 13).addBox(-5.6875F, -18.0F, -2.125F, 2.0F, 6.0F, 1.0F, -0.1875F, false);
		main.texOffs(44, 38).addBox(-5.6875F, -18.0F, -4.375F, 2.0F, 6.0F, 1.0F, -0.1875F, false);
		main.texOffs(51, 45).addBox(-6.0824F, -15.9713F, -4.6935F, 1.0F, 1.0F, 1.0F, -0.125F, false);
		main.texOffs(11, 25).addBox(-5.8324F, -16.3463F, -1.3185F, 1.0F, 1.0F, 1.0F, -0.125F, false);
		main.texOffs(29, 23).addBox(0.75F, -18.875F, -5.4375F, 3.0F, 4.0F, 3.0F, -0.375F, false);
		main.texOffs(26, 40).addBox(0.75F, -17.6875F, -4.875F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		main.texOffs(28, 31).addBox(0.75F, -18.6875F, -3.875F, 3.0F, 6.0F, 1.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-4.0515F, -15.9357F, -0.8185F);
		main.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.2269F);
		cube_r1.texOffs(46, 33).addBox(-3.0625F, -0.75F, -0.5F, 4.0F, 1.0F, 1.0F, -0.3125F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-5.1739F, -15.8041F, -0.881F);
		main.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.2967F);
		cube_r2.texOffs(44, 0).addBox(-2.0F, -0.5F, -0.4375F, 4.0F, 1.0F, 1.0F, -0.3125F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-6.6486F, -15.9373F, -0.8185F);
		main.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.0349F);
		cube_r3.texOffs(19, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.3125F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(-6.8986F, -15.5623F, -4.1935F);
		main.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -0.0349F);
		cube_r4.texOffs(27, 47).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.3125F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(-5.4239F, -15.4291F, -4.256F);
		main.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.2967F);
		cube_r5.texOffs(47, 3).addBox(-2.0F, -0.5F, -0.4375F, 4.0F, 1.0F, 1.0F, -0.3125F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(-4.3015F, -15.5607F, -4.1935F);
		main.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.2269F);
		cube_r6.texOffs(16, 47).addBox(-3.0625F, -0.75F, -0.5F, 4.0F, 1.0F, 1.0F, -0.3125F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(-3.5126F, -23.2938F, -1.9715F);
		main.addChild(cube_r7);
		setRotationAngle(cube_r7, 3.0369F, 0.0F, -0.3927F);
		cube_r7.texOffs(0, 34).addBox(-1.5F, -3.5F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(-3.9806F, -24.4237F, -1.6819F);
		main.addChild(cube_r8);
		setRotationAngle(cube_r8, 1.885F, 0.0F, -0.3927F);
		cube_r8.texOffs(51, 15).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(-3.9215F, -24.281F, 1.3378F);
		main.addChild(cube_r9);
		setRotationAngle(cube_r9, 1.5533F, 0.0F, -0.3927F);
		cube_r9.texOffs(19, 11).addBox(-1.5F, -2.875F, 0.0F, 3.0F, 3.0F, 1.0F, 0.125F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(-3.9215F, -24.281F, 1.3378F);
		main.addChild(cube_r10);
		setRotationAngle(cube_r10, 1.2566F, 0.0F, -0.3927F);
		cube_r10.texOffs(0, 48).addBox(-1.5F, -2.0F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(-3.2109F, -22.5654F, 2.0487F);
		main.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0524F, 0.0F, -0.3927F);
		cube_r11.texOffs(16, 41).addBox(-1.5F, -2.0F, -0.5F, 3.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(3.5126F, -23.2938F, -1.9715F);
		main.addChild(cube_r12);
		setRotationAngle(cube_r12, 3.0369F, 0.0F, 0.3927F);
		cube_r12.texOffs(0, 41).addBox(-1.5F, -3.5F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(3.9806F, -24.4237F, -1.6819F);
		main.addChild(cube_r13);
		setRotationAngle(cube_r13, 1.885F, 0.0F, 0.3927F);
		cube_r13.texOffs(51, 36).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(3.9215F, -24.281F, 1.3378F);
		main.addChild(cube_r14);
		setRotationAngle(cube_r14, 1.5533F, 0.0F, 0.3927F);
		cube_r14.texOffs(48, 6).addBox(-1.5F, -2.875F, 0.0F, 3.0F, 3.0F, 1.0F, 0.125F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(3.9215F, -24.281F, 1.3378F);
		main.addChild(cube_r15);
		setRotationAngle(cube_r15, 1.2566F, 0.0F, 0.3927F);
		cube_r15.texOffs(9, 50).addBox(-1.5F, -2.0F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(3.2109F, -22.5654F, 2.0487F);
		main.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0524F, 0.0F, 0.3927F);
		cube_r16.texOffs(42, 46).addBox(-1.5F, -2.0F, -0.5F, 3.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(0.0F, -21.4033F, -2.7715F);
		main.addChild(cube_r17);
		setRotationAngle(cube_r17, -0.1571F, 0.0F, 0.0F);
		cube_r17.texOffs(29, 0).addBox(-3.0F, -1.5F, -0.5F, 6.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(0.0F, -23.8125F, 3.6309F);
		main.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.9055F, 0.9798F, 0.8137F);
		cube_r18.texOffs(51, 11).addBox(-0.0799F, -1.0F, 1.0427F, 3.0F, 2.0F, 1.0F, -0.3125F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(0.0F, -23.8125F, 3.6309F);
		main.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.4538F, 0.0F, 0.0F);
		cube_r19.texOffs(42, 23).addBox(-2.0F, -1.0F, -0.2403F, 4.0F, 2.0F, 1.0F, -0.3125F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(0.0F, -23.8125F, 3.6309F);
		main.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.9055F, -0.9798F, -0.8137F);
		cube_r20.texOffs(18, 50).addBox(-2.9201F, -1.0F, 1.0427F, 3.0F, 2.0F, 1.0F, -0.3125F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(-1.875F, -16.5178F, -3.5277F);
		main.addChild(cube_r21);
		setRotationAngle(cube_r21, -0.1134F, 0.0F, 0.0F);
		cube_r21.texOffs(36, 51).addBox(-1.8227F, -0.0062F, -1.4941F, 2.0F, 3.0F, 1.0F, -0.375F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(-1.875F, -16.1875F, -3.5625F);
		main.addChild(cube_r22);
		setRotationAngle(cube_r22, -0.2443F, 0.0F, 0.0F);
		cube_r22.texOffs(51, 25).addBox(-1.8227F, -0.4422F, -1.4825F, 2.0F, 1.0F, 2.0F, -0.375F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(-1.875F, -16.5178F, -3.6527F);
		main.addChild(cube_r23);
		setRotationAngle(cube_r23, -0.1134F, 0.0F, 0.0F);
		cube_r23.texOffs(51, 40).addBox(-0.1076F, -0.0059F, -1.4965F, 2.0F, 3.0F, 1.0F, -0.375F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(-1.875F, -16.1875F, -3.6875F);
		main.addChild(cube_r24);
		setRotationAngle(cube_r24, -0.2443F, 0.0F, 0.0F);
		cube_r24.texOffs(27, 51).addBox(-0.1076F, -0.4416F, -1.4849F, 2.0F, 1.0F, 2.0F, -0.375F, false);

		this.body.addChild(main);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		BulletProofVest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	private void copyModelAngles(ModelRenderer in, ModelRenderer out){
		out.xRot = in.xRot;
		out.yRot = in.yRot;
		out.zRot = in.zRot;
	}

	public BipedModel<T> applySlot(EquipmentSlotType slot){
		main.visible = false;
		switch(slot){
			//这里的CHEST说明我们的盔甲是胸甲，头盔HEAD,护腿LEGS，靴子FEET
			case CHEST:
				main.visible = true;
				break;
			default:
				break;
		}

		return this;
	}

}