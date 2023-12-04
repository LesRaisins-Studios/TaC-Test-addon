//package me.xjqsh.lesraisinsadd.client.render.model.gun;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.tac.guns.client.gunskin.GunSkin;
//import com.tac.guns.client.gunskin.IModelComponent;
//import com.tac.guns.client.gunskin.ModelComponent;
//import com.tac.guns.client.gunskin.SkinManager;
//import com.tac.guns.client.render.animation.module.GunAnimationController;
//import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
//import com.tac.guns.client.render.gun.SkinAnimationModel;
//import com.tac.guns.client.util.RenderUtil;
//import com.tac.guns.util.GunModifierHelper;
//import me.xjqsh.lesraisinsadd.client.animation.HK433AnimationController;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.model.ItemCameraTransforms;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.math.vector.Vector3d;
//
//import static com.tac.guns.client.gunskin.ModelComponent.*;
//
///*
// * Because the revolver has a rotating chamber, we need to render it in a
// * different way than normal items. In this case we are overriding the model.
// */
//
///**
// * Author: Timeless Development, and associates.
// */
//public class hk433_animation extends SkinAnimationModel {
//
//    @Override
//    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
//        HK433AnimationController controller = HK433AnimationController.getInstance();
//        GunSkin skin = SkinManager.getSkin(stack);
//
//        matrices.pushPose();
//        {
//            controller.applySpecialModelTransform(getModelComponent(skin, BODY), HK433AnimationController.INDEX_BODY, transformType, matrices);
//            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
//            renderBarrelWithDefault(stack,matrices,renderBuffer,light,overlay,skin);
//            renderStock(stack,matrices,renderBuffer,light,overlay,skin);
//        }
//        matrices.popPose();
//
//        matrices.pushPose();
//        {
//            controller.applySpecialModelTransform(getModelComponent(skin, BODY), HK433AnimationController.INDEX_MAGAZINE_2, transformType, matrices);
//            if (GunModifierHelper.getAmmoCapacityWeight(stack) > -1) {
//                this.renderComponentWithOffset(stack, matrices, renderBuffer, light, overlay, skin, ModelComponent.MAG_EXTENDED);
//            } else {
//                this.renderComponentWithOffset(stack, matrices, renderBuffer, light, overlay, skin, ModelComponent.MAG_STANDARD);
//            }
//        }
//        matrices.popPose();
//
//        matrices.pushPose();
//        {
//            controller.applySpecialModelTransform(getModelComponent(skin, BODY), HK433AnimationController.INDEX_MAGAZINE, transformType, matrices);
//            if (GunModifierHelper.getAmmoCapacityWeight(stack) > -1) {
//                this.renderComponentWithOffset(stack, matrices, renderBuffer, light, overlay, skin, ModelComponent.MAG_EXTENDED);
//            } else {
//                this.renderComponentWithOffset(stack, matrices, renderBuffer, light, overlay, skin, ModelComponent.MAG_STANDARD);
//            }
//        }
//        matrices.popPose();
//
//        renderSinglePart(BOLT,HK433AnimationController.INDEX_BOLT,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
//        renderSinglePart(HANDLE,HK433AnimationController.INDEX_HANDLE,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
//
//        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
//    }
//
//    private void renderSinglePart(IModelComponent component, int index, ItemCameraTransforms.TransformType transformType, ItemStack stack, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay, GunAnimationController controller, GunSkin skin) {
//        matrices.pushPose();
//        {
//            controller.applySpecialModelTransform(getModelComponent(skin, BODY), index, transformType, matrices);
//            RenderUtil.renderModel(getModelComponent(skin, component), stack, matrices, renderBuffer, light, overlay);
//        }
//        matrices.popPose();
//    }
//
//    private void renderComponentWithOffset(ItemStack stack, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay, GunSkin skin, IModelComponent modelComponent) {
//        if (this.extraOffset.containsKey(modelComponent)) {
//            Vector3d x = (Vector3d)this.extraOffset.get(modelComponent);
//            matrices.pushPose();
//            matrices.translate(x.x(), x.y(), x.z());
//            RenderUtil.renderModel(this.getModelComponent(skin, modelComponent), stack, matrices, renderBuffer, light, overlay);
//            matrices.translate(-x.x(), -x.y(), -x.z());
//            matrices.popPose();
//        } else {
//            RenderUtil.renderModel(this.getModelComponent(skin, modelComponent), stack, matrices, renderBuffer, light, overlay);
//        }
//
//    }
//}
