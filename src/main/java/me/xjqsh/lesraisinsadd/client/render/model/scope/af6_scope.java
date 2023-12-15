package me.xjqsh.lesraisinsadd.client.render.model.scope;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tac.guns.Config;
import com.tac.guns.client.handler.AimingHandler;
import com.tac.guns.client.handler.GunRenderingHandler;
import com.tac.guns.client.handler.command.ScopeEditor;
import com.tac.guns.client.handler.command.data.ScopeData;
import com.tac.guns.client.render.gun.IOverrideModel;
import com.tac.guns.client.util.RenderUtil;
import com.tac.guns.item.attachment.IAttachment;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.client.listener.ModelCaches;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Objects;

public class af6_scope implements IOverrideModel {
    private static final ResourceLocation RED_DOT_RETICLE =
            new ResourceLocation(Reference.MOD_ID, "textures/items/af6/af6_scope.png");

    public af6_scope() {
    }

    public void render(float partialTicks, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, int overlay) {
        matrixStack.pushPose();
        {
            double prog;
            double transition;
            double zScale;
            if (Config.CLIENT.display.redDotSquishUpdate.get() && transformType.firstPerson() && entity.equals(Minecraft.getInstance().player)) {
                prog = 0.0;
                if (AimingHandler.get().getNormalisedAdsProgress() > 0.725) {
                    prog = (AimingHandler.get().getNormalisedAdsProgress() - 0.725) * 3.63;
                }

                transition = 1.0 - Math.pow(1.0 - prog, 2.0);
                zScale = 0.05 + 0.95 * (1.0 - transition);
                matrixStack.scale(1.0F, 1.0F, (float) zScale);
            } else if (transformType.firstPerson() && entity.equals(Minecraft.getInstance().player)) {
                prog = 0.0;
                if (AimingHandler.get().getNormalisedAdsProgress() > 0.725) {
                    prog = (AimingHandler.get().getNormalisedAdsProgress() - 0.725) * 1.1875;
                    transition = 1.0 - Math.pow(1.0 - prog, 2.0);
                    zScale = 0.05 + 0.95 * (1.0 - transition);
                    matrixStack.scale(1.0F, 1.0F, (float) zScale);
                } else {
                    transition = 1.0 - Math.pow(1.0 - prog, 2.0);
                    zScale = 0.05 + 0.95 * (1.0 - transition);
                    matrixStack.scale(1.0F, 1.0F, (float) zScale);
                }
            }

            RenderUtil.getItemStackColor(stack, parent, IAttachment.Type.SCOPE_BODY_COLOR, 0);
            matrixStack.translate(0.0, 0.074, -0.1375);
            RenderUtil.renderModel(ModelCaches.AF6.getModel(), parent, matrixStack, renderTypeBuffer, light, overlay);
            RenderUtil.renderModel(ModelCaches.AF6_LIGHT.getModel(), parent, matrixStack, renderTypeBuffer, 15728880, overlay);
        }
        matrixStack.popPose();

        matrixStack.translate(0.0, 0.034, -0.1375);
        if (transformType.firstPerson() && entity.equals(Minecraft.getInstance().player)) {
            ScopeData scopeData = ScopeEditor.get().getScopeData() != null && Objects.equals(ScopeEditor.get().getScopeData().getTagName(), "coyote") ? ScopeEditor.get().getScopeData() : new ScopeData("");
            matrixStack.pushPose();
            Matrix4f matrix = matrixStack.last().pose();
            Matrix3f normal = matrixStack.last().normal();
            float size = 0.075F;
            matrixStack.translate((double)(-size / 2.0F) - 0.0035 + scopeData.getReticleXMod(), (0.6859999999999999 + scopeData.getReticleYMod()) * 0.0625, (0.9945 + scopeData.getReticleZMod()) * 0.0625);
            double invertProgress = 1.0 - AimingHandler.get().getNormalisedAdsProgress();
            matrixStack.translate(-0.04 * invertProgress, 0.01 * invertProgress, 0.0);
            double scale = 0.7824947999999998 + (double)scopeData.getReticleSizeMod();
            matrixStack.translate((double)(size / 2.0F), (double)(size / 2.0F), 0.0);
            matrixStack.translate(-((double)size / scale) / 2.0, -((double)size / scale) / 2.0, 0.0);
            matrixStack.translate(0.0, 0.0, 1.0E-4);

            int reticleGlowColor = RenderUtil.getItemStackColor(stack, parent, IAttachment.Type.SCOPE_RETICLE_COLOR, 1);
            float red = (float)(reticleGlowColor >> 16 & 255) / 255.0F;
            float green = (float)(reticleGlowColor >> 8 & 255) / 255.0F;
            float blue = (float)(reticleGlowColor & 255) / 255.0F;
            float alpha = (float)(AimingHandler.get().getNormalisedAdsProgress());
            IVertexBuilder builder = renderTypeBuffer.getBuffer(RenderType.entityTranslucentCull (RED_DOT_RETICLE));

            GunRenderingHandler.get().applyDelayedSwayTransforms(matrixStack, Minecraft.getInstance().player, partialTicks, -1.0F);
            GunRenderingHandler.get().applyBobbingTransforms(matrixStack, true, 1.25F);
            GunRenderingHandler.get().applyNoiseMovementTransform(matrixStack, -1.5F);
            GunRenderingHandler.get().applyJumpingTransforms(matrixStack, partialTicks, -0.8F);
            matrixStack.translate(0.0, 0.0, -0.35);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(GunRenderingHandler.get().newSwayYaw));
            matrixStack.mulPose(Vector3f.ZN.rotationDegrees(GunRenderingHandler.get().newSwayPitch));
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(GunRenderingHandler.get().recoilLift * GunRenderingHandler.get().recoilReduction * 1.15F));
            matrixStack.translate(0.0, 0.0, 0.35);
            builder.vertex(matrix, 0.0F, (float)((double)size / scale), 0.0F).color(red, green, blue, alpha).uv(0.0F, 0.9375F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            builder.vertex(matrix, 0.0F, 0.0F, 0.0F).color(red, green, blue, alpha).uv(0.0F, 0.0F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            builder.vertex(matrix, (float)((double)size / scale), 0.0F, 0.0F).color(red, green, blue, alpha).uv(0.9375F, 0.0F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            builder.vertex(matrix, (float)((double)size / scale), (float)((double)size / scale), 0.0F).color(red, green, blue, alpha).uv(0.9375F, 0.9375F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            matrixStack.popPose();
        }

    }
}
