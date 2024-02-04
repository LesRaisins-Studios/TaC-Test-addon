package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.animation.DVL10AnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;

import static com.tac.guns.client.gunskin.ModelComponent.*;


public class dvl10_animation extends SkinAnimationModel {
    public dvl10_animation(){
        this.extraOffset.put(BARREL,new Vector3d(0,0,9*0.0625));
    }

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        DVL10AnimationController controller = DVL10AnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), DVL10AnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
            renderComponentWithOffset(stack, matrices, renderBuffer, light, overlay,skin,BARREL);
        }
        matrices.popPose();

        renderSinglePart(BOLT,DVL10AnimationController.INDEX_BOLT,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(BULLET,DVL10AnimationController.INDEX_BULLET,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), DVL10AnimationController.INDEX_MAG, transformType, matrices);
            renderMag(stack, matrices, renderBuffer, light, overlay,skin);
        }
        matrices.popPose();

        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
    }

    public void renderSinglePart(IModelComponent component, int index, ItemCameraTransforms.TransformType transformType, ItemStack stack, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay, GunAnimationController controller, GunSkin skin) {
        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), index, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, component), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();
    }

    private void renderComponentWithOffset(ItemStack stack, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay, GunSkin skin, IModelComponent modelComponent) {
        if (this.extraOffset.containsKey(modelComponent)) {
            Vector3d x = this.extraOffset.get(modelComponent);
            matrices.pushPose();
            matrices.translate(x.x(), x.y(), x.z());
            RenderUtil.renderModel(this.getModelComponent(skin, modelComponent), stack, matrices, renderBuffer, light, overlay);
            matrices.translate(-x.x(), -x.y(), -x.z());
            matrices.popPose();
        } else {
            RenderUtil.renderModel(this.getModelComponent(skin, modelComponent), stack, matrices, renderBuffer, light, overlay);
        }

    }

}
