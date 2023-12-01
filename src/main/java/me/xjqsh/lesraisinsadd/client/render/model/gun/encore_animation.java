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
import me.xjqsh.lesraisinsadd.client.animation.ENCOREAnimationController;
import me.xjqsh.lesraisinsadd.item.AceItem;
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
public class encore_animation extends SkinAnimationModel {

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        ENCOREAnimationController controller = ENCOREAnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), ENCOREAnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);

        }
        matrices.popPose();

        renderSinglePart(BULLET,ENCOREAnimationController.INDEX_BULLET,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(BULLET_HEAD,ENCOREAnimationController.INDEX_BULLET_HEAD,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(HAMMER,ENCOREAnimationController.INDEX_HAMMER,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);
        renderSinglePart(RAIL_DEFAULT,ENCOREAnimationController.INDEX_UPPER,transformType, stack, matrices, renderBuffer, light, overlay, controller, skin);

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
