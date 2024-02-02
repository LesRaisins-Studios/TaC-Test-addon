package me.xjqsh.lesraisinsadd.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.Collections;
import java.util.List;

public class BurnedEffect extends Effect {
    public BurnedEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier){
        if(!entityLivingBaseIn.fireImmune()){

        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier){
        return duration % 20 == 0;
    }

}
