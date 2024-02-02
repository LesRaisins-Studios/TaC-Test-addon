package me.xjqsh.lesraisinsadd.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import me.xjqsh.lesraisinsadd.init.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OverlayRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OverlayRenderer.class)
public abstract class OverlayRendererMixin {

    @Shadow
    private static void renderFire(Minecraft p_228737_0_, MatrixStack p_228737_1_) {
    }

    @Inject(method = "renderScreenEffect",at = @At("RETURN"))
    private static void renderScreenEffect(Minecraft mc, MatrixStack stack, CallbackInfo ci){
        if (mc.player != null) {
            if(mc.player.isOnFire() || mc.player.isSpectator()) return;
            if(mc.player.hasEffect(ModEffects.BURNED.get())){
                RenderSystem.disableAlphaTest();
                renderFire(mc,stack);
                RenderSystem.enableAlphaTest();
            }
        }

    }
}
