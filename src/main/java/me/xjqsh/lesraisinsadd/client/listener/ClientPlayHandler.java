package me.xjqsh.lesraisinsadd.client.listener;

import me.xjqsh.lesraisinsadd.event.ItemCooldownEvent;
import me.xjqsh.lesraisinsadd.init.ModItems;
import me.xjqsh.lesraisinsadd.init.ModSounds;
import me.xjqsh.lesraisinsadd.item.shield.FlashShieldItem;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
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

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void addInformationEvent(ItemTooltipEvent event) {
        ItemStack stack= event.getItemStack();
        Item item = stack.getItem();
        if(item.equals(ModItems.THE_LAST_WORD.get())){
            event.getToolTip().add(new TranslationTextComponent("tooltip.lesraisins.the_last_word_1")
                    .withStyle(TextFormatting.ITALIC));
            event.getToolTip().add(new TranslationTextComponent("tooltip.lesraisins.the_last_word_2")
                    .withStyle(TextFormatting.ITALIC));
        }
    }
}
