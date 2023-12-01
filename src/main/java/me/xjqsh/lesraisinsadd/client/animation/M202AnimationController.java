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
public class M202AnimationController extends GunAnimationController {
    public static int INDEX_BODY = 8;
    public static int INDEX_LEFT_HAND = 10;
    public static int INDEX_RIGHT_HAND = 0;
    public static int INDEX_COVER_BACK = 2;
    public static int INDEX_COVER_FRONT = 3;
    public static int INDEX_ROCKET_FULL = 5;
    public static int INDEX_ROCKET_HANDLE_FULL = 4;
    public static int INDEX_ROCKET_EMPTY = 7;
    public static int INDEX_ROCKET_HANDLE_EMPTY = 6;
    public static final AnimationMeta RELOAD = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/m202_reload.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/m202_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/m202_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/m202_static.gltf"));
    private static M202AnimationController instance;

    private M202AnimationController() {
        try {
            Animations.load(RELOAD);
            Animations.load(INSPECT);
            Animations.load(DRAW);
            Animations.load(STATIC);
        } catch (IOException e) {
            GunMod.LOGGER.fatal(e.getStackTrace());
        }
        this.enableStaticState();
        GunAnimationController.setAnimationControllerMap(ModItems.M202.getId(),this);
    }

    public static M202AnimationController getInstance(){
        if(instance==null){
            instance = new M202AnimationController();
        }
        return instance;
    }

    @Override
    public AnimationMeta getAnimationFromLabel(AnimationLabel label) {
        switch (label){
            case INSPECT_EMPTY:
            case INSPECT: return INSPECT;
            case RELOAD_EMPTY:
            case RELOAD_NORMAL: return RELOAD;
            case DRAW: return DRAW;
            case STATIC: return STATIC;
            default: return null;
        }
    }

    @Override
    public AnimationSoundMeta getSoundFromLabel(AnimationLabel label){
        return super.getSoundFromLabel(ModItems.M202.get(), label);
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
