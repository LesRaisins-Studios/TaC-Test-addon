package me.xjqsh.lesraisinsadd.effect;

import me.xjqsh.lesraisinsadd.Config;
import me.xjqsh.lesraisinsadd.util.EntityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.server.ServerWorld;

import java.util.Collections;
import java.util.List;

public class VirusEffect extends Effect implements ITickEffect {
    public static final DamageSource source = new DamageSource("lesraisins.virus");
    public VirusEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier){}

    @Override
    public void applyTick(LivingEntity entity, int amplifier, int duration) {
        if(entity.level instanceof ServerWorld) {
            ServerWorld level = (ServerWorld) entity.level;

            if (duration % 60 == 0) {
                level.sendParticles(ParticleTypes.SNEEZE,
                        entity.getX(), entity.getEyeY() + 0.5, entity.getZ(), 16,
                        3.5, 0.5, 3.5, 0);

                List<LivingEntity> entities = EntityUtil.getEntitiesInRadius(level,
                        Config.server.virusSpread.get() ? PlayerEntity.class : LivingEntity.class,
                        entity.getEyePosition(1.0f), 3.5);

                for (LivingEntity e : entities) {
                    if (!e.hasEffect(this)) {
                        e.addEffect(new TickEffectInstance(this, 300));
                        return;
                    }
                }
            }


            if (duration % 20 == 0) {
                level.sendParticles(ParticleTypes.SNEEZE,
                        entity.getX(), entity.getEyeY() + 0.5, entity.getZ(), 3,
                        0.5, 0.1, 0.5, 0);

                entity.hurt(source, 1f + amplifier);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier){
        return false;
    }

}
