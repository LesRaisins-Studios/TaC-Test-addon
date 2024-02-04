package me.xjqsh.lesraisinsadd.entity.throwable;

import me.xjqsh.lesraisinsadd.common.data.grenades.CloudGrenadeMeta;
import me.xjqsh.lesraisinsadd.entity.ModifiedAreaEffectCloud;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class CloudGrenadeEntity extends ThrowableItemEntity<CloudGrenadeMeta> {


    public CloudGrenadeEntity(World world, LivingEntity player, int i, ThrowableItem<CloudGrenadeMeta> effectGrenadeMeta) {
        super(ModEntities.EFFECT_GRENADE.get(), world, player, effectGrenadeMeta);
    }

    public CloudGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    @Override
    public void onDeath(){
        if(!level.isClientSide()){
            ModifiedAreaEffectCloud cloud = new ModifiedAreaEffectCloud(this.level,this.getX(),this.getY() + 0.1,this.getZ());
            this.level.addFreshEntity(cloud);

            cloud.setMaxRadius(this.getMeta().getCloud().getMaxRadius());
            cloud.setRadius(this.getMeta().getCloud().getStartRadius());
            cloud.setRadiusPerTick(this.getMeta().getCloud().getRadiusPerTick());
            cloud.setHeight(this.getMeta().getCloud().getHeight());

            cloud.setDuration(this.getMeta().getCloud().getCloudLifeTime());
            cloud.setExtinguishBySmoke(this.getMeta().getCloud().isExtinguishBySmoke());

            ParticleType<?> type = ForgeRegistries.PARTICLE_TYPES.getValue(this.getMeta().getCloud().getParticle());
            if (type instanceof IParticleData) {
                cloud.setParticle((IParticleData) type);
            }

            Effect effect = ForgeRegistries.POTIONS.getValue(this.getMeta().getEffect());
            if(effect != null){
                cloud.addEffect(new EffectInstance(effect,
                        this.getMeta().getEffectDuration(),
                        this.getMeta().getEffectAmplifier()));
            }

        }
    }




}
