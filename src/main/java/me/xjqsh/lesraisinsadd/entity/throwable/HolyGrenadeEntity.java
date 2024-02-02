package me.xjqsh.lesraisinsadd.entity.throwable;


import com.tac.guns.entity.DamageSourceExplosion;
import com.tac.guns.entity.IExplosionProvider;
import me.xjqsh.lesraisinsadd.entity.BeamEntity;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.common.data.grenades.HolyGrenadeMeta;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import me.xjqsh.lesraisinsadd.util.EntityUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.awt.*;

public class HolyGrenadeEntity extends ThrowableItemEntity<HolyGrenadeMeta> implements IExplosionProvider {
    public HolyGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    public HolyGrenadeEntity(World world, LivingEntity player, int useTick, ThrowableItem<HolyGrenadeMeta> meta) {
        super(ModEntities.HOLY_GRENADE.get(), world, player, meta);
        this.maxLife = meta.getMeta().getMaxLife() - useTick;
    }

    @Override
    public void onDeath() {
        if(!this.level.isClientSide()){
            for (LivingEntity s : EntityUtil.getEntitiesInRadius(this.level, LivingEntity.class, this.getPosition(1.0f),5)){
                if(s instanceof MonsterEntity){
                    if(this.getMeta().createBeam()){
                        BeamEntity entity = new BeamEntity(ModEntities.BEAM.get(),this.level,
                                s.getPosition(1.0f).add(0,0,0));

                        entity.xRot = -90;
                        entity.yRot = 0;
                        this.level.addFreshEntity(entity);
                    }

                    s.hurt(DamageSource.indirectMagic(this,this.getOwner()),this.getMeta().getHostileDamage());
                }else {
                    s.heal(this.getMeta().getFriendlyHeal());
                }
            }
        }else {
            Vector3d center = this.getPosition(1.0f);
            Color color = new Color(253, 218, 0,255);

            this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER,center.x,center.y+0.2,center.z,
                    0,0,0);

            this.level.playLocalSound(center.x, center.y, center.z, SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F,
                    (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);

            for (int i = 0; i < 128; i++) {
                Vector3f v = new Vector3f(this.random.nextFloat()-0.5f,0,this.random.nextFloat()-0.5f);
                v.normalize();
                v.mul(5.0f*this.random.nextFloat());
                this.level.addParticle(ParticleTypes.ENTITY_EFFECT,center.x+v.x(),center.y+0.2,center.z+v.z(),
                        color.getRed()/255f,color.getGreen()/255f,color.getBlue()/255f);
            }
        }
    }

    @Override
    public DamageSourceExplosion createDamageSource() {
        return new DamageSourceExplosion(this,this.getItem().getItem().getRegistryName());
    }

    @Override
    public void renderTrailing() {}
}
