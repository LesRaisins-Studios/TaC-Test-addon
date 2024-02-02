package me.xjqsh.lesraisinsadd.entity.throwable;

import me.xjqsh.lesraisinsadd.common.data.grenades.AreaGrenadeMeta;
import me.xjqsh.lesraisinsadd.entity.ModifiedAreaEffectCloud;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class EffectGrenadeEntity extends ThrowableItemEntity<AreaGrenadeMeta> {

    public EffectGrenadeEntity(World world, LivingEntity player, int i, ThrowableItem<AreaGrenadeMeta> effectGrenadeMeta) {
        super(ModEntities.EFFECT_GRENADE.get(), world, player, effectGrenadeMeta);
    }

    public EffectGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    @Override
    public void onDeath(){
        if(!level.isClientSide()){
            ModifiedAreaEffectCloud cloud = new ModifiedAreaEffectCloud(this.level,this.getX(),this.getY() + 0.1,this.getZ());
            this.level.addFreshEntity(cloud);
        }
    }


//    @Override
//    public void renderTrailing(){
//        int r = this.color >> 16 & 255;
//        int g = this.color >> 8 & 255;
//        int b = this.color & 255;
//        this.world.addOptionalParticle(this.particle, this.getPosX()+rand.nextDouble()/3.0f, this.getPosY()+rand.nextDouble()/3.0f+0.25, this.getPosZ()+rand.nextDouble()/3.0f,
//                (float)r / 255.0F, (float)g / 255.0F, (float)b / 255.0F);
//    }

}
