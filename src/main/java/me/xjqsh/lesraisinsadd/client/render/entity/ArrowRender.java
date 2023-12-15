package me.xjqsh.lesraisinsadd.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.xjqsh.lesraisinsadd.entity.CrossBowArrowEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class ArrowRender extends EntityRenderer<CrossBowArrowEntity> {
    public ArrowRender(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(CrossBowArrowEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light)
    {
        if(!entity.getProjectile().isVisible() || entity.tickCount <= 1)
        {
            return;
        }

        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180F));
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(entityYaw));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(entity.xRot));
        Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItem(), ItemCameraTransforms.TransformType.NONE, 15728880, OverlayTexture.NO_OVERLAY, matrixStack, renderTypeBuffer);
        matrixStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(CrossBowArrowEntity p_110775_1_) {
        return null;
    }
}
