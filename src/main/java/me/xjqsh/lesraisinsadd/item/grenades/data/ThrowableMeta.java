package me.xjqsh.lesraisinsadd.item.grenades.data;

import net.minecraft.network.PacketBuffer;

import java.util.Map;

public class ThrowableMeta {
    private Map<String, Object> extra;
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
    }

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
    }

    public boolean canCook() {
        return canCook;
    }

    public void setCanCook(boolean canCook) {
        this.canCook = canCook;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getMaxCookTime() {
        return maxCookTime;
    }

    public void setMaxCookTime(int maxCookTime) {
        this.maxCookTime = maxCookTime;
    }

    public boolean shouldRot() {
        return shouldRot;
    }

    public void setShouldRot(boolean shouldRot) {
        this.shouldRot = shouldRot;
    }

    public boolean shouldBounce() {
        return shouldBounce;
    }
    public void setShouldBounce(boolean shouldBounce) {
        this.shouldBounce = shouldBounce;
    }
    public boolean isBrokeOnGround() {
        return brokeOnGround;
    }
    public void setBrokeOnGround(boolean brokeOnGround) {
        this.brokeOnGround = brokeOnGround;
    }
    public float getGravityVelocity() {
        return gravityVelocity;
    }
    public void setGravityVelocity(float gravityVelocity) {
        this.gravityVelocity = gravityVelocity;
    }
    public int getMaxLife() {
        return maxLife;
    }
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public double getBounceFactor() {
        return bounceFactor;
    }

    public void setBounceFactor(double bounceFactor) {
        this.bounceFactor = bounceFactor;
    }
}