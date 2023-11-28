package me.xjqsh.lesraisinsadd.mixin;

import com.tac.guns.util.GunModifierHelper;
import me.xjqsh.lesraisinsadd.item.AceItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GunModifierHelper.class,remap = false)
public class GunModifierHelperMixin {
    @Inject(method = "getAdditionalDamage",at = @At("RETURN"), cancellable = true)
    private static void addExtraDamage(ItemStack weapon, CallbackInfoReturnable<Float> cir){
        if(weapon.getItem() instanceof AceItem){
            if(AceItem.isEnhanced(weapon)){
                cir.setReturnValue(cir.getReturnValue()+5);
            }
        }
    }
}
