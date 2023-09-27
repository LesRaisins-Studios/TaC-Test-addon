package me.xjqsh.lesraisinsadd.client.listener;

import me.xjqsh.lesraisinsadd.event.ItemCooldownEvent;
import me.xjqsh.lesraisinsadd.init.ModSounds;
import me.xjqsh.lesraisinsadd.item.shield.FlashShieldItem;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import net.minecraft.client.Minecraft;
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

    @SubscribeEvent
    public static void onCooldownEnd(ItemCooldownEvent event){
        if(event.getItem() instanceof FlashShieldItem){
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.playSound(ModSounds.flash_shield_charged.get(),1.0f,1.0f);
            }
        }
    }
}
