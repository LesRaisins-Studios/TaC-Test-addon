package me.xjqsh.lesraisinsadd.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tac.guns.client.handler.AimingHandler;
import me.xjqsh.lesraisinsadd.client.listener.ModelCaches;
import me.xjqsh.lesraisinsadd.entity.X26HookEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class X26HookRenderer extends EntityRenderer<X26HookEntity> {
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("lesraisinsadd:textures/entity/x26_ammo.png");

    public X26HookRenderer(EntityRendererManager p_i46175_1_) {
        super(p_i46175_1_);
    }

    public void render(X26HookEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int light) {
        PlayerEntity player = entity.getPlayerOwner();
        if (player != null) {
            stack.pushPose();
            {
                int i = player.getMainArm() == HandSide.RIGHT ? 1 : -1;

                float aimp = 1 - AimingHandler.get().getAimProgress(player, partialTicks);

                float f2 = MathHelper.lerp(partialTicks, player.yBodyRotO, player.yBodyRot) * ((float) Math.PI / 180F);

                double d0 = MathHelper.sin(f2);
                double d1 = MathHelper.cos(f2);
                double d2 = (double) i * 0.35D;
                double x1;
                double y1;
                double z1;

                if ((this.entityRenderDispatcher.options == null || this.entityRenderDispatcher.options.getCameraType().isFirstPerson()) && player == Minecraft.getInstance().player) {
                    double d7 = this.entityRenderDispatcher.options.fov;
                    d7 = d7 / 100.0D;

                    Vector3d vector3d = new Vector3d((double) i * -0.1d * aimp * d7, -0.175d * d7, 0.45d);

                    vector3d = vector3d.xRot(-MathHelper.lerp(0, player.xRotO, player.xRot) * ((float) Math.PI / 180F));
                    vector3d = vector3d.yRot(-MathHelper.lerp(0, player.yRotO, player.yRot) * ((float) Math.PI / 180F));

                    x1 = MathHelper.lerp(partialTicks, player.xo, player.getX()) + vector3d.x;
                    y1 = MathHelper.lerp(partialTicks, player.yo, player.getY()) + vector3d.y;
                    z1 = MathHelper.lerp(partialTicks, player.zo, player.getZ()) + vector3d.z;

                    y1 += player.getEyeHeight();
                } else {
                    x1 = MathHelper.lerp(partialTicks, player.xo, player.getX()) - d1 * d2 - d0 * 0.8D;
                    y1 = player.yo + (double) player.getEyeHeight() + (player.getY() - player.yo) * (double) partialTicks - 0.45D;
                    z1 = MathHelper.lerp(partialTicks, player.zo, player.getZ()) - d0 * d2 + d1 * 0.8D;
                    y1 += player.isCrouching() ? -0.1875F : 0.0F;
                }

                double x2 = MathHelper.lerp(partialTicks, entity.xo, entity.getX());
                double y2 = MathHelper.lerp(partialTicks, entity.yo, entity.getY()) + 0.25D;
                double z2 = MathHelper.lerp(partialTicks, entity.zo, entity.getZ());

                float a = (float) (x1 - x2);
                float b = (float) (y1 - y2);
                float c = (float) (z1 - z2);

                IVertexBuilder builder = buffer.getBuffer(RenderType.lines());
                Matrix4f matrix4f1 = stack.last().pose();

                for (int k = 0; k < 16; ++k) {
                    stringVertex(a, b, c, builder, matrix4f1, fraction(k, 16));
                    stringVertex(a, b, c, builder, matrix4f1, fraction(k + 1, 16));
                }
            }
            stack.popPose();
            super.render(entity, entityYaw, partialTicks, stack, buffer, light);



            IBakedModel model = ModelCaches.X26.getModel();
            if(model!=null){
                IVertexBuilder builder2;
                builder2 = ItemRenderer.getFoilBufferDirect(buffer, RenderType.solid(),
                        true, false);

                stack.pushPose();
                {
                    stack.mulPose(Vector3f.YP.rotationDegrees(180F));
                    stack.mulPose(Vector3f.YP.rotationDegrees(entityYaw));
                    stack.mulPose(Vector3f.XP.rotationDegrees(entity.xRot));
                    stack.translate(-0.5, -0.375, -0.5);

                    Random random = new Random();
                    Direction[] var9 = Direction.values();

                    for (Direction direction : var9) {
                        random.setSeed(42L);
                            renderQuads(stack, builder2, model.getQuads(null, direction, random),
                                light, OverlayTexture.NO_OVERLAY);
                    }

                    random.setSeed(42L);
                        renderQuads(stack, builder2, model.getQuads(null, null, random),
                            light, OverlayTexture.NO_OVERLAY);
                }
                stack.popPose();

            }
        }
    }

    private static float fraction(int a, int b) {
        return (float)a / (float)b;
    }

    private static void stringVertex(float a, float b, float c, IVertexBuilder builder, Matrix4f matrix4f, float p) {
        builder.vertex(matrix4f, a * p, b * (p * p + p) * 0.5F + 0.125F, c * p).color(0, 0, 0, 255).endVertex();
    }

    private static void renderQuads(MatrixStack matrixStack, IVertexBuilder buffer, List<BakedQuad> quads, int light, int overlay) {
        MatrixStack.Entry entry = matrixStack.last();

        for (BakedQuad quad : quads) {
            buffer.addVertexData(entry, quad, 1, 1, 1, 1, light, overlay, false);
        }

    }

    public ResourceLocation getTextureLocation(X26HookEntity p_110775_1_) {
        return TEXTURE_LOCATION;
    }

}