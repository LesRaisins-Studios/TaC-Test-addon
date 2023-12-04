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
public class NTW20AnimationController extends BoltActionAnimationController{
    public static int INDEX_BODY = 5;
    public static int INDEX_LEFT_HAND = 7;
    public static int INDEX_RIGHT_HAND = 0;
    public static int INDEX_BOLT = 2;
    public static int INDEX_MAG = 4;
    public static int INDEX_BULLET = 3;

    public static final AnimationMeta RELOAD_EMPTY = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ntw20_reload_empty.gltf"));
    public static final AnimationMeta RELOAD_NORM = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ntw20_reload_norm.gltf"));

    public static final AnimationMeta PULL_BOLT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ntw20_bolt.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ntw20_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ntw20_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ntw20_static.gltf"));
    private static NTW20AnimationController instance;

    private NTW20AnimationController() {
        try {
            Animations.load(RELOAD_EMPTY);
            Animations.load(RELOAD_NORM);
            Animations.load(INSPECT);
            Animations.load(DRAW);
            Animations.load(PULL_BOLT);
            Animations.load(STATIC);
        } catch (IOException e) {
            GunMod.LOGGER.fatal(e.getStackTrace());
        }
        this.enableStaticState();
        GunAnimationController.setAnimationControllerMap(ModItems.NTW20.getId(),this);
    }

    public static NTW20AnimationController getInstance(){
        if(instance==null){
            instance = new NTW20AnimationController();
        }
        return instance;
    }

    @Override
    public AnimationMeta getAnimationFromLabel(AnimationLabel label) {
        switch (label){
            case INSPECT_EMPTY:
            case INSPECT: return INSPECT;
            case DRAW: return DRAW;
            case STATIC: return STATIC;
            case RELOAD_NORMAL: return RELOAD_NORM;
            case RELOAD_EMPTY: return RELOAD_EMPTY;
            case PULL_BOLT: return PULL_BOLT;
            default: return null;
        }
    }

    @Override
    public AnimationSoundMeta getSoundFromLabel(AnimationLabel label){
        return super.getSoundFromLabel(ModItems.NTW20.get(), label);
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
