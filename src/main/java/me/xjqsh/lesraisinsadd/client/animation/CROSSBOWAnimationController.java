package me.xjqsh.lesraisinsadd.client.animation;


import com.tac.guns.GunMod;
import com.tac.guns.client.render.animation.module.AnimationMeta;
import com.tac.guns.client.render.animation.module.AnimationSoundMeta;
import com.tac.guns.client.render.animation.module.Animations;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.init.ModItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.IOException;

@OnlyIn(Dist.CLIENT)
public class CROSSBOWAnimationController extends GunAnimationController implements IFireController {
    public static int INDEX_BODY = 11;
    public static int INDEX_LEFT_HAND = 13;
    public static int INDEX_RIGHT_HAND = 0;
    public static int INDEX_ARROW = 10;

    public static int INDEX_STRING_L = 2;
    public static int INDEX_ARM_L = 4;
    public static int INDEX_WHEEL_L = 3;

    public static int INDEX_STRING_R = 6;
    public static int INDEX_ARM_R = 8;
    public static int INDEX_WHEEL_R = 7;

    public static final AnimationMeta RELOAD_NORM = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/crossbow_reload.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/crossbow_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/crossbow_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/crossbow_static.gltf"));
    public static final AnimationMeta FIRE = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/crossbow_fire.gltf"));
    private static CROSSBOWAnimationController instance;

    private CROSSBOWAnimationController() {
        try {
            Animations.load(RELOAD_NORM);
            Animations.load(INSPECT);
            Animations.load(DRAW);
            Animations.load(STATIC);
            Animations.load(FIRE);
        } catch (IOException e) {
            GunMod.LOGGER.fatal(e.getStackTrace());
        }
        this.enableStaticState();
        GunAnimationController.setAnimationControllerMap(ModItems.CROSSBOW.getId(),this);
    }

    public static CROSSBOWAnimationController getInstance(){
        if(instance==null){
            instance = new CROSSBOWAnimationController();
        }
        return instance;
    }

    @Override
    public AnimationMeta getAnimationFromLabel(AnimationLabel label) {
        switch (label){
            case INSPECT_EMPTY:
            case INSPECT: return INSPECT;
            case RELOAD_EMPTY:
            case RELOAD_NORMAL: return RELOAD_NORM;
            case DRAW: return DRAW;
            case STATIC: return STATIC;
            default: return null;
        }
    }

    @Override
    public AnimationSoundMeta getSoundFromLabel(AnimationLabel label){
        return super.getSoundFromLabel(ModItems.CROSSBOW.get(), label);
    }

    @Override
    protected int getAttachmentsNodeIndex() {
        return INDEX_BODY;
    }

    @Override
    protected int getRightHandNodeIndex() {
        return INDEX_RIGHT_HAND;
    }

    @Override
    protected int getLeftHandNodeIndex() {
        return INDEX_LEFT_HAND;
    }

    @Override
    public void runFireAnimation(){
        runAnimation(FIRE,null,null);
    }

    @Override
    public boolean isFireAnimationRunning(){
        return Animations.isAnimationRunning(FIRE);
    }
}
