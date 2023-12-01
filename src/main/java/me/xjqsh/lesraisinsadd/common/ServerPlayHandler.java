package me.xjqsh.lesraisinsadd.common;

import com.tac.guns.entity.DamageSourceProjectile;
import com.tac.guns.event.GunFireEvent;
import com.tac.guns.event.GunReloadEvent;
import me.xjqsh.lesraisinsadd.init.ModItems;
import me.xjqsh.lesraisinsadd.init.ModTags;
import me.xjqsh.lesraisinsadd.item.AceItem;
import me.xjqsh.lesraisinsadd.item.interfaces.IDefeatAction;
import me.xjqsh.lesraisinsadd.item.interfaces.IFireAction;
import me.xjqsh.lesraisinsadd.item.interfaces.IReloadAction;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import me.xjqsh.lesraisinsadd.network.PacketHandler;
import me.xjqsh.lesraisinsadd.network.message.SDefeatSpEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

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

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event){
        if(event.isCanceled())return;

        if(event.getSource() instanceof DamageSourceProjectile){
            if(!event.getEntity().getType().is(ModTags.caster))return;
            DamageSourceProjectile source = (DamageSourceProjectile) event.getSource();
            ItemStack weapon = source.getWeapon();
            if(weapon.getItem().equals(ModItems.ENCORE.get())){
                event.setAmount(event.getAmount()*2);
            }
        }

    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event){
        if(event.isCanceled() || event.getEntity().level.isClientSide())return;

        if(event.getSource() instanceof DamageSourceProjectile){
            DamageSourceProjectile source = (DamageSourceProjectile) event.getSource();

            if(!(event.getSource().getEntity() instanceof PlayerEntity))return;
            PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            Entity suffer = event.getEntity();

            if(player.getMainHandItem().getItem() instanceof IDefeatAction){
                ((IDefeatAction) player.getMainHandItem().getItem()).onGunDefeat(event,player.getMainHandItem(),source);
            }

            if(!player.getOffhandItem().getItem().equals(ModItems.SEAL.get())) return;

            Vector3d v = suffer.getEyePosition(1);

            PacketHandler.getPlayChannel().send(
                    PacketDistributor.NEAR.with(()-> new PacketDistributor.TargetPoint(v.x,v.y,v.z,64,suffer.level.dimension())
                    ),  new SDefeatSpEffect(v));
        }
        
    }

    @SubscribeEvent
    public static void onGunReload(GunReloadEvent.Post event){
        if(event.isCanceled() || event.getEntity().level.isClientSide())return;
        ItemStack weapon = event.getStack();
        if(event.getStack().getItem() instanceof IReloadAction){
            ((IReloadAction) event.getStack().getItem()).onGunReload(event,weapon);
        }

    }

    @SubscribeEvent
    public static void onGunFire(GunFireEvent.Post event){
        if(event.isCanceled() || event.getEntity().level.isClientSide())return;
        ItemStack weapon = event.getStack();
        if(event.getStack().getItem() instanceof IFireAction){
            ((IFireAction) event.getStack().getItem()).onGunFire(event,weapon);
        }

    }
}
