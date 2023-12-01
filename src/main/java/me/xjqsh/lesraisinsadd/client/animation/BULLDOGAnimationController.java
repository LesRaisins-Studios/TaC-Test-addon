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
public class BULLDOGAnimationController extends GunAnimationController implements IFireController{
    public static int INDEX_BODY = 6;
    public static int INDEX_LEFT_HAND = 8;
    public static int INDEX_RIGHT_HAND = 0;
    public static int INDEX_MAG = 4;
    public static int INDEX_HAMMER = 2;
    public static int INDEX_RAIL = 5;

    public static final AnimationMeta RELOAD = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/bulldog_reload.gltf"));
    public static final AnimationMeta FIRE = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/bulldog_fire.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/bulldog_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/bulldog_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/bulldog_static.gltf"));
    private static BULLDOGAnimationController instance;

    private BULLDOGAnimationController() {
        try {
            Animations.load(RELOAD);
            Animations.load(INSPECT);
            Animations.load(DRAW);
            Animations.load(FIRE);
            Animations.load(STATIC);
        } catch (IOException e) {
            GunMod.LOGGER.fatal(e.getStackTrace());
        }
        this.enableStaticState();
        GunAnimationController.setAnimationControllerMap(ModItems.BULLDOG.getId(),this);
    }

    public static BULLDOGAnimationController getInstance(){
        if(instance==null){
            instance = new BULLDOGAnimationController();
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
            case RELOAD_NORMAL:
            case RELOAD_EMPTY: return RELOAD;
            default: return null;
        }
    }

    @Override
    public AnimationSoundMeta getSoundFromLabel(AnimationLabel label){
        return super.getSoundFromLabel(ModItems.BULLDOG.get(), label);
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

    public void runFireAnimation(){
        runAnimation(FIRE,null,null);
    }
    @Override
    public boolean isFireAnimationRunning(){
        return Animations.isAnimationRunning(FIRE);
    }
}
