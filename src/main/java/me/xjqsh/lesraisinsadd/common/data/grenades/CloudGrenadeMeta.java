package me.xjqsh.lesraisinsadd.common.data.grenades;

import me.xjqsh.lesraisinsadd.common.data.IMeta;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class CloudGrenadeMeta extends ThrowableMeta {
    public static class CloudMeta implements IMeta {
        private int cloudLifeTime = 600;
        private float height = 1.2f;
        private float startRadius = 2.5f;
        private float maxRadius = 8f;
        private float radiusPerTick = 0.05f;
        private boolean extinguishBySmoke = false;
        private ResourceLocation particle = new ResourceLocation("minecraft:flame");
        public void writeBuffer(PacketBuffer buffer) {
            buffer.writeInt(cloudLifeTime);
            buffer.writeFloat(height);
            buffer.writeFloat(startRadius);
            buffer.writeFloat(maxRadius);
            buffer.writeFloat(radiusPerTick);
            buffer.writeBoolean(extinguishBySmoke);
            buffer.writeResourceLocation(particle);
        }

        public void readBuffer(PacketBuffer buffer) {
            this.cloudLifeTime = buffer.readInt();
            this.height = buffer.readFloat();
            this.startRadius = buffer.readFloat();
            this.maxRadius = buffer.readFloat();
            this.radiusPerTick = buffer.readFloat();
            this.extinguishBySmoke = buffer.readBoolean();
            this.particle = buffer.readResourceLocation();
        }

        public boolean isExtinguishBySmoke() {
            return extinguishBySmoke;
        }

        public int getCloudLifeTime() {
            return cloudLifeTime;
        }

        public ResourceLocation getParticle() {
            return particle;
        }

        public float getHeight() {
            return height;
        }

        public float getStartRadius() {
            return startRadius;
        }

        public float getMaxRadius() {
            return maxRadius;
        }

        public float getRadiusPerTick() {
            return radiusPerTick;
        }
    }
    private CloudMeta cloud = new CloudMeta();
    private ResourceLocation effect = new ResourceLocation("minecraft:poison");

    private int effectDuration = 100;
    private int effectAmplifier = 0;

    private boolean specialEffectInstance = false;
    private boolean onlyAffectPlayer = false;


    @Override
    public void writeBuffer(PacketBuffer buffer) {
        super.writeBuffer(buffer);
        buffer.writeResourceLocation(effect);
        buffer.writeInt(effectDuration);
        buffer.writeInt(effectAmplifier);
        buffer.writeBoolean(specialEffectInstance);
        buffer.writeBoolean(onlyAffectPlayer);
        cloud.writeBuffer(buffer);
    }

    @Override
    public void readBuffer(PacketBuffer buffer) {
        super.readBuffer(buffer);
        this.effect = buffer.readResourceLocation();
        this.effectDuration = buffer.readInt();
        this.effectAmplifier = buffer.readInt();
        this.specialEffectInstance = buffer.readBoolean();
        this.onlyAffectPlayer = buffer.readBoolean();
        this.cloud.readBuffer(buffer);
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

    public CloudMeta getCloud() {
        return cloud;
    }
}