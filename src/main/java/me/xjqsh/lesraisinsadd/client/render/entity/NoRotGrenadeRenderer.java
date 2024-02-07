package me.xjqsh.lesraisinsadd.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.xjqsh.lesraisinsadd.entity.throwable.DecoyGrenadeEntity;
import me.xjqsh.lesraisinsadd.entity.throwable.ThrowableItemEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class NoRotGrenadeRenderer extends EntityRenderer<ThrowableItemEntity<?>> {
    public NoRotGrenadeRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    public void render(ThrowableItemEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, int light) {
        stack.pushPose();
        {
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));


            stack.mulPose(Vector3f.YP.rotationDegrees(entityYaw));
            float rotation = entity.prevRotation + (entity.rotation - entity.prevRotation) * partialTicks;

            stack.translate(0.0, 0.15, 0.0);
            stack.mulPose(Vector3f.XP.rotationDegrees(-rotation));
            stack.translate(0.0, -0.15, 0.0);

            stack.translate(0, entity.getDimensions(Pose.STANDING).height / 2, 0);
            stack.mulPose(Vector3f.ZP.rotationDegrees(-90F));
            stack.translate(0, -entity.getDimensions(Pose.STANDING).height / 2, 0);

            if(entity instanceof DecoyGrenadeEntity){
                stack.translate(0.0, -0.25, 0.0);
            }else {
                stack.translate(0.0, -0.5, 0.0);
            }

            Minecraft.getInstance().getItemRenderer()
                    .renderStatic(entity.getItem(), ItemCameraTransforms.TransformType.NONE, light,
                            OverlayTexture.NO_OVERLAY, stack, renderTypeBuffer);
        }
        stack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(ThrowableItemEntity p_110775_1_) {
        return null;
    }
}
