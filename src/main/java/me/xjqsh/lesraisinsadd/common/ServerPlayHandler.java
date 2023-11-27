package me.xjqsh.lesraisinsadd.common;

import com.tac.guns.entity.DamageSourceProjectile;
import me.xjqsh.lesraisinsadd.init.ModItems;
import me.xjqsh.lesraisinsadd.init.ModParticleTypes;
import me.xjqsh.lesraisinsadd.init.ModSounds;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import me.xjqsh.lesraisinsadd.network.PacketHandler;
import me.xjqsh.lesraisinsadd.network.message.SDefeatSpEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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
    public static void onLivingDeath(LivingDeathEvent event){
        if(event.isCanceled() || event.getEntity().level.isClientSide())return;

        if(event.getSource() instanceof DamageSourceProjectile){
            if(!(event.getSource().getEntity() instanceof PlayerEntity))return;
            if(!((PlayerEntity) event.getSource().getEntity()).getOffhandItem().getItem().equals(ModItems.SEAL.get())) return;

            Entity entity = event.getEntity();

            Vector3d v = entity.getEyePosition(1);

            PacketHandler.getPlayChannel().send(
                    PacketDistributor.NEAR.with(()-> new PacketDistributor.TargetPoint(v.x,v.y,v.z,32,entity.level.dimension())
                    ),  new SDefeatSpEffect(v));

        }
        
    }
}
