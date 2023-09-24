package me.xjqsh.lesraisinsadd.client.render;

import me.xjqsh.lesraisinsadd.common.item.RiotShieldItem;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class PlayerRenderHandle {
    @SubscribeEvent
    public static void onPlayerRender(RenderPlayerEvent.Pre event){
        if(event.getPlayer().getMainHandItem().getItem() instanceof RiotShieldItem){
            PlayerRenderer renderer = event.getRenderer();
            PlayerModel<AbstractClientPlayerEntity> playermodel = renderer.getModel();
            playermodel.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
        }
    }

}
