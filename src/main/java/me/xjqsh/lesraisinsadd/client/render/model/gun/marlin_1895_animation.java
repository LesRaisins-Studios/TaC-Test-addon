package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import com.tac.guns.common.Gun;
import com.tac.guns.item.attachment.IAttachment;
import me.xjqsh.lesraisinsadd.client.animation.MARLIN1895AnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.LEVER;


public class marlin_1895_animation extends SkinAnimationModel {
    public marlin_1895_animation(){
        this.extraOffset.put(MUZZLE_SILENCER,new Vector3d(0,0,-10*0.0625));
        this.extraOffset.put(MUZZLE_BRAKE,new Vector3d(0,0,-2*0.0625));
        this.extraOffset.put(MUZZLE_COMPENSATOR,new Vector3d(0,0,-3*0.0625));
    }

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        MARLIN1895AnimationController controller = MARLIN1895AnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), MARLIN1895AnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);

            if (Gun.getScope(stack) != null || Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack).getItem() != ItemStack.EMPTY.getItem()) {
                RenderUtil.renderModel(getModelComponent(skin, RAIL_EXTENDED), stack, matrices, renderBuffer, light, overlay);
            }else {
                RenderUtil.renderModel(getModelComponent(skin, RAIL_DEFAULT), stack, matrices, renderBuffer, light, overlay);
            }

            renderBarrel(stack, matrices, renderBuffer, light, overlay, skin);
            renderStockWithDefault(stack, matrices, renderBuffer, light, overlay, skin);
            renderGrip(stack, matrices, renderBuffer, light, overlay, skin);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), MARLIN1895AnimationController.INDEX_LEVER, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, LEVER), stack, matrices, renderBuffer, light, overlay);

        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), MARLIN1895AnimationController.INDEX_BOLT, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BOLT), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), MARLIN1895AnimationController.INDEX_HAMMER, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, HAMMER), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), MARLIN1895AnimationController.INDEX_BULLET, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BULLET), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
    }
}
