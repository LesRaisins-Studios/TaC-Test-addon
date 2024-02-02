package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.animation.AlexAnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;

/*
 * Because the revolver has a rotating chamber, we need to render it in a
 * different way than normal items. In this case we are overriding the model.
 */

/**
 * Author: Timeless Development, and associates.
 */
public class alex_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        AlexAnimationController controller = AlexAnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        renderSinglePart(BODY,AlexAnimationController.INDEX_BODY,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(W_HEAD,AlexAnimationController.INDEX_W_HEAD,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(W_ARM_L,AlexAnimationController.INDEX_W_LA,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(W_ARM_R,AlexAnimationController.INDEX_W_RA,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(W_LEG_L,AlexAnimationController.INDEX_W_LL,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);
        renderSinglePart(W_LEG_R,AlexAnimationController.INDEX_W_RL,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);

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
