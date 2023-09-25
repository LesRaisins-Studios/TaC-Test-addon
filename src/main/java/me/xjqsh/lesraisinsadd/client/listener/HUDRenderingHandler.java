package me.xjqsh.lesraisinsadd.client.listener;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.common.item.RiotShieldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HUDRenderingHandler extends AbstractGui {
    public static final ResourceLocation shield_icon = new ResourceLocation(Reference.MOD_ID, "textures/gui/shield.png");

    private static int x = 0;

    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null) {
            return;
        }

        ClientPlayerEntity player = Minecraft.getInstance().player;
        ItemStack heldItem = player.getMainHandItem();

        MatrixStack stack = event.getMatrixStack();

        if(heldItem.getItem() instanceof RiotShieldItem){
            int anchorPointX = (int) (event.getWindow().getGuiScaledWidth() / 2f);
            int anchorPointY = (int) (event.getWindow().getGuiScaledHeight() * 0.75f);
            double p = 1.0-heldItem.getItem().getDurabilityForDisplay(heldItem);
            if(p<0.3) {
                x++;
                if (x > 40) x = 0;
            }else{
                x=10;
            }

            stack.pushPose();
            {
                Minecraft.getInstance().getTextureManager().bind(shield_icon);
                RenderSystem.color4f(1.0f, (float) p + 0.2f,(float)p + 0.2f,x/10f);
                blit(stack, anchorPointX -16, anchorPointY -16,0,0,32,32,32,32);
                RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);

                int color = (p>0.5 ? new Color(0,255,0,0).getRGB() :
                        new Color(255,0,0,0).getRGB());

                renderBar(stack,anchorPointX-24,anchorPointY+24,6,48,color,p);
            }
            stack.popPose();

        }
    }

    private static void renderBar(MatrixStack stack, int x,int y,int height,int width,int color,double percent){
        //bg
        fill(stack,x-1,y-1,x+width+1,y+height+1,new Color(0, 0, 0,200).getRGB());
        //bar
        fill(stack,x,y,(int)(x+width*percent),y+height,color+(90<<24));
    }
}
