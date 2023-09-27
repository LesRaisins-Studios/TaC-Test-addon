package me.xjqsh.lesraisinsadd.mixin;

import me.xjqsh.lesraisinsadd.event.ItemCooldownEvent;
import net.minecraft.item.Item;
import net.minecraft.util.CooldownTracker;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(CooldownTracker.class)
public class CooldownTrackerMixin {
    @Inject(method = "removeCooldown", at = @At("HEAD"))
    public void onRemoveCooldown(Item item, CallbackInfo ci){
        MinecraftForge.EVENT_BUS.post(new ItemCooldownEvent(item));
    }
}
