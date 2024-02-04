package me.xjqsh.lesraisinsadd.common.data.grenades;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class AreaGrenadeMeta extends ThrowableMeta {
    private static final int[] defaultColor = {0,0,0};
    private int[] color = {0,0,0};
    private ResourceLocation effect = new ResourceLocation("minecraft:poison");
    private int effectDuration = 100;
    private int effectAmplifier = 0;
    private boolean specialEffectInstance = false;
    private boolean onlyAffectPlayer = false;

    @Override
    public void writeBuffer(PacketBuffer buffer) {
        super.writeBuffer(buffer);
        buffer.writeVarIntArray(color);
        buffer.writeResourceLocation(effect);
        buffer.writeInt(effectDuration);
        buffer.writeInt(effectAmplifier);
        buffer.writeBoolean(specialEffectInstance);
        buffer.writeBoolean(onlyAffectPlayer);
    }

    @Override
    public void readBuffer(PacketBuffer buffer) {
        super.readBuffer(buffer);
        this.color = buffer.readVarIntArray();
        this.effect = buffer.readResourceLocation();
        this.effectDuration = buffer.readInt();
        this.effectAmplifier = buffer.readInt();
        this.specialEffectInstance = buffer.readBoolean();
        this.onlyAffectPlayer = buffer.readBoolean();
    }

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