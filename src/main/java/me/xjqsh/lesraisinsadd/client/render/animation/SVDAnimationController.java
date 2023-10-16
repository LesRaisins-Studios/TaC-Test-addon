package me.xjqsh.lesraisinsadd.client.render.animation;


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
public class SVDAnimationController extends GunAnimationController {
    public static int INDEX_BODY = 6;
    public static int INDEX_LEFT_HAND = 0;
    public static int INDEX_RIGHT_HAND = 2;
    public static int INDEX_MAGAZINE = 5;
    public static int INDEX_BOLT = 4;

    public static final AnimationMeta RELOAD_NORM = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/svd_reload_norm.gltf"));
    public static final AnimationMeta RELOAD_EMPTY = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/svd_reload_empty.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/svd_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/svd_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/svd_static.gltf"));
    private static final SVDAnimationController instance = new SVDAnimationController();

    private SVDAnimationController() {
        try {
            Animations.load(RELOAD_NORM);
            Animations.load(INSPECT);
            Animations.load(DRAW);
            Animations.load(RELOAD_EMPTY);
            Animations.load(STATIC);
        } catch (IOException e) {
            GunMod.LOGGER.fatal(e.getStackTrace());
        }
        this.enableStaticState();
        GunAnimationController.setAnimationControllerMap(ModItems.SVD.getId(),this);
    }

    public static SVDAnimationController getInstance(){
        return instance;
    }

    @Override
    public AnimationMeta getAnimationFromLabel(AnimationLabel label) {
        switch (label){
            case INSPECT: return INSPECT;
            case RELOAD_NORMAL: return RELOAD_NORM;
            case DRAW: return DRAW;
            case STATIC: return STATIC;
            case RELOAD_EMPTY: return RELOAD_EMPTY;
            default: return null;
        }
    }

    @Override
    public AnimationSoundMeta getSoundFromLabel(AnimationLabel label){
        return super.getSoundFromLabel(ModItems.SVD.get(), label);
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
