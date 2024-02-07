package me.xjqsh.lesraisinsadd.util;

import com.tac.guns.client.audio.GunShotSound;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoundUtil {
    public static void playSound(ResourceLocation rl, float x, float y, float z) {
        Minecraft.getInstance().getSoundManager()
                .play(new GunShotSound(rl, SoundCategory.PLAYERS,
                        x,y,z, 2, 1, false));
    }
}
