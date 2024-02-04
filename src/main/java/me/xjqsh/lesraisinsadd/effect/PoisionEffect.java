package me.xjqsh.lesraisinsadd.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

import java.util.Collections;
import java.util.List;

public class PoisionEffect extends Effect implements ITickEffect {
    public static final DamageSource source = new DamageSource("lesraisins.poison").bypassArmor();
    public static final DamageSource drown = new DamageSource("lesraisins.poison.drown").bypassArmor();
    public PoisionEffect(EffectType typeIn, int liquidColorIn) {
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
        entity.setAirSupply(Math.max(-10,entity.getAirSupply()-6));

        if(entity.getAirSupply()<0 && duration%10==0){
            entity.hurt(drown,2f + 1f * amplifier);
        }else if(duration%20==0){
            entity.hurt(source,1f + amplifier);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier){
        return false;
    }

}
