package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.animation.SW_MODEL3AnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.CYLINDER;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.FRONT;

public class sw_model3_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        SW_MODEL3AnimationController controller = SW_MODEL3AnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        renderSinglePart(BODY, SW_MODEL3AnimationController.INDEX_BODY, transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(FRONT, SW_MODEL3AnimationController.INDEX_FRONT, transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);

        matrices.pushPose();
        {
            if(transformType.firstPerson() && controller.isAnimationRunning(GunAnimationController.AnimationLabel.RELOAD_LOOP)){
                controller.applySpecialModelTransform(getModelComponent(skin, BODY), SW_MODEL3AnimationController.INDEX_BULLET, transformType, matrices);
                RenderUtil.renderModel(getModelComponent(skin, BULLET), stack, matrices, renderBuffer, light, overlay);
            }
        }
        matrices.popPose();

        renderSinglePart(BULLET_SHELL, SW_MODEL3AnimationController.INDEX_SHELL, transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(HAMMER, SW_MODEL3AnimationController.INDEX_HAMMER, transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(CYLINDER, SW_MODEL3AnimationController.INDEX_CYLINDER, transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);


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
}
