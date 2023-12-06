package me.xjqsh.lesraisinsadd.util;

import com.tac.guns.Config;
import com.tac.guns.common.Gun;
import com.tac.guns.common.network.ServerPlayHandler;
import com.tac.guns.item.GunItem;
import com.tac.guns.util.GunEnchantmentHelper;
import com.tac.guns.util.GunModifierHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ToolTipUtil {
    public static void showBasicInfo(ItemStack stack, List<ITextComponent> tooltip) {
        Gun modifiedGun = ((GunItem)stack.getItem()).getModifiedGun(stack);
        Item ammo = ForgeRegistries.ITEMS.getValue(modifiedGun.getProjectile().getItem());
        CompoundNBT tagCompound = stack.getTag();

        if (ammo != null) {
            tooltip.add((new TranslationTextComponent("info.tac.ammo_type", (new TranslationTextComponent(ammo.getDescriptionId())).withStyle(TextFormatting.GOLD))).withStyle(TextFormatting.DARK_GRAY));
        }

        String additionalDamageText = "";

        float additionalDamage = GunModifierHelper.getAdditionalDamage(stack);
        if (additionalDamage > 0.0F) {
            additionalDamageText = TextFormatting.GREEN + " +" + ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(additionalDamage);
        } else if (additionalDamage < 0.0F) {
            additionalDamageText = TextFormatting.RED + " " + ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(additionalDamage);
        }

        additionalDamage = modifiedGun.getProjectile().getDamage();
        additionalDamage = GunModifierHelper.getModifiedProjectileDamage(stack, additionalDamage);
        additionalDamage = GunEnchantmentHelper.getAcceleratorDamage(stack, additionalDamage);
        tooltip.add((new TranslationTextComponent("info.tac.damage", TextFormatting.GOLD + ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(additionalDamage) + additionalDamageText)).withStyle(TextFormatting.DARK_GRAY));
        if (tagCompound != null) {
            if (tagCompound.getBoolean("IgnoreAmmo")) {
                tooltip.add((new TranslationTextComponent("info.tac.ignore_ammo")).withStyle(TextFormatting.AQUA));
            } else {
                int ammoCount = tagCompound.getInt("AmmoCount");
                tooltip.add((new TranslationTextComponent("info.tac.ammo", TextFormatting.GOLD.toString() + ammoCount + "/" + GunModifierHelper.getAmmoCapacity(stack, modifiedGun))).withStyle(TextFormatting.DARK_GRAY));
            }
        }
    }

    public static void showAttributes(ItemStack stack, List<ITextComponent> tooltip) {
        Gun modifiedGun = ((GunItem)stack.getItem()).getModifiedGun(stack);
        CompoundNBT tagCompound = stack.getTag();
        GunItem gun = (GunItem) stack.getItem();
        if (tagCompound != null) {
            double armorPen = gun.getGun().getProjectile().getGunArmorIgnore() >= 0.0F ? Math.min(Config.COMMON.gameplay.percentDamageIgnoresStandardArmor.get() * (double)gun.getGun().getProjectile().getGunArmorIgnore() * 100.0, 100.0) : 0.0;
            tooltip.add((new TranslationTextComponent("info.tac.armorPen", (new TranslationTextComponent(String.format("%.1f", armorPen) + "%")).withStyle(TextFormatting.RED))).withStyle(TextFormatting.DARK_AQUA));
            int headDamgeModifier = Config.COMMON.gameplay.headShotDamageMultiplier.get() * (double)gun.getGun().getProjectile().getGunHeadDamage() >= 0.0 ? (int)(Config.COMMON.gameplay.headShotDamageMultiplier.get() * (double)gun.getGun().getProjectile().getGunHeadDamage() * 100.0) : 0;
            tooltip.add((new TranslationTextComponent("info.tac.headDamageModifier", (new TranslationTextComponent(String.format("%d", headDamgeModifier) + "%")).withStyle(TextFormatting.RED))).withStyle(TextFormatting.DARK_AQUA));
            float speed = ServerPlayHandler.calceldGunWeightSpeed(gun.getGun(), stack);
            speed = Math.max(Math.min(speed, 0.1F), 0.075F);
            if (speed > 0.094F) {
                tooltip.add((new TranslationTextComponent("info.tac.lightWeightGun", (new TranslationTextComponent(-((int)((0.1 - (double)speed) * 1000.0)) + "%")).withStyle(TextFormatting.RED))).withStyle(TextFormatting.DARK_AQUA));
            } else if ((double)speed < 0.095 && (double)speed > 0.0875) {
                tooltip.add((new TranslationTextComponent("info.tac.standardWeightGun", (new TranslationTextComponent(-((int)((0.1 - (double)speed) * 1000.0)) + "%")).withStyle(TextFormatting.RED))).withStyle(TextFormatting.DARK_GREEN));
            } else {
                tooltip.add((new TranslationTextComponent("info.tac.heavyWeightGun", (new TranslationTextComponent(-((int)((0.1 - (double)speed) * 1000.0)) + "%")).withStyle(TextFormatting.RED))).withStyle(TextFormatting.DARK_RED));
            }

            float percentageToNextLevel = tagCompound.getFloat("levelDmg") * 100.0F / (modifiedGun.getGeneral().getLevelReq() * (float) tagCompound.getInt("level") * 3.0F);
            tooltip.add((new TranslationTextComponent("info.tac.current_level")).append(new TranslationTextComponent(" " + tagCompound.getInt("level") + " : " + String.format("%.2f", percentageToNextLevel) + "%")).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.BOLD));
        }
    }
}
