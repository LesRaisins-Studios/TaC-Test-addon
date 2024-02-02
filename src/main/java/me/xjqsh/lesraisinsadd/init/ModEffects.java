package me.xjqsh.lesraisinsadd.init;


import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.effect.BurnedEffect;
import me.xjqsh.lesraisinsadd.effect.PoisionEffect;
import me.xjqsh.lesraisinsadd.effect.VirusEffect;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
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
    public static final RegistryObject<PoisionEffect> POISON = REGISTER.register("deep_poison",() -> new PoisionEffect(EffectType.HARMFUL,0));
    public static final RegistryObject<VirusEffect> VIRUS = REGISTER.register("virus",() -> (VirusEffect) new VirusEffect(EffectType.HARMFUL,0).addAttributeModifier(
            Attributes.MOVEMENT_SPEED,"B0541809-C72A-F25D-5CD0-27E1D644D2E5", -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL
    ));

}
