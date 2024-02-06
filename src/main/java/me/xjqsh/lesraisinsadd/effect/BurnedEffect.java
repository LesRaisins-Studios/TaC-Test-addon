package me.xjqsh.lesraisinsadd.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

import java.util.Collections;
import java.util.List;

public class BurnedEffect extends Effect {
    public static final DamageSource burned = new DamageSource("lesraisins.burned").bypassArmor().setIsFire();
    public BurnedEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier){
        if(!entity.fireImmune()){
            entity.hurt(burned,2f + amplifier);
            entity.setSecondsOnFire(2);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier){
        return duration % 20 == 0;
    }

}
