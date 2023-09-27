package me.xjqsh.lesraisinsadd.client.listener;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.tac.guns.Config;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.item.shield.FlashShieldItem;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.Color;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HUDRenderingHandler extends AbstractGui {
    public static final ResourceLocation shield_icon = new ResourceLocation(Reference.MOD_ID, "textures/gui/shield.png");
    private static int x = 0;

    private static final ResourceLocation[] RELOAD_ICONS = new ResourceLocation[]
            {
                    new ResourceLocation(com.tac.guns.Reference.MOD_ID, "textures/gui/reloadbar.png")
            };

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
            int x = (int) (event.getWindow().getGuiScaledWidth() / 2f);
            int y = (int) (event.getWindow().getGuiScaledHeight() * 0.75f);
            double p = 1.0-heldItem.getItem().getDurabilityForDisplay(heldItem);
            if(p<0.3) {
                HUDRenderingHandler.x++;
                if (HUDRenderingHandler.x > 40) HUDRenderingHandler.x = 0;
            }else{
                HUDRenderingHandler.x =10;
            }

            stack.pushPose();
            {
                Minecraft.getInstance().getTextureManager().bind(shield_icon);
                RenderSystem.color4f(1.0f, (float) p + 0.2f,(float)p + 0.2f, HUDRenderingHandler.x /10f);
                blit(stack, x -16, y -16,0,0,32,32,32,32);
                RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);

                int color = (p>0.5 ? new Color(0,255,0,0).getRGB() :
                        new Color(255,0,0,0).getRGB());

                renderBar(stack,x-24,y+24,6,48,color,p);
            }
            stack.popPose();

            if(heldItem.getItem() instanceof FlashShieldItem){
                float anchorPointX = event.getWindow().getGuiScaledWidth() / 12F * 11F;
                float anchorPointY = event.getWindow().getGuiScaledHeight() / 10F * 9F;

                float configScaleWeaponCounter = Config.CLIENT.weaponGUI.weaponAmmoCounter.weaponAmmoCounterSize.get().floatValue();
                float configScaleWeaponReloadBar = Config.CLIENT.weaponGUI.weaponReloadTimer.weaponReloadTimerSize.get().floatValue();

                float counterSize = 1.8F * configScaleWeaponCounter;
                float ReloadBarSize = 32.0F * configScaleWeaponReloadBar;

                // Text rendering
                stack.pushPose();
                {
                    stack.translate(
                            (anchorPointX - (counterSize * 32) / 2) + (-Config.CLIENT.weaponGUI.weaponAmmoCounter.x.get().floatValue()),
                            (anchorPointY - (counterSize * 32) / 4) + (-Config.CLIENT.weaponGUI.weaponAmmoCounter.y.get().floatValue()),
                            0
                    );
                    if (heldItem.getTag() != null) {
                        IFormattableTextComponent currentAmmo;
                        IFormattableTextComponent reserveAmmo;

                        int ammo = ((FlashShieldItem) heldItem.getItem()).getAmmo(heldItem);
                        TextFormatting ammoColor = (ammo==0 ? TextFormatting.RED : TextFormatting.WHITE);
                        TextFormatting ammoBg = (ammo==0 ? TextFormatting.RED : TextFormatting.GRAY);
                        TextFormatting reserveColor = (ammo==0 ? TextFormatting.RED : TextFormatting.GRAY);

                        currentAmmo = byPaddingZeros(ammo).withStyle(ammoBg)
                                .append(new StringTextComponent(""+ammo).withStyle(ammoColor));
                        reserveAmmo = byPaddingZeros(3)
                                .append(new StringTextComponent(""+3).withStyle(TextFormatting.GRAY));

                        stack.scale(counterSize, counterSize, counterSize);
                        stack.pushPose();
                        {
                            stack.translate(-21.15, 0, 0);
                            drawString(stack, Minecraft.getInstance().font, currentAmmo, 0, 0, 0xffffff); // Gun ammo
                        }
                        stack.popPose();

                        stack.pushPose();
                        {
                            stack.scale(0.7f, 0.7f, 0.7f);
                            stack.translate(3.7, 3.4, 0);
                            drawString(stack, Minecraft.getInstance().font, reserveAmmo, 0, 0, 0xffffff); // Reserve ammo
                        }
                        stack.popPose();
                    }
                }
                stack.popPose();

                stack.pushPose();
                {
                    RenderSystem.enableAlphaTest();
                    BufferBuilder buffer = Tessellator.getInstance().getBuilder();
                    buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

                    stack.translate(anchorPointX - (ReloadBarSize * 4.35) / 4F, anchorPointY + (ReloadBarSize * 1.625F) / 5F * 3F, 0);//stack.translate(anchorPointX - (fireModeSize*6) / 4F, anchorPointY - (fireModeSize*1F) / 5F * 3F, 0); // *68for21F
                    stack.translate(-ReloadBarSize, -ReloadBarSize, 0);

                    stack.translate(-16.25 - 7.3, 0.15 + 1.6, 0);

                    stack.scale(3.05F, 0.028F, 0); // *21F
                    Minecraft.getInstance().getTextureManager().bind(RELOAD_ICONS[0]); // Future options to render bar types

                    Matrix4f matrix = stack.last().pose();
                    drawBar(ReloadBarSize, buffer, matrix);

                    stack.translate(19.25, (1.5 + (-63.4)) * 10, 0);

                    stack.scale(0.0095F, 20.028F, 0); // *21F

                    drawBar(ReloadBarSize, buffer, matrix);

                    buffer.end();
                    WorldVertexBufferUploader.end(buffer);
                }
                stack.popPose();
            }
        }
    }

    private static void drawBar(float reloadBarSize, BufferBuilder buffer, Matrix4f matrix) {
        buffer.vertex(matrix, 0, reloadBarSize, 0).uv(0, 1).color(1.0F, 1.0F, 1.0F, 0.99F).endVertex();
        buffer.vertex(matrix, reloadBarSize, reloadBarSize, 0).uv(1, 1).color(1.0F, 1.0F, 1.0F, 0.99F).endVertex();
        buffer.vertex(matrix, reloadBarSize, 0, 0).uv(1, 0).color(1.0F, 1.0F, 1.0F, 0.99F).endVertex();
        buffer.vertex(matrix, 0, 0, 0).uv(0, 0).color(1.0F, 1.0F, 1.0F, 0.99F).endVertex();
    }

    private static void renderBar(MatrixStack stack, int x,int y,int height,int width,int color,double percent){
        //bg
        fill(stack,x-1,y-1,x+width+1,y+height+1,new Color(0, 0, 0,200).getRGB());
        //bar
        fill(stack,x,y,(int)(x+width*percent),y+height,color+(90<<24));
    }

    private static IFormattableTextComponent byPaddingZeros(int number) {
        String text = String.format("%0" + (byPaddingZerosCount(number) + 1) + "d", 1);
        text = text.substring(0, text.length() - 1);
        return new TranslationTextComponent(text).withStyle(TextFormatting.GRAY);
    }

    private static int byPaddingZerosCount(int length) {
        if (length < 10)
            return 2;
        if (length < 100)
            return 1;
        if (length < 1000)
            return 0;
        return 0;
    }
}
