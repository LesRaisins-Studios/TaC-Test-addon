package me.xjqsh.lesraisinsadd.entity.throwable;

import me.xjqsh.lesraisinsadd.entity.ModifiedAreaEffectCloud;
import me.xjqsh.lesraisinsadd.common.data.grenades.SmokeGrenadeMeta;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.init.ModParticleTypes;
import me.xjqsh.lesraisinsadd.init.ModSounds;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class SmokeGrenadeEntity extends ThrowableItemEntity<SmokeGrenadeMeta> {
    public SmokeGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    public SmokeGrenadeEntity(World world, LivingEntity player,int useTick, ThrowableItem<SmokeGrenadeMeta> meta) {
        super(ModEntities.THROWABLE_SMOKE_GRENADE.get(), world, player, meta);
    }

    @Override
    public void tick(){
        super.tick();
        double y = this.getY() + 0.15;
        if(tickCount==45){
            this.level.playSound(null, this.getX(), y, this.getZ(),
                    ModSounds.ENTITY_SMOKE_GRENADE_EXPLOSION.get(), SoundCategory.BLOCKS,
                    3, (1 + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);
        }
        if (this.level.isClientSide()) {
            Minecraft mc = Minecraft.getInstance();
            ParticleManager particleManager = mc.particleEngine;

            if(tickCount<40){
                level.addParticle(ModParticleTypes.GRENADE_SMOKE.get(),this.getX(),
                        y, this.getZ(), 0, 0.05, 0);
            }else {
                int amount = Math.min( (tickCount-40), 30);
                int radius = amount/5;
                for (int i = 0; i < amount; i++) {
                    double theta = this.random.nextDouble() * 2 * Math.PI;
                    double phi = Math.acos(2*this.random.nextDouble()-1);

                    double xs = radius * Math.sin(phi) * Math.cos(theta) * 0.05;
                    double ys =
                            radius * Math.abs(Math.sin(phi) * Math.sin(theta)) * this.random.nextDouble() * 0.05;
                    double zs = radius * Math.cos(phi) * 0.05;

                    Particle smoke = particleManager.createParticle(ModParticleTypes.GRENADE_SMOKE.get(),this.getX(),
                            y, this.getZ(), xs, ys, zs);
                    if (smoke != null) {
                        smoke.setLifetime(100);
                    }
                }
            }
        }else{
            if(tickCount<60 || tickCount%20!=0)return;
            int amount = Math.min( (tickCount-40), 30);
            double radius = amount/5.5f;
            double x1 = this.getX()-radius;
            double x2 = this.getX()+radius;
            double y1 = this.getY()-radius;
            double y2 = this.getY()+radius;
            double z1 = this.getZ()-radius;
            double z2 = this.getZ()+radius;

            AxisAlignedBB bb = new AxisAlignedBB(x1,y1,z1,x2,y2,z2);
            List<ModifiedAreaEffectCloud> list1 = this.level.getEntitiesOfClass(ModifiedAreaEffectCloud.class, bb);
            for (ModifiedAreaEffectCloud cloud : list1) {
                double d0 = cloud.getX() - this.getX();
                double d1 = cloud.getZ() - this.getZ();
                double d2 = d0 * d0 + d1 * d1;
                if (d2 <= (radius * radius) && cloud.extinguishBySmoke()) {
                    cloud.remove();
                }
            }
        }
    }

    @Override
    public void playImpactSound(BlockRayTraceResult result){
        SoundEvent sound = ModSounds.ENTITY_SMOKE_GRENADE_HIT.get();
        this.level.playSound(null, result.getLocation().x, result.getLocation().y, result.getLocation().z,
                sound, SoundCategory.AMBIENT, 1.0F, 1.0F);
    }
}
