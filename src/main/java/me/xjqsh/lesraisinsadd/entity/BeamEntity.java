package me.xjqsh.lesraisinsadd.entity;

import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

import java.awt.*;

public class BeamEntity extends Entity implements IEntityAdditionalSpawnData {
    private float length = 1.0f;
    public BeamEntity(EntityType<?> entityTypeIn, World worldIn, Vector3d pos) {
        super(entityTypeIn, worldIn);
        this.setPos(pos.x,pos.y,pos.z);
    }

    public BeamEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        if(this.tickCount > 6){
            this.remove();
        }
    }

    public float calcScale(){
        if(tickCount < 2){
            return ((float)tickCount) / 2;
        } else if (tickCount>= 4) {
            return ((float) 6 - tickCount) / 2;
        }else{
            return 1.0f;
        }
    }

    @Override
    public boolean shouldRender(double p_145770_1_, double p_145770_3_, double p_145770_5_) {
        return true;
    }

    public float getLength() {
        return length;
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

    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {

    }


    public float getYr() {
        return this.yRot;
    }

    public float getXr() {
        return this.xRot;
    }

    public int getRotateSpeed() {
        return 1;
    }

    public int getColor() {
        return new Color(255, 238, 79,255).getRGB();
    }
}

