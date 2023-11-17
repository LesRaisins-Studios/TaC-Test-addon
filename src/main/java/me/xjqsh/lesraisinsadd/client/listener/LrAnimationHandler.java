package me.xjqsh.lesraisinsadd.client.listener;

import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.event.GunFireEvent;
import me.xjqsh.lesraisinsadd.client.render.animation.IFireController;
import me.xjqsh.lesraisinsadd.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class LrAnimationHandler {
    private static Random random = new Random();
    @SubscribeEvent
    public static void onGunFire(GunFireEvent.Post event) {
        if (!event.isClient()) return;
        if (Minecraft.getInstance().player == null) return;
        if (!event.getPlayer().getUUID().equals(Minecraft.getInstance().player.getUUID())) return;
        GunAnimationController controller = GunAnimationController.fromItem(event.getStack().getItem());
        if (controller instanceof IFireController) {
            if(((IFireController) controller).isFireAnimationRunning()){
                controller.stopAnimation();
            }
            ((IFireController) controller).runFireAnimation();
        }

        if(event.getStack().getItem().equals(ModItems.FLINTLOCK.get())){
            PlayerEntity player = event.getPlayer();
            Vector3d v1 = player.getViewVector(1.0f);
            Vector3d v = player.getEyePosition(1.0f).add(v1.x*2,v1.y*2,v1.z*2);

            for (int i = 0; i < 3; i++) {
                event.getPlayer().level.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,v.x,v.y+0.2,v.z,
                        random.nextDouble()*0.03,0.04,random.nextDouble()*0.03);
            }

            event.getPlayer().level.addParticle(ParticleTypes.LARGE_SMOKE,v.x,v.y+0.2,v.z,
                    0,0,0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW, receiveCanceled = true)
    public static void onGunFire(GunFireEvent.Pre event) {
        if (event.isClient()) {
            if (Minecraft.getInstance().player != null) {
                if (event.getPlayer().getUUID().equals(Minecraft.getInstance().player.getUUID())) {
                    GunAnimationController controller = GunAnimationController.fromItem(event.getStack().getItem());
                    if (controller instanceof IFireController && ((IFireController) controller).isFireAnimationRunning()) {
                        if(event.isCanceled()){
                            event.setCanceled(false);
                        }
                    }
                }
            }
        }
    }

}
