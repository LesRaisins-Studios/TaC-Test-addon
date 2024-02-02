package me.xjqsh.lesraisinsadd.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class TickEffectInstance extends EffectInstance {
    public TickEffectInstance(Effect effect, int effectDuration) {
        super(effect, effectDuration);
    }

    public TickEffectInstance(Effect effect, int effectDuration, int effectAmplifier) {
        super(effect, effectDuration, effectAmplifier);
    }

    public boolean tick(LivingEntity entity, Runnable runnable) {
        if (this.getDuration() > 0) {
            if(this.getEffect() instanceof ITickEffect){
                ((ITickEffect) this.getEffect()).applyTick(entity,this.getAmplifier(),this.getDuration());
            }
        }

        return super.tick(entity,runnable);
    }
}
