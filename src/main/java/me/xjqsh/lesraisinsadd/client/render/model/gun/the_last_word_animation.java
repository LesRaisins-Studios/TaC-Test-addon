package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.render.animation.ANGLEAnimationController;
import me.xjqsh.lesraisinsadd.client.render.animation.THELASTWORDAnimationController;
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
public class the_last_word_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        THELASTWORDAnimationController controller = THELASTWORDAnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), THELASTWORDAnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
            RenderUtil.renderModel(getModelComponent(skin, SIGHT), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        renderSinglePart(BULLET,THELASTWORDAnimationController.INDEX_BULLET,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(HAMMER,THELASTWORDAnimationController.INDEX_HAMMER,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_1,THELASTWORDAnimationController.INDEX_MAG_1,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_2,THELASTWORDAnimationController.INDEX_MAG_2,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_3,THELASTWORDAnimationController.INDEX_MAG_3,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(SIGHT_LEFT,THELASTWORDAnimationController.INDEX_SIGHT_LEFT,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(SIGHT_RIGHT,THELASTWORDAnimationController.INDEX_SIGHT_RIGHT,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);

        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
    }

    private void renderSinglePart(IModelComponent component, int index, ItemCameraTransforms.TransformType transformType, ItemStack stack, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay, THELASTWORDAnimationController controller, GunSkin skin) {
        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), index, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, component), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();
    }
}
