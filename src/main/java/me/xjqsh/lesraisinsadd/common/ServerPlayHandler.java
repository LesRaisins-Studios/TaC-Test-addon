package me.xjqsh.lesraisinsadd.common;

import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ServerPlayHandler {
    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event){
        if(event.isCanceled())return;

        if(event.getEntity() instanceof PlayerEntity){
            PlayerEntity entity = (PlayerEntity) event.getEntity();
            if(entity.getMainHandItem().getItem() instanceof RiotShieldItem){
                ItemStack stack = entity.getMainHandItem();
                int i = 1 + MathHelper.floor(event.getAmount());
                stack.hurtAndBreak(i,entity,(item)->{
                    item.broadcastBreakEvent(Hand.MAIN_HAND);
                    net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(entity, stack, Hand.MAIN_HAND);
                });
            }
        }

    }
}
