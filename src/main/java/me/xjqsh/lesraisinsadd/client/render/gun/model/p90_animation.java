package me.xjqsh.lesraisinsadd.client.render.gun.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.render.animation.P90AnimationController;
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
public class p90_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        P90AnimationController controller = P90AnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), P90AnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
            renderBarrel(stack,matrices,renderBuffer,light,overlay,skin);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), P90AnimationController.INDEX_MAGAZINE, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, MAG_STANDARD), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

//        matrices.pushPose();
//        {
//            controller.applySpecialModelTransform(getModelComponent(skin, BODY), P90AnimationController.INDEX_PULL, transformType, matrices);
//            RenderUtil.renderModel(getModelComponent(skin, PULL), stack, matrices, renderBuffer, light, overlay);
//        }
//        matrices.popPose();

        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
    }
}
