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
public class ACEOFSPADESAnimationController extends GunAnimationController implements IFireController{
    public static int INDEX_BODY = 7;
    public static int INDEX_LEFT_HAND = 11;
    public static int INDEX_RIGHT_HAND = 8;
    public static int INDEX_SIGHT_LEFT = 4;
    public static int INDEX_SIGHT_RIGHT = 5;
    public static int INDEX_MAG_1 = 3;
    public static int INDEX_MAG_2 = 2;
    public static int INDEX_MAG_3 = 0;
    public static int INDEX_BULLET = 1;

    public static final AnimationMeta RELOAD = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ace_of_spades_reload.gltf"));
    public static final AnimationMeta FIRE = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ace_of_spades_fire.gltf"));
    public static final AnimationMeta INSPECT = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ace_of_spades_inspect.gltf"));
    public static final AnimationMeta DRAW = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ace_of_spades_draw.gltf"));
    public static final AnimationMeta STATIC = new AnimationMeta(new ResourceLocation(Reference.MOD_ID,"animations/ace_of_spades_static.gltf"));
    private static ACEOFSPADESAnimationController instance;

    private ACEOFSPADESAnimationController() {
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
        GunAnimationController.setAnimationControllerMap(ModItems.ACE_OF_SPADES.getId(),this);
    }

    public static ACEOFSPADESAnimationController getInstance(){
        if(instance==null){
            instance = new ACEOFSPADESAnimationController();
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
        return super.getSoundFromLabel(ModItems.ACE_OF_SPADES.get(), label);
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
