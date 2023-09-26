package me.xjqsh.lesraisinsadd.mixin;

import com.tac.guns.entity.DamageSourceProjectile;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin{
    @Shadow public abstract ItemStack getMainHandItem();

    @Inject(method = "isDamageSourceBlocked", at = @At("HEAD"), cancellable = true)
    public void block(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if(source instanceof DamageSourceProjectile){
            if(this.getMainHandItem()!=null){
                if(this.getMainHandItem().getItem() instanceof RiotShieldItem){
                    if(((Object)this) instanceof PlayerEntity){
                        float x = ((PlayerEntity)(Object)this).getAttackStrengthScale(0.5f);
                        if(x < 1.0f){
                            cir.setReturnValue(false);
                        }else{
                            Vector3d vector3d2 = source.getSourcePosition();
                            if (vector3d2 != null) {
                                Vector3d vector3d = ((Entity)(Object)this).getViewVector(1.0F);
                                Vector3d vector3d1 = vector3d2.vectorTo(((Entity)(Object)this).position()).normalize();
                                vector3d1 = new Vector3d(vector3d1.x, 0.0D, vector3d1.z);
                                if (vector3d1.dot(vector3d) < 0.0D) {
                                    cir.setReturnValue(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "isBlocking", at = @At("HEAD"), cancellable = true)
    public void isBlocking(CallbackInfoReturnable<Boolean> cir) {
        if(this.getMainHandItem().getItem() instanceof RiotShieldItem){
            if(((Object)this) instanceof PlayerEntity){
                float x = ((PlayerEntity)(Object)this).getAttackStrengthScale(0.5f);
                if(x < 1.0f){
                    cir.setReturnValue(false);
                }else{
                    cir.setReturnValue(true);
                }
            }
        }

    }
}
