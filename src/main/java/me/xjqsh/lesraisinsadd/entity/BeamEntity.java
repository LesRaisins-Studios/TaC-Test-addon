package me.xjqsh.lesraisinsadd.entity;

import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class BeamEntity extends Entity implements IEntityAdditionalSpawnData {
    private BeamInfo beamInfo = new BeamInfo();
    private float length = 1.0f;
    private int index = 0;
    public BeamEntity(EntityType<?> entityTypeIn, World worldIn, Vector3d pos, BeamInfo info, int index) {
        super(entityTypeIn, worldIn);
        this.setPos(pos.x,pos.y,pos.z);
        this.setRot(info.yr,info.yr);
        this.beamInfo = info;
        this.index = index;
    }

    public BeamEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
//        if(level.isClientSide()){
//            if(beamInfo.endDisappear && tickCount>=beamInfo.maxLifeTime-beamInfo.disappearTime + index*2f){
//                length = Math.max(0f, length-0.5f);
//            }
//        }
//
//        if(this.tickCount> beamInfo.getMaxLifeTime()){
//            this.remove();
//        }
    }

    public float calcScale(){
        if(tickCount< beamInfo.getDeployTime()){
            return ((float)tickCount)/ beamInfo.getDeployTime();
        } else if (tickCount>= beamInfo.getMaxLifeTime() - beamInfo.getDisappearTime()) {
            return ((float) beamInfo.getMaxLifeTime() -tickCount)/ beamInfo.getDisappearTime();
        }else{
            return 1.0f;
        }
    }

    @Override
    public boolean shouldRender(double p_145770_1_, double p_145770_3_, double p_145770_5_) {
        return true;
    }

    @Override
    public void setBoundingBox(AxisAlignedBB p_174826_1_) {
        super.setBoundingBox(p_174826_1_.move(0,-20,0));
    }

    public BeamInfo getBeamInfo(){return beamInfo;}
    public float getLength() {
        return length;
    }
    public int getIndex(){
        return index;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {}

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {}

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        beamInfo.writeToBuffer(buffer);
        buffer.writeInt(index);
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        this.beamInfo = BeamInfo.readBuffer(additionalData);
        index = additionalData.readInt();
        this.setRot(beamInfo.getYr(),beamInfo.getXr());
    }


    public static class BeamInfo {
        private int color = -1;
        private float xr = 0;
        private float yr = 0;
        private int maxLifeTime = 10;
        private int disappearTime = 4;
        private int deployTime = 2;
        private float scale = 1.0f;
        private boolean endDisappear = false;
        private float rotateSpeed = 1.0f;

        public void setColor(int color) {
            this.color = color;
        }

        public void setXr(float xr) {
            this.xr = xr;
        }

        public void setYr(float yr) {
            this.yr = yr;
        }

        public void setMaxLifeTime(int maxLifeTime) {
            this.maxLifeTime = maxLifeTime;
        }

        public void setDisappearTime(int disappearTime) {
            this.disappearTime = disappearTime;
        }

        public void setDeployTime(int deployTime) {
            this.deployTime = deployTime;
        }

        public void setScale(float scale) {
            this.scale = scale;
        }
        public void setEndDisappear(boolean endDisappear) {
            this.endDisappear = endDisappear;
        }

        public void setRotateSpeed(float rotateSpeed) {
            this.rotateSpeed = rotateSpeed;
        }

        public float getXr() {
            return xr;
        }
        public float getYr() {
            return yr;
        }
        public float getScaleMultiplier() {
            return scale;
        }
        public float getRotateSpeed() {
            return rotateSpeed;
        }
        public int getColor() {
            return color;
        }
        public int getMaxLifeTime() {
            return maxLifeTime;
        }
        public int getDisappearTime() {
            return disappearTime;
        }
        public int getDeployTime() {
            return deployTime;
        }

        public void writeToBuffer(PacketBuffer buffer) {
            buffer.writeFloat(xr);
            buffer.writeFloat(yr);
            buffer.writeFloat(scale);
            buffer.writeFloat(rotateSpeed);

            buffer.writeInt(color);
            buffer.writeInt(maxLifeTime);
            buffer.writeInt(disappearTime);
            buffer.writeInt(deployTime);

            buffer.writeBoolean(endDisappear);
        }
        public static BeamInfo readBuffer(PacketBuffer buffer) {
            BeamInfo info = new BeamInfo();
            info.xr = buffer.readFloat();
            info.yr = buffer.readFloat();
            info.scale = buffer.readFloat();
            info.rotateSpeed = buffer.readFloat();

            info.color = buffer.readInt();
            info.maxLifeTime = buffer.readInt();
            info.disappearTime = buffer.readInt();
            info.deployTime = buffer.readInt();

            info.endDisappear = buffer.readBoolean();
            return info;
        }
    }
}

