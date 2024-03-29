package me.xjqsh.lesraisinsadd.common;

import com.tac.guns.Config;
import com.tac.guns.common.Gun;
import com.tac.guns.entity.DamageSourceProjectile;
import com.tac.guns.event.GunFireEvent;
import com.tac.guns.event.GunReloadEvent;
import com.tac.guns.item.GunItem;
import com.tac.guns.network.message.MessageGunSound;
import com.tac.guns.util.GunModifierHelper;
import me.xjqsh.lesraisinsadd.common.data.NetworkDataManager;
import me.xjqsh.lesraisinsadd.entity.BeamEntity;
import me.xjqsh.lesraisinsadd.entity.X26HookEntity;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.init.ModItems;
import me.xjqsh.lesraisinsadd.init.ModTags;
import me.xjqsh.lesraisinsadd.item.interfaces.IDefeatAction;
import me.xjqsh.lesraisinsadd.item.interfaces.IFireAction;
import me.xjqsh.lesraisinsadd.item.interfaces.IReloadAction;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import me.xjqsh.lesraisinsadd.network.PacketHandler;
import me.xjqsh.lesraisinsadd.network.message.SCustomMeta;
import me.xjqsh.lesraisinsadd.network.message.SDefeatSpEffect;
import me.xjqsh.lesraisinsadd.network.message.SPlayerReload;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class ServerPlayHandler {
    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent e) {
        if (e.getPlayer() == null) {
            PacketHandler.getPlayChannel().send(PacketDistributor.ALL.noArg(),
                    new SCustomMeta(NetworkDataManager.getInstance().getMetaMap()));
            return;
        }

        PacketHandler.getPlayChannel().send(PacketDistributor.PLAYER.with(e::getPlayer),
                new SCustomMeta(NetworkDataManager.getInstance().getMetaMap()));
    }

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

        if(ModItems.BAM4.get().equals(weapon.getItem()) || ModItems.MP18.get().equals(weapon.getItem())){
            PacketHandler.getPlayChannel().send(
                    PacketDistributor.DIMENSION.with(()-> event.getPlayer().level.dimension()),
                    new SPlayerReload(event.getPlayer().getUUID())
            );
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onX26GunFire(GunFireEvent.Pre event){
        if(event.isCanceled() || event.getEntity().level.isClientSide())return;
        ItemStack weapon = event.getStack();
        if(ModItems.X26.get().getItem().equals(weapon.getItem())){
            World world = event.getPlayer().level;

            GunItem item = (GunItem) weapon.getItem();
            Gun modifiedGun = item.getModifiedGun(weapon);

            PlayerEntity player = event.getPlayer();
            event.setCanceled(true);

            X26HookEntity hook = new X26HookEntity(event.getPlayer());

            hook.shootFromRotation(event.getPlayer(), event.getPlayer().xRot, event.getPlayer().yRot,
                    0.0F, 5.0f, 2.5F);

            hook.setDamage(modifiedGun.getProjectile().getDamage());

            event.getPlayer().level.addFreshEntity(hook);

            boolean silenced = GunModifierHelper.isSilencedFire(weapon);
            ResourceLocation fireSound = silenced ? modifiedGun.getSounds().getSilencedFire() : modifiedGun.getSounds().getFire();
            if (fireSound != null) {
                double posX = player.getX();
                double posY = player.getY() + player.getEyeHeight();
                double posZ = player.getZ();
                float volume = GunModifierHelper.getFireSoundVolume(weapon);

                // PATCH NOTE: Neko required to remove the random pitch effect in sound
                final float pitch = 0.9F + world.random.nextFloat() * 0.125F;

                double radius = GunModifierHelper.getModifiedFireSoundRadius(weapon, Config.SERVER.gunShotMaxDistance.get());
                boolean muzzle = modifiedGun.getDisplay().getFlash() != null;
                MessageGunSound messageSound = new MessageGunSound(fireSound, SoundCategory.PLAYERS,
                        (float) posX, (float) posY, (float) posZ, volume, pitch, player.getId(), muzzle, false);
                PacketDistributor.TargetPoint targetPoint = new PacketDistributor.TargetPoint(posX, posY, posZ, radius, player.level.dimension());
                com.tac.guns.network.PacketHandler.getPlayChannel().send(PacketDistributor.NEAR.with(() -> targetPoint), messageSound);
            }

            if (!player.isCreative()) {
                CompoundNBT tag = weapon.getOrCreateTag();
                if (!tag.getBoolean("IgnoreAmmo")) {
                    tag.putInt("AmmoCount", Math.max(0, tag.getInt("AmmoCount") - 1));
                }
            }

            MinecraftForge.EVENT_BUS.post(new GunFireEvent.Post(event.getPlayer(), weapon));
        }
//        else if(ModItems.TXC.get().getItem().equals(weapon.getItem())){
//            BeamEntity entity = new BeamEntity(ModEntities.BEAM.get(),event.getPlayer().level,
//                    event.getPlayer().getEyePosition(1.0f)
//                            .add(0,-0.15,0)
//                            .add(event.getPlayer().getForward().multiply(0.2,0.2,0.2)));
//            entity.xRot = event.getPlayer().getViewXRot(1.0f);
//            entity.yRot = event.getPlayer().getViewYRot(1.0f);
//            event.getPlayer().level.addFreshEntity(entity);
//        }
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
