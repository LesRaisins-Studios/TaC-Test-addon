package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.animation.THEFIRSTCURSEAnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;


public class the_first_curse_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        THEFIRSTCURSEAnimationController controller = THEFIRSTCURSEAnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), THEFIRSTCURSEAnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        renderSinglePart(BULLET,THEFIRSTCURSEAnimationController.INDEX_BULLET,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_1,THEFIRSTCURSEAnimationController.INDEX_MAG_1,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_2,THEFIRSTCURSEAnimationController.INDEX_MAG_2,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_3,THEFIRSTCURSEAnimationController.INDEX_MAG_3,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
    }

    private void renderSinglePart(IModelComponent component, int index, ItemCameraTransforms.TransformType transformType, ItemStack stack, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay, THEFIRSTCURSEAnimationController controller, GunSkin skin) {
        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), index, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, component), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();
    }
}
