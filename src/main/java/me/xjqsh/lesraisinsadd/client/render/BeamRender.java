package me.xjqsh.lesraisinsadd.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.xjqsh.lesraisinsadd.client.render.model.entity.BeamModel;
import me.xjqsh.lesraisinsadd.entity.BeamEntity;
import me.xjqsh.lesraisinsadd.entity.CrossBowArrowEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class BeamRender extends EntityRenderer<BeamEntity> {
    private final EntityModel<BeamEntity> beamEntityEntityModel;
    public BeamRender(EntityRendererManager manager) {
        super(manager);
        beamEntityEntityModel = new BeamModel();
    }

    @Override
    public void render(BeamEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int light)
    {
        matrixStack.pushPose();
        {
            matrixStack.translate(0,0.125,0);

//            matrixStack.mulPose(Vector3f.XP.rotationDegrees(30));
            int x = entity.tickCount%10;
            if(x>=6){
                x=9-x;
            }else if (x>3){
                x=3;
            }

            float s = x*0.33f;
            matrixStack.scale(s,s,5.0f);

            matrixStack.mulPose(Vector3f.ZP.rotationDegrees((entity.tickCount / 2f) % 20 * 18));



//            matrixStack.mulPose(Vector3f.YP.rotationDegrees(entityYaw));
//            matrixStack.mulPose(Vector3f.XP.rotationDegrees(entity.xRot));
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(
                    this.beamEntityEntityModel.renderType(this.getTextureLocation(entity)));
            this.beamEntityEntityModel.renderToBuffer(matrixStack, ivertexbuilder, light,
                    OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        matrixStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(BeamEntity p_110775_1_) {
        return new ResourceLocation("lesraisinsadd:textures/entity/a.png");
    }
}
