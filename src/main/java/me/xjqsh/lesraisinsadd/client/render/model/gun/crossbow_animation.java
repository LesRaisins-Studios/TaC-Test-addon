package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.animation.CROSSBOWAnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import static com.tac.guns.client.gunskin.ModelComponent.BODY;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;

/*
 * Because the revolver has a rotating chamber, we need to render it in a
 * different way than normal items. In this case we are overriding the model.
 */

/**
 * Author: Timeless Development, and associates.
 */
public class crossbow_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        CROSSBOWAnimationController controller = CROSSBOWAnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        boolean hasAmmo = hasAmmo(stack) || controller.isAnimationRunning(GunAnimationController.AnimationLabel.RELOAD_EMPTY);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), CROSSBOWAnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), CROSSBOWAnimationController.INDEX_ARROW, transformType, matrices);
            if(hasAmmo){
                RenderUtil.renderModel(getModelComponent(skin, ARROW), stack, matrices, renderBuffer, light, overlay);
            }
        }
        matrices.popPose();

        multiState(CROSSBOWAnimationController.INDEX_ARM_L,ARM_L,transformType, stack, matrices, renderBuffer, light, overlay, hasAmmo, controller, skin);
        multiState(CROSSBOWAnimationController.INDEX_ARM_R,ARM_R,transformType, stack, matrices, renderBuffer, light, overlay, hasAmmo, controller, skin);
        multiState(CROSSBOWAnimationController.INDEX_WHEEL_L,WHEEL_L,transformType, stack, matrices, renderBuffer, light, overlay, hasAmmo, controller, skin);
        multiState(CROSSBOWAnimationController.INDEX_WHEEL_R,WHEEL_R,transformType, stack, matrices, renderBuffer, light, overlay, hasAmmo, controller, skin);
        multiState(CROSSBOWAnimationController.INDEX_STRING_L,STRING_L,transformType, stack, matrices, renderBuffer, light, overlay, hasAmmo, controller, skin);
        multiState(CROSSBOWAnimationController.INDEX_STRING_R,STRING_R,transformType, stack, matrices, renderBuffer, light, overlay, hasAmmo, controller, skin);

        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
    }

    private void multiState(int x,IModelComponent component ,ItemCameraTransforms.TransformType transformType, ItemStack stack, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay, boolean hasAmmo, CROSSBOWAnimationController controller, GunSkin skin) {
        matrices.pushPose();
        {
            if(hasAmmo){
                controller.applySpecialModelTransform(getModelComponent(skin, BODY), x, transformType, matrices);
            }else {
                controller.applySpecialModelTransform(getModelComponent(skin, BODY), CROSSBOWAnimationController.INDEX_BODY, transformType, matrices);
            }
            RenderUtil.renderModel(getModelComponent(skin, component), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();
    }


    public static boolean hasAmmo(ItemStack gunStack) {
        CompoundNBT tag = gunStack.getOrCreateTag();
        return tag.getInt("AmmoCount") > 0;
    }

}
