package me.xjqsh.lesraisinsadd.client.animation;


import com.tac.guns.GunMod;
import com.tac.guns.client.render.animation.module.*;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.init.ModItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.IOException;

@OnlyIn(Dist.CLIENT)
public class COLT_M1873AnimationController extends PumpShotgunAnimationController{
    public static int INDEX_BODY = 7;
    public static int INDEX_LEFT_HAND = 9;
    public static int INDEX_RIGHT_HAND = 0;

    public static int INDEX_BULLET = 6;
    public static int INDEX_SHELL = 5;
    public static int INDEX_CYLINDER = 3;
    public static int INDEX_HAMMER = 2;
    public static int INDEX_MAG = 4;

    public static final AnimationMeta FIRE = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/colt_m1873_fire.gltf"));
    public static final AnimationMeta RELOAD_END = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/colt_m1873_reload_end.gltf"));
    public static final AnimationMeta RELOAD_INTRO = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/colt_m1873_reload_intro.gltf"));
    public static final AnimationMeta RELOAD_LOOP = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/colt_m1873_reload_loop.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/colt_m1873_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/colt_m1873_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/colt_m1873_static.gltf"));
    private static COLT_M1873AnimationController instance;

    private COLT_M1873AnimationController() {
        try {
            Animations.load(RELOAD_INTRO);
            Animations.load(RELOAD_LOOP);
            Animations.load(RELOAD_END);
            Animations.load(FIRE);
            Animations.load(INSPECT);
            Animations.load(DRAW);
            Animations.load(STATIC);
        } catch (IOException e) {
            GunMod.LOGGER.fatal(e.getStackTrace());
        }
        this.enableStaticState();
        GunAnimationController.setAnimationControllerMap(ModItems.COLT_M1873.getId(),this);
    }

    public static COLT_M1873AnimationController getInstance(){
        if(instance==null){
            instance = new COLT_M1873AnimationController();
        }
        return instance;
    }

    @Override
    public AnimationMeta getAnimationFromLabel(AnimationLabel label) {
        switch (label){
            case INSPECT_EMPTY:
            case INSPECT: return INSPECT;
            case RELOAD_INTRO: return RELOAD_INTRO;
            case RELOAD_LOOP: return RELOAD_LOOP;
            case RELOAD_NORMAL_END:
            case RELOAD_EMPTY_END: return RELOAD_END;
            case DRAW: return DRAW;
            case STATIC: return STATIC;
            case PUMP: return FIRE;
            default: return null;
        }
    }

    @Override
    public AnimationSoundMeta getSoundFromLabel(AnimationLabel label){
        return super.getSoundFromLabel(ModItems.COLT_M1873.get(), label);
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

}
