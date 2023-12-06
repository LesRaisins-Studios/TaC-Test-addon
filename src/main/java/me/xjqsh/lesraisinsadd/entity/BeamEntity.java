package me.xjqsh.lesraisinsadd.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BeamEntity extends Entity {
    private static final DataParameter<Integer> COUNTER = EntityDataManager.defineId(BeamEntity.class, DataSerializers.INT);

    public BeamEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);

    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(COUNTER, 0);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.tickCount>10){
            this.remove();
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        this.entityData.set(COUNTER, nbt.getInt("counter"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        nbt.putInt("counter", this.entityData.get(COUNTER));
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

