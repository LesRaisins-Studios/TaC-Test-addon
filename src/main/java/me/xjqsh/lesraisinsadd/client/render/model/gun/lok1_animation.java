package me.xjqsh.lesraisinsadd.client.render.model.gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tac.guns.client.gunskin.GunSkin;
import com.tac.guns.client.gunskin.SkinManager;
import com.tac.guns.client.render.animation.module.PlayerHandAnimation;
import com.tac.guns.client.render.gun.SkinAnimationModel;
import com.tac.guns.client.util.RenderUtil;
import me.xjqsh.lesraisinsadd.client.render.animation.LOK1AnimationController;
import me.xjqsh.lesraisinsadd.client.render.animation.M202AnimationController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

import java.awt.*;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;


public class lok1_animation extends SkinAnimationModel {
    private void renderAmmo(MatrixStack matrices,int ammo){
        if(ammo>99)ammo=99;
        FontRenderer font = Minecraft.getInstance().font;
        matrices.pushPose();
        {
            float scale = 0.007f;
            matrices.mulPose(Vector3f.XP.rotationDegrees(180));
            matrices.mulPose(Vector3f.XN.rotationDegrees(22.5f));
            matrices.scale(scale,scale,scale);
            matrices.translate(-27.5f,-19.5,24);
            int color = ammo==0 ? Color.RED.getRGB() : Color.GREEN.getRGB();
            font.draw(matrices,(ammo<10?"0":"")+ammo,0,0, color);
        }
        matrices.popPose();
    }

    @Override
    public void render(float v, ItemCameraTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrices, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        LOK1AnimationController controller = LOK1AnimationController.getInstance();
        GunSkin skin = SkinManager.getSkin(stack);

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), LOK1AnimationController.INDEX_BODY, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BODY), stack, matrices, renderBuffer, light, overlay);
            renderAmmo(matrices,stack.getOrCreateTag().getInt("AmmoCount"));
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), LOK1AnimationController.INDEX_BOLT, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, BOLT), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), LOK1AnimationController.INDEX_MAG, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, MAG_STANDARD), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), LOK1AnimationController.INDEX_SIGHT, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, SIGHT), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        matrices.pushPose();
        {
            controller.applySpecialModelTransform(getModelComponent(skin, BODY), LOK1AnimationController.INDEX_GRIP_MAG, transformType, matrices);
            RenderUtil.renderModel(getModelComponent(skin, GRIP_MAG), stack, matrices, renderBuffer, light, overlay);
        }
        matrices.popPose();

        PlayerHandAnimation.render(controller, transformType, matrices, renderBuffer, light);
    }
}
