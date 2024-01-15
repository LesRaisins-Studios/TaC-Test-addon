//package me.xjqsh.lesraisinsadd.entity.throwable;
//
//import me.xjqsh.lesraisinsadd.entity.ModifiedAreaEffectCloud;
//import me.xjqsh.lesraisinsadd.entity.data.EffectGrenadeMeta;
//import me.xjqsh.lesraisinsadd.init.ModEntities;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.potion.EffectInstance;
//import net.minecraft.world.World;
//
//public class EffectCloudGrenadeEntity extends ThrowableItemEntity {
//
//    public EffectCloudGrenadeEntity(World world, LivingEntity player, EffectGrenadeMeta effectGrenadeMeta) {
//        super(ModEntities.THROWABLE_EFFECT_GRENADE.get(), world, player, effectGrenadeMeta);
//    }
//
//    public EffectCloudGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
//        super(entityType, worldIn);
//    }
//
//    @Override
//    public EffectGrenadeMeta getMeta(){
//        return (EffectGrenadeMeta) super.getMeta();
//    }
//
//    @Override
//    public void onDeath(){
//        if(!level.isClientSide()){
//            releaseEffectCloud();
////            this.level.playSound(null,this.getX(),this.getY(),this.getZ(),
////                    ModSounds.ENTITY_MOLOTOV_EXPLOSION.get(), SoundCategory.AMBIENT, 6, 1);
//        }
//    }
//
//
////    @Override
////    public void renderTrailing(){
////        int r = this.color >> 16 & 255;
////        int g = this.color >> 8 & 255;
////        int b = this.color & 255;
////        this.world.addOptionalParticle(this.particle, this.getPosX()+rand.nextDouble()/3.0f, this.getPosY()+rand.nextDouble()/3.0f+0.25, this.getPosZ()+rand.nextDouble()/3.0f,
////                (float)r / 255.0F, (float)g / 255.0F, (float)b / 255.0F);
////    }
//
//    public void releaseEffectCloud(){
//        ModifiedAreaEffectCloud effectCloud = new ModifiedAreaEffectCloud(this.level,this.getX(),this.getY()+0.15,this.getZ());
//
//        for(EffectInstance effect : getMeta().getEffects()){
//            effectCloud.addEffect(new EffectInstance(effect));
//        }
//
////        effectCloud.setRadiusPerTick(this.effectThrowableMeta.getSpreadPreTick());
////        effectCloud.setFixedColor(this.effectThrowableMeta.getColor());
////        effectCloud.setHeight(this.effectThrowableMeta.getAreaHeight());
////        effectCloud.setRadius(this.effectThrowableMeta.getMinRadius());
////        effectCloud.setExtinguishBySmoke(this.effectThrowableMeta.isExtinguishBySmoke());
////        effectCloud.setMaxRadius(this.effectThrowableMeta.getMaxRadius());
////        effectCloud.setDuration(this.effectThrowableMeta.getAreaDuration());
////        effectCloud.setParticle(this.effectThrowableMeta.getParticle());
//
//        this.level.addFreshEntity(effectCloud);
//    }
//}
