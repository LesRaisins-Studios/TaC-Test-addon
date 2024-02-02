package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.animation.XM8AnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import static com.tac.guns.client.gunskin.ModelComponent.*;

/*
 * Because the revolver has a rotating chamber, we need to render it in a
 * different way than normal items. In this case we are overriding the model.
 */

/**
 * Author: Timeless Development, and associates.
 */
public class xm8_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        XM8AnimationController controller = XM8AnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        renderSinglePart(BODY,XM8AnimationController.INDEX_BODY,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(MAG_STANDARD,XM8AnimationController.INDEX_MAG,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(BOLT,XM8AnimationController.INDEX_BOLT,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(PULL,XM8AnimationController.INDEX_PULL,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);

        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
    }
    private void renderSinglePart(IModelComponent component, int index, ItemCameraTransforms.TransformType transformType, ItemStack stack, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay, GunAnimationController controller, GunSkin skin) {
        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), index, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, component), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();
    }
}
