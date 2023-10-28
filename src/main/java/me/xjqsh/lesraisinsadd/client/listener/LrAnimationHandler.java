package me.xjqsh.lesraisinsadd.client.listener;

import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.event.GunFireEvent;
import me.xjqsh.lesraisinsadd.client.render.animation.ANGLEAnimationController;
import me.xjqsh.lesraisinsadd.client.render.animation.CROSSBOWAnimationController;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class LrAnimationHandler {
    @SubscribeEvent
    public static void onGunFire(GunFireEvent.Post event) {
        if (!event.isClient()) return;
        if (Minecraft.getInstance().player == null) return;
        if (!event.getPlayer().getUUID().equals(Minecraft.getInstance().player.getUUID())) return;
        GunAnimationController controller = GunAnimationController.fromItem(event.getStack().getItem());
        if (controller instanceof ANGLEAnimationController ) {
            ((ANGLEAnimationController) controller).runFireAnimation();
        }
        if (controller instanceof CROSSBOWAnimationController) {
            ((CROSSBOWAnimationController) controller).runFireAnimation();
        }
    }
}
