package me.xjqsh.lesraisinsadd.item.interfaces;

import com.tac.guns.event.GunFireEvent;
import net.minecraft.item.ItemStack;

public interface IFireAction {
    void onGunFire(GunFireEvent.Post event, ItemStack stack);
}
