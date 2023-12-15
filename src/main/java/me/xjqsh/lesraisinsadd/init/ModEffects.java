package me.xjqsh.lesraisinsadd.init;


import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.effect.BurnedEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class ModEffects
{
    public static final DeferredRegister<Effect> REGISTER = DeferredRegister.create(ForgeRegistries.POTIONS, Reference.MOD_ID);
    public static final RegistryObject<BurnedEffect> BURNED = REGISTER.register("burned",() -> new BurnedEffect(EffectType.HARMFUL,0));
}
