package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.IModelComponent;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.AnimationMeta;
import com.tac.guns.client.render.animation.module.Animations;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import de.javagl.jgltf.model.animation.AnimationRunner;
import me.xjqsh.lesraisinsadd.client.animation.ACEOFSPADESAnimationController;
import me.xjqsh.lesraisinsadd.item.AceItem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;


public class ace_of_spades_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        ACEOFSPADESAnimationController controller = ACEOFSPADESAnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), ACEOFSPADESAnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
            RenderUtil.renderModel(getModelComponent(skin, SIGHT), stack, matrices, renderBuffer, 15728880, overlay);
        }
        matrices.popPose();

        boolean flag = true;
        float current = 3;
        if(AceItem.isEnhanced(stack)){
            if (controller.isAnimationRunning()) {
                AnimationMeta meta = controller.getPreviousAnimation();
                if (meta!=null && meta.equals(ACEOFSPADESAnimationController.RELOAD)){
                    AnimationRunner runner = Animations.getAnimationRunner(meta.getResourceLocation());
                    if (runner == null) return;
                    current = runner.getAnimationManager().getCurrentTimeS();
                    if(current<1.85f){
                        flag = false;
                    }
                }
            }
        }else {
            flag = false;
        }
        int mg = light;
        if(flag){
            mg = 15728880;
            if(current>2.0) {
                renderSinglePart(SMOKE,ACEOFSPADESAnimationController.INDEX_BODY,transformType, stack, matrices, renderBuffer, mg, overlay, controller, skin);
            }
        }

        renderSinglePart(BULLET,ACEOFSPADESAnimationController.INDEX_BULLET,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_1,ACEOFSPADESAnimationController.INDEX_MAG_1,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_2,ACEOFSPADESAnimationController.INDEX_MAG_2,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(MAG_3,ACEOFSPADESAnimationController.INDEX_MAG_3,transformType, stack, matrices, renderBuffer, mg, overlay, controller, skin);
        renderSinglePart(SIGHT_LEFT,ACEOFSPADESAnimationController.INDEX_SIGHT_LEFT,transformType, stack, matrices, renderBuffer, 15728880, overlay, controller, skin);
        renderSinglePart(SIGHT_RIGHT,ACEOFSPADESAnimationController.INDEX_SIGHT_RIGHT,transformType, stack, matrices, renderBuffer, 15728880, overlay, controller, skin);

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
