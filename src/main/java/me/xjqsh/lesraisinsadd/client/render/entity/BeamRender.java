package me.xjqsh.lesraisinsadd.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.xjqsh.lesraisinsadd.client.listener.ModelCaches;
import me.xjqsh.lesraisinsadd.entity.BeamEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import java.util.List;
import java.util.Random;

public class BeamRender extends EntityRenderer<BeamEntity> {
    private static final RenderType TEST = RenderType.create("test",
            DefaultVertexFormats.BLOCK, 7, 131072, true, false, RenderType.State.builder()
                    .setShadeModelState(new RenderState.ShadeModelState(false))
                    .setLightmapState(new RenderState.LightmapState(true))
                    .setTextureState(new RenderState.TextureState(AtlasTexture.LOCATION_BLOCKS, false, false))
                    .setAlphaState(new RenderState.AlphaState(0.5F))
                    .createCompositeState(true));

    public BeamRender(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(BeamEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light)
    {
        matrixStack.pushPose();
        {
            matrixStack.translate(0,0.125,0);

            BeamEntity.BeamInfo info = entity.getBeamInfo();

            matrixStack.mulPose(Vector3f.YN.rotationDegrees(info.getYr()));
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(info.getXr()));

            matrixStack.mulPose(Vector3f.ZP.rotationDegrees((entity.tickCount*info.getRotateSpeed() / 2f) % 20 * 18));

//            matrixStack.translate(0,0,10-entity.getLength()*5);
//            matrixStack.scale(info.getScaleMultiplier(),info.getScaleMultiplier(),1.0f);
//            matrixStack.scale(entity.calcScale(),entity.calcScale(),10f*entity.getLength());

            matrixStack.translate(0,0,20);
            matrixStack.scale(1,1,40f);

            IBakedModel model = ModelCaches.BEAM.getModel();
            if(model!=null){
                IVertexBuilder builder;
                builder = ItemRenderer.getFoilBufferDirect(buffer, TEST, true, false);


                matrixStack.pushPose();
                {
                    matrixStack.translate(-0.5, -0.5, -0.5);
                    Random random = new Random();
                    Direction[] var9 = Direction.values();

                    for (Direction direction : var9) {
                        random.setSeed(42L);
                        renderQuads(matrixStack, builder, model.getQuads(null, direction, random),
                                15728880, OverlayTexture.NO_OVERLAY, info.getColor());
                    }

                    random.setSeed(42L);
                    renderQuads(matrixStack, builder, model.getQuads(null, null, random),
                            15728880, OverlayTexture.NO_OVERLAY, info.getColor());
                }
                matrixStack.popPose();

            }

        }
        matrixStack.popPose();
    }

    private static void renderQuads(MatrixStack matrixStack, IVertexBuilder buffer, List<BakedQuad> quads, int light, int overlay, int color) {
        MatrixStack.Entry entry = matrixStack.last();

        for (BakedQuad quad : quads) {
            float alpha = 1.0F;

            float red = (float) (color >> 16 & 255) / 255.0F;
            float green = (float) (color >> 8 & 255) / 255.0F;
            float blue = (float) (color & 255) / 255.0F;

            float x = Math.max(red,Math.max(green,blue));

            buffer.addVertexData(entry, quad, red/x, green/x, blue/x, alpha, light, overlay, false);
        }

    }

    @Override
    public ResourceLocation getTextureLocation(BeamEntity p_110775_1_) {
        return new ResourceLocation("lesraisinsadd:textures/entity/beam.png");
    }

    @Override
    public boolean shouldRender(BeamEntity p_225626_1_, ClippingHelper p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
        return true;
    }
}
