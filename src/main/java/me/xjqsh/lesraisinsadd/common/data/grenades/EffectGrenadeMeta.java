package me.xjqsh.lesraisinsadd.common.data.grenades;

import net.minecraft.util.ResourceLocation;

public class EffectGrenadeMeta extends ThrowableMeta {
    private static final int[] defaultColor = {0,0,0};
    private int[] color = {0,0,0};
    private ResourceLocation effect = new ResourceLocation("minecraft:poison");
    private int effectDuration = 100;
    private int effectAmplifier = 0;
    private boolean specialEffectInstance = false;
    private boolean onlyAffectPlayer = false;

    public int[] getColor() {
        return color!=null && color.length==3 ? color : defaultColor;
    }

    public ResourceLocation getEffect(){return effect;}
    public int getEffectDuration() {
        return effectDuration;
    }
    public boolean isSpecialEffectInstance() {
        return specialEffectInstance;
    }
    public boolean isOnlyAffectPlayer() {
        return onlyAffectPlayer;
    }
    public int getEffectAmplifier() {return effectAmplifier;}
}