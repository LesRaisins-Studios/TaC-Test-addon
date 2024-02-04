package me.xjqsh.lesraisinsadd.entity;

import me.xjqsh.lesraisinsadd.init.ModEntities;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class ModifiedAreaEffectCloud extends AreaEffectCloudEntity implements IEntityAdditionalSpawnData {
    public ModifiedAreaEffectCloud(World worldIn, double x, double y, double z) {
        this(ModEntities.EFFECT_CLOUD.get(),worldIn);
        this.setPos(x, y, z);
    }

    public ModifiedAreaEffectCloud(EntityType<? extends AreaEffectCloudEntity> cloud, World world) {
        super(cloud, world);
    }
    private float height = 1.0f;
    private float maxRadius = 4.0f;
    private boolean extinguishBySmoke = false;

    public void setHeight(float height) {
        this.height = height;
    }

    public void setMaxRadius(float maxRadius) {
        this.maxRadius = maxRadius;
    }

    public void setExtinguishBySmoke(boolean extinguishBySmoke) {
        this.extinguishBySmoke = extinguishBySmoke;
    }

    public boolean extinguishBySmoke() {
        return this.extinguishBySmoke;
    }

    @Override
    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(this.getRadius() * 2.0F, height);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeFloat(this.height);
        buffer.writeFloat(this.maxRadius);
    }

    @Override
    public void readSpawnData(PacketBuffer buffer) {
        height = buffer.readFloat();
        maxRadius = buffer.readFloat();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void setRadius(float radiusIn) {
        if(radiusIn < maxRadius){
            super.setRadius(radiusIn);
        }
    }

}
