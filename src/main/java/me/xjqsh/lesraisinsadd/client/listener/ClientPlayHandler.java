package me.xjqsh.lesraisinsadd.client.listener;

import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientPlayHandler {
    @SubscribeEvent
    public static void onShieldClickEntity(PlayerInteractEvent.EntityInteractSpecific event){
        if(event.getPlayer().getMainHandItem().getItem() instanceof RiotShieldItem){
            event.setCancellationResult(ActionResultType.PASS);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onShieldClickEntity(PlayerInteractEvent.EntityInteract event){
        if(event.getPlayer().getMainHandItem().getItem() instanceof RiotShieldItem){
            event.setCancellationResult(ActionResultType.PASS);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onShieldClickEntity(PlayerInteractEvent.RightClickBlock event){
        if(event.getPlayer().getMainHandItem().getItem() instanceof RiotShieldItem){
            event.setCancellationResult(ActionResultType.PASS);
            event.setCanceled(true);
        }
    }


}
