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
public class THEFIRSTCURSEAnimationController extends GunAnimationController implements IFireController{
    public static int INDEX_BODY = 7;
    public static int INDEX_LEFT_HAND = 11;
    public static int INDEX_RIGHT_HAND = 8;
    public static int INDEX_MAG_1 = 5;
    public static int INDEX_MAG_2 = 4;
    public static int INDEX_MAG_3 = 3;
    public static int INDEX_BULLET = 2;

    public static final AnimationMeta RELOAD = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/the_first_curse_reload.gltf"));
    public static final AnimationMeta FIRE = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/the_first_curse_fire.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/the_first_curse_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/the_first_curse_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/the_first_curse_static.gltf"));
    private static THEFIRSTCURSEAnimationController instance;

    private THEFIRSTCURSEAnimationController() {
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
        GunAnimationController.setAnimationControllerMap(ModItems.THE_FIRST_CURSE.getId(),this);
    }

    public static THEFIRSTCURSEAnimationController getInstance(){
        if(instance==null){
            instance = new THEFIRSTCURSEAnimationController();
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
        return super.getSoundFromLabel(ModItems.THE_FIRST_CURSE.get(), label);
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
