package me.xjqsh.lesraisinsadd.entity.throwable;

import me.xjqsh.lesraisinsadd.common.data.grenades.AreaGrenadeMeta;
import me.xjqsh.lesraisinsadd.effect.TickEffectInstance;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import me.xjqsh.lesraisinsadd.util.EntityUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class AreaGrenadeEntity extends ThrowableItemEntity<AreaGrenadeMeta> {
    public static class AffectArea{
        float strength;
        BlockPos pos;
        public AffectArea(BlockPos pos, float strength){
            this.strength=strength;
            this.pos=pos;
        }
    }
    private Set<BlockPos> affect = new HashSet<>();
    private Set<BlockPos> set = new HashSet<>();
    private Queue<AffectArea> queue = new ArrayDeque<>();

    public AreaGrenadeEntity(World world, LivingEntity player, int useTick, ThrowableItem<AreaGrenadeMeta> effectGrenadeMeta) {
        super(ModEntities.AREA_GRENADE.get(), world, player, effectGrenadeMeta);
    }

    public AreaGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        if(tickCount<80)return;

        float radius = Math.min(tickCount-80,60)/60f * 8;

        if(queue.isEmpty()){
            // if the queue is empty, then we thought a turn of traversal has complete
            this.affect.clear();
            this.affect.addAll(set);
            this.set.clear();
            BlockPos pos = new BlockPos(this.getEyePosition(1.0f));
            BlockState bs = this.level.getBlockState(pos);
            if (!bs.canOcclude() && !(bs.getBlock() instanceof FlowingFluidBlock)){
                this.add(new BlockPos(this.getEyePosition(1.0f)),12);
            }
        }

        for (int i = 0; i < 64; i++) {
            AffectArea area = queue.peek();
            if(area==null) break;
            BlockPos pos = area.pos;

            if(this.level.isClientSide()){
                releaseParticle(pos);
            }

            if(area.strength>0) {
                for (Direction d : Direction.values()) {
                    BlockPos next = pos.relative(d);
                    BlockState bs = this.level.getBlockState(next);
                    if (!bs.canOcclude() && !(bs.getBlock() instanceof FlowingFluidBlock)){

                        if(next.distSqr(this.getEyePosition(1.0f), false) < radius * radius){
                            add(next, area.strength -  (d.equals(Direction.UP) ? 1.5f : 0.8f));
                        }
                    }
                }
            }
            queue.poll();
        }

        if(this.level.isClientSide() || tickCount%10!=0) return;

        for(LivingEntity entity : EntityUtil.getEntitiesInRadius(this.level,
                this.getMeta().isOnlyAffectPlayer() ? PlayerEntity.class : LivingEntity.class,
                this.getEyePosition(1.0f),radius)){
            if(this.isInArea(entity)){
                this.affectEntity(entity);
            }
        }
    }

    private void releaseParticle(BlockPos pos) {
        Particle particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.CLOUD,
                pos.getX() + (this.random.nextDouble()-0.5)*0.2,
                pos.getY() + 0.5 + (this.random.nextDouble()-0.5)*0.2,
                pos.getZ() + (this.random.nextDouble()-0.5)*0.2,
                (this.random.nextDouble()-0.5)*0.1,(this.random.nextDouble()-0.6)*0.1,(this.random.nextDouble()-0.5)*0.1);
        if (particle != null) {
            particle.setLifetime(60);
            particle.scale(0.5f);
            int[] color = this.getMeta().getColor();
            particle.setColor(color[0]/255f,color[1]/255f,color[2]/255f);
        }
    }

    private void add(BlockPos pos, float i){
        if(!this.set.contains(pos)){
            this.queue.add(new AffectArea(pos,i));
            this.set.add(pos);
        }
    }

    public boolean isInArea(Entity entity){
        BlockPos pos = new BlockPos(entity.getEyePosition(1.0f));
        return affect.contains(pos);
    }

    public void affectEntity(LivingEntity entity){
        Effect effect = ForgeRegistries.POTIONS.getValue(this.getMeta().getEffect());
        if(effect==null) return;

        EffectInstance instance = entity.getEffect(effect);
        if(instance == null || instance.getDuration()<=61){
            if(this.getMeta().isSpecialEffectInstance()){
                entity.addEffect(new TickEffectInstance(effect,this.getMeta().getEffectDuration(),this.getMeta().getEffectAmplifier()));
            }else {
                entity.addEffect(new EffectInstance(effect,this.getMeta().getEffectDuration(),this.getMeta().getEffectAmplifier()));
            }

        }
    }
}
