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
public class NailgunAnimationController extends GunAnimationController {
    public static int INDEX_BODY = 5;
    public static int INDEX_LEFT_HAND = 7;
    public static int INDEX_RIGHT_HAND = 0;

    public static int INDEX_HANDLE = 2;
    public static int INDEX_MAG = 3;
    public static int INDEX_MAG2 = 4;

    public static final AnimationMeta RELOAD_NORM = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/nailgun_reload_norm.gltf"));
    public static final AnimationMeta RELOAD_EMPTY = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/nailgun_reload_empty.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/nailgun_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/nailgun_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/nailgun_static.gltf"));
    private static NailgunAnimationController instance;

    private NailgunAnimationController() {
        try {
            Animations.load(RELOAD_NORM);
            Animations.load(RELOAD_EMPTY);
            Animations.load(INSPECT);
            Animations.load(DRAW);
            Animations.load(STATIC);
        } catch (IOException e) {
            GunMod.LOGGER.fatal(e.getStackTrace());
        }
        this.enableStaticState();
        GunAnimationController.setAnimationControllerMap(ModItems.NAIL_GUN.getId(),this);
    }

    public static NailgunAnimationController getInstance(){
        if(instance==null){
            instance = new NailgunAnimationController();
        }
        return instance;
    }

    @Override
    public AnimationMeta getAnimationFromLabel(AnimationLabel label) {
        switch (label){
            case INSPECT_EMPTY:
            case INSPECT: return INSPECT;
            case RELOAD_EMPTY: return RELOAD_EMPTY;
            case RELOAD_NORMAL: return RELOAD_NORM;
            case DRAW: return DRAW;
            case STATIC: return STATIC;
            default: return null;
        }
    }

    @Override
    public AnimationSoundMeta getSoundFromLabel(AnimationLabel label){
        return super.getSoundFromLabel(ModItems.NAIL_GUN.get(), label);
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
