package me.xjqsh.lesraisinsadd.client.render.animation;


import com.tac.guns.GunMod;
import com.tac.guns.client.render.animation.module.*;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.init.ModItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.IOException;

@OnlyIn(Dist.CLIENT)
public class MARLIN1895AnimationController extends PumpShotgunAnimationController {
    public static int INDEX_BODY = 6;
    public static int INDEX_LEFT_HAND = 8;
    public static int INDEX_RIGHT_HAND = 0;
    public static int INDEX_LEVER = 2;
    public static int INDEX_BULLET = 3;
    public static int INDEX_BOLT = 4;
    public static int INDEX_HAMMER = 5;
    public static final AnimationMeta FIRE = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/marlin_1895_fire.gltf"));
    public static final AnimationMeta RELOAD_END = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/marlin_1895_reload_end.gltf"));
    public static final AnimationMeta RELOAD_INTRO = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/marlin_1895_reload_intro.gltf"));
    public static final AnimationMeta RELOAD_LOOP = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/marlin_1895_reload_loop.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/marlin_1895_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/marlin_1895_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/marlin_1895_static.gltf"));
    private static MARLIN1895AnimationController instance;

    private MARLIN1895AnimationController() {
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
        GunAnimationController.setAnimationControllerMap(ModItems.MARLIN_1895.getId(),this);
    }

    public static MARLIN1895AnimationController getInstance(){
        if(instance==null){
            instance=new MARLIN1895AnimationController();
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
        return super.getSoundFromLabel(ModItems.MARLIN_1895.get(), label);
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
