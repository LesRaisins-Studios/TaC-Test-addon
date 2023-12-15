package me.xjqsh.lesraisinsadd.client.listener;


import com.tac.guns.client.Keys;
import com.tac.guns.client.handler.ReloadHandler;
import com.tac.guns.client.render.animation.module.GunAnimationController;
import com.tac.guns.item.TransitionalTypes.TimelessGunItem;
import me.xjqsh.lesraisinsadd.Config;
import me.xjqsh.lesraisinsadd.client.particle.ReloadingParticle;
import me.xjqsh.lesraisinsadd.event.ItemCooldownEvent;
import me.xjqsh.lesraisinsadd.init.ModItems;
import me.xjqsh.lesraisinsadd.init.ModParticleTypes;
import me.xjqsh.lesraisinsadd.init.ModSounds;
import me.xjqsh.lesraisinsadd.item.shield.FlashShieldItem;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import me.xjqsh.lesraisinsadd.network.message.SDefeatSpEffect;
import me.xjqsh.lesraisinsadd.network.message.SPlayerReload;
import net.minecraft.client.AbstractOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MouseSettingsScreen;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
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
        }else if(item.equals(ModItems.ACE_OF_SPADES.get())){
            event.getToolTip().add(new TranslationTextComponent("tooltip.lesraisins.ace_of_spades")
                    .withStyle(TextFormatting.ITALIC));
        }else if(item.equals(ModItems.ANGEL.get())){
            event.getToolTip().add(new TranslationTextComponent("tooltip.lesraisins.angel")
                    .withStyle(TextFormatting.ITALIC));
        }else if(item.equals(ModItems.SA58.get())){
            event.getToolTip().add(new TranslationTextComponent("tooltip.lesraisins.sa58")
                    .withStyle(TextFormatting.ITALIC));
        } else if (item.equals(ModItems.MP18.get())) {
            event.getToolTip().add(new TranslationTextComponent("tooltip.lesraisins.mp18_1")
                    .withStyle(TextFormatting.ITALIC));
            event.getToolTip().add(new TranslationTextComponent("tooltip.lesraisins.mp18_2")
                    .withStyle(TextFormatting.ITALIC));
        }
    }

    public static void handleSpEffect(SDefeatSpEffect effect){
        if (Minecraft.getInstance().level != null) {

            Minecraft.getInstance().level.addParticle(ModParticleTypes.Hun100.get(),
                    effect.getX(),effect.getY(), effect.getZ(),
                    0,0.4,0);

            Minecraft.getInstance().level.playLocalSound(effect.getX(),effect.getY(),effect.getZ(),
                    ModSounds.hun_100.get(), SoundCategory.PLAYERS,5.0f,1.0f,true);
        }
    }

    private static boolean isInGame() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            if (mc.overlay != null) {
                return false;
            } else if (mc.screen != null) {
                return false;
            } else {
                return mc.mouseHandler.isMouseGrabbed() && mc.isWindowActive();
            }
        } else {
            return false;
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event){
        if(event.phase!= TickEvent.Phase.END)return;
        if(!Config.client.autoReload.get())return;
        if (isInGame()) {
            Minecraft mc = Minecraft.getInstance();
            PlayerEntity player = mc.player;
            if (player != null) {
                ItemStack heldItem = player.getMainHandItem();
                if (heldItem.getItem() instanceof TimelessGunItem) {
                    if(GunAnimationController.fromItem(heldItem.getItem()).isAnimationRunning())return;
                    if(ReloadHandler.get().isReloading())return;
                    if (heldItem.getOrCreateTag().getInt("AmmoCount") == 0) {
                        Keys.RELOAD.setDown(true);
                        Keys.RELOAD.setDown(false);
                    }
                }

            }
        }
    }

    public static void handleReload(SPlayerReload msg){
        if (Minecraft.getInstance().level == null) return;

        for(PlayerEntity entity : Minecraft.getInstance().level.players()){
            if(entity.getUUID().equals(msg.getUUID())){

                ReloadingParticle particle = (ReloadingParticle) Minecraft.getInstance().particleEngine.createParticle(ModParticleTypes.Reloading.get(),
                        entity.getX(),entity.getY()+1.2, entity.getZ(),
                        0,0.4,0);

                if (particle != null) {
                    particle.setPlayer(entity);
                }

            }
        }

    }

//    @SubscribeEvent
//    public static void onTick(EntityJoinWorldEvent event){
//        if(!event.getWorld().isClientSide) return;
//
//        ReloadingParticle particle = (ReloadingParticle) Minecraft.getInstance().particleEngine.createParticle(ModParticleTypes.Reloading.get(),
//                event.getEntity().getX(),event.getEntity().getY()+1.2, event.getEntity().getZ(),
//                0,0.4,0);
//
//        if (particle != null) {
//            particle.setPlayer(event.getEntity());
//        }
//    }

    @SubscribeEvent
    public static void onScreenInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof MouseSettingsScreen) {
            MouseSettingsScreen screen = (MouseSettingsScreen) event.getGui();
            OptionsRowList list = screen.list;
            list.addSmall(new AbstractOption[]{
                new BooleanOption("button.lesraisins.auto_reload",unused-> Config.client.autoReload.get(),
                        (unused, newValue)->Config.client.autoReload.set(newValue))
            });
        }
    }


}
