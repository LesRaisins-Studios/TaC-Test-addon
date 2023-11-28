package me.xjqsh.lesraisinsadd.item.interfaces;

import com.tac.guns.entity.DamageSourceProjectile;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/** called when a player kill an entity use this gun
 */
public interface IDefeatAction {
    void onGunDefeat(LivingDeathEvent event, ItemStack stack, DamageSourceProjectile source);
}
