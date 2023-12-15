package me.xjqsh.lesraisinsadd.effect;

import me.xjqsh.lesraisinsadd.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber()
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
            entityLivingBaseIn.setSecondsOnFire(2);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier){
        return duration % 20 == 0;
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event){
        if(event.getEntityLiving().hasEffect(ModEffects.BURNED.get())){
            if(event.getSource().isFire())
                event.setAmount(event.getAmount() * 4.0f);
        }
    }
}
