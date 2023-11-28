package me.xjqsh.lesraisinsadd.item.interfaces;

import com.tac.guns.event.GunReloadEvent;
import net.minecraft.item.ItemStack;

/** called during GunReload event
 */
public interface IReloadAction {
    void onGunReload(GunReloadEvent.Post event, ItemStack stack);
}
