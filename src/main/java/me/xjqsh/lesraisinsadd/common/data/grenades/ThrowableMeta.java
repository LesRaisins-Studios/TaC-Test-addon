package me.xjqsh.lesraisinsadd.common.data.grenades;

import me.xjqsh.lesraisinsadd.common.data.IMeta;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class ThrowableMeta implements IMeta {
    private ResourceLocation tailParticle = new ResourceLocation("minecraft:smoke");
    private boolean canCook = true;
    private boolean shouldRot = true;
    private boolean shouldBounce = false;
    private boolean brokeOnGround = false;
    private float gravityVelocity = 0.03F;
    private float speed = 1.0F;
    private int maxCookTime = 200;
    private int maxLife = 200;
    private double bounceFactor = 0.5;

    public ThrowableMeta() {}
    @Override
    public void writeBuffer(PacketBuffer buffer) {
        buffer.writeBoolean(canCook);
        buffer.writeBoolean(shouldRot);
        buffer.writeBoolean(shouldBounce);
        buffer.writeBoolean(brokeOnGround);
        buffer.writeFloat(gravityVelocity);
        buffer.writeFloat(speed);
        buffer.writeInt(maxCookTime);
        buffer.writeInt(maxLife);
        buffer.writeDouble(bounceFactor);
        buffer.writeResourceLocation(tailParticle);
    }

    @Override
    public void readBuffer(PacketBuffer buffer) {
        this.canCook = buffer.readBoolean();
        this.shouldRot = buffer.readBoolean();
        this.shouldBounce = buffer.readBoolean();
        this.brokeOnGround = buffer.readBoolean();
        this.gravityVelocity = buffer.readFloat();
        this.speed = buffer.readFloat();
        this.maxCookTime = buffer.readInt();
        this.maxLife = buffer.readInt();
        this.bounceFactor = buffer.readDouble();
        this.tailParticle = buffer.readResourceLocation();
    }

    public boolean canCook() {
        return canCook;
    }

    public float getSpeed() {
        return speed;
    }
    public int getMaxCookTime() {
        return maxCookTime;
    }
    public boolean shouldRot() {
        return shouldRot;
    }
    public boolean shouldBounce() {
        return shouldBounce;
    }
    public boolean isBrokeOnGround() {
        return brokeOnGround;
    }
    public float getGravityVelocity() {
        return gravityVelocity;
    }
    public int getMaxLife() {
        return maxLife;
    }
    public double getBounceFactor() {
        return bounceFactor;
    }

    public ResourceLocation getTailParticle() {
        return tailParticle;
    }
}