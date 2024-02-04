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
import me.xjqsh.lesraisinsadd.client.animation.NailgunAnimationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;

import static com.tac.guns.client.gunskin.ModelComponent.*;


public class nailgun_animation extends SkinAnimationModel {


    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        NailgunAnimationController controller = NailgunAnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), NailgunAnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
            renderStockWithDefault(stack,matrices,renderBuffer,light,overlay,skin);
            renderBarrel(stack,matrices,renderBuffer,light,overlay,skin);
            renderComponentWithOffset(stack,matrices,renderBuffer,15728880,overlay,skin,SIGHT_LIGHT);
            if (Gun.getScope(stack) != null) {
                RenderUtil.renderModel(getModelComponent(skin, RAIL_SCOPE), stack, matrices, renderBuffer, light, overlay);
            }
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), NailgunAnimationController.INDEX_MAG2, transformType, matrices);
            renderMag(stack,matrices,renderBuffer,light,overlay,skin);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            if(controller.isAnimationRunning(GunAnimationController.AnimationLabel.RELOAD_EMPTY) || controller.isAnimationRunning(GunAnimationController.AnimationLabel.RELOAD_NORMAL)){
                controller.applySpecialModelTransform(getModelComponent(skin, BODY), NailgunAnimationController.INDEX_MAG, transformType, matrices);
                renderMag(stack,matrices,renderBuffer,light,overlay,skin);
            }
        }
        matrices.popPose();

        renderSinglePart(HANDLE,NailgunAnimationController.INDEX_HANDLE,transformType,stack,matrices,renderBuffer,light,overlay,controller,skin);

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
