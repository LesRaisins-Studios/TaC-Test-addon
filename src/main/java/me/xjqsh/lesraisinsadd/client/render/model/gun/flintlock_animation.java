package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import com.tac.guns.common.Gun;
import com.tac.guns.item.attachment.IAttachment;
import me.xjqsh.lesraisinsadd.client.animation.FLINTLOCKAnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;


public class flintlock_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        FLINTLOCKAnimationController controller = FLINTLOCKAnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), FLINTLOCKAnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);

            if (Gun.getScope(stack) != null || Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() != ItemStack.EMPTY.getItem()) {
                RenderUtil.renderModel(getModelComponent(skin, RAIL_EXTENDED), stack, matrices, renderBuffer, light, overlay);
            }else {
                RenderUtil.renderModel(getModelComponent(skin, RAIL_DEFAULT), stack, matrices, renderBuffer, light, overlay);
            }

            renderStockWithDefault(stack,matrices,renderBuffer,light,overlay,skin);
            renderGrip(stack,matrices,renderBuffer,light,overlay,skin);
        }
        matrices.popPose();

        if(controller.isAnimationRunning(GunAnimationController.AnimationLabel.RELOAD_EMPTY)){
            renderSinglePart(BULLET,FLINTLOCKAnimationController.INDEX_BULLET,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
            renderSinglePart(BOTTLE,FLINTLOCKAnimationController.INDEX_BOTTLE,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
            renderSinglePart(STICK,FLINTLOCKAnimationController.INDEX_STICK,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        }

        renderSinglePart(HAMMER,FLINTLOCKAnimationController.INDEX_HAMMER,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(PLATE,FLINTLOCKAnimationController.INDEX_PLATE,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);

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
