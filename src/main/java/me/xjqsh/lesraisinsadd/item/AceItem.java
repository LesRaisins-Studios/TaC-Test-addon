package me.xjqsh.lesraisinsadd.item;

import com.tac.guns.Config;
import com.tac.guns.GunMod;
import com.tac.guns.client.Keys;
import com.tac.guns.common.Gun;
import com.tac.guns.common.network.ServerPlayHandler;
import com.tac.guns.entity.DamageSourceProjectile;
import com.tac.guns.event.GunFireEvent;
import com.tac.guns.event.GunReloadEvent;
import com.tac.guns.interfaces.IGunModifier;
import com.tac.guns.item.GunItem;
import com.tac.guns.item.TransitionalTypes.TimelessGunItem;
import com.tac.guns.util.GunEnchantmentHelper;
import com.tac.guns.util.GunModifierHelper;
import com.tac.guns.util.Process;
import me.xjqsh.lesraisinsadd.client.LRKeys;
import me.xjqsh.lesraisinsadd.item.interfaces.IDefeatAction;
import me.xjqsh.lesraisinsadd.item.interfaces.IFireAction;
import me.xjqsh.lesraisinsadd.item.interfaces.IReloadAction;
import me.xjqsh.lesraisinsadd.util.EntityUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public class AceItem extends TimelessGunItem implements IReloadAction, IDefeatAction, IFireAction {
    public static final String ENHANCED_KEY = "EnhancedAmmo";
    public AceItem(Process<Properties> properties, IGunModifier... modifiers) {
        super((properties1) -> properties.process((new Properties()).stacksTo(1).tab(GunMod.GROUP)),
                modifiers);
    }

    public static void setEnhancedAmmo(ItemStack stack,int i){
        stack.getOrCreateTag().putInt(ENHANCED_KEY,i);
    }

    public static int getEnhancedAmmo(ItemStack stack){
        return stack.getOrCreateTag().getInt(ENHANCED_KEY);
    }

    public static boolean isEnhanced(ItemStack stack){
        return stack.getOrCreateTag().getInt(ENHANCED_KEY) > 0 && Gun.hasAmmo(stack);
    }

    public static void setDefeat(ItemStack stack, boolean status){
        stack.getOrCreateTag().putBoolean("Defeat",status);
    }

    public static boolean hasDefeat(ItemStack stack){
        return stack.getOrCreateTag().getBoolean("Defeat");
    }

    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
        Gun modifiedGun = this.getModifiedGun(stack);
        Item ammo = ForgeRegistries.ITEMS.getValue(modifiedGun.getProjectile().getItem());
        if (ammo != null) {
            tooltip.add((new TranslationTextComponent("info.tac.ammo_type", (new TranslationTextComponent(ammo.getDescriptionId())).withStyle(TextFormatting.GOLD))).withStyle(TextFormatting.DARK_GRAY));
        }

        String additionalDamageText = "";
        CompoundNBT tagCompound = stack.getTag();
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

        boolean isShift = Keys.MORE_INFO_HOLD.isDown();
        boolean isCtrl = LRKeys.MORE_EFFECT_HOLD.isDown();
        if (!isShift && !isCtrl) {
            tooltip.add(new TranslationTextComponent("sp.lesraisins.memento_mori")
                    .withStyle(TextFormatting.UNDERLINE)
                    .withStyle(TextFormatting.BOLD)
                    .withStyle(isEnhanced(stack) ? TextFormatting.AQUA : TextFormatting.WHITE));

            tooltip.add(new TranslationTextComponent("sp.lesraisins.firefly")
                    .withStyle(TextFormatting.UNDERLINE)
                    .withStyle(TextFormatting.BOLD)
                    .withStyle(TextFormatting.WHITE));

            String text = (new KeybindTextComponent("key.tac.more_info_hold")).getString().toUpperCase(Locale.ENGLISH);
            tooltip.add((new TranslationTextComponent("info.tac.more_info_gunitem", text)).withStyle(TextFormatting.YELLOW));
            String text2 = (new KeybindTextComponent("key.lesraisinsadd.more_effect_hold")).getString().toUpperCase(Locale.ENGLISH);
            tooltip.add((new TranslationTextComponent("info.lesraisins.more_effect_gunitem", text2)).withStyle(TextFormatting.YELLOW));
        }

        if (isShift) {
            GunItem gun = (GunItem)stack.getItem();
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

                float percentageToNextLevel = tagCompound.getFloat("levelDmg") * 100.0F / (modifiedGun.getGeneral().getLevelReq() * (float)tagCompound.getInt("level") * 3.0F);
                tooltip.add((new TranslationTextComponent("info.tac.current_level")).append(new TranslationTextComponent(" " + tagCompound.getInt("level") + " : " + String.format("%.2f", percentageToNextLevel) + "%")).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.BOLD));
            }


        }

        if (isCtrl) {
            tooltip.add(new TranslationTextComponent("sp.lesraisins.memento_mori")
                    .withStyle(TextFormatting.UNDERLINE)
                    .withStyle(TextFormatting.BOLD)
                    .withStyle(isEnhanced(stack) ? TextFormatting.AQUA : TextFormatting.WHITE));
            tooltip.add(new TranslationTextComponent("sp.lesraisins.perk.solid").withStyle(TextFormatting.GRAY));
            tooltip.add(new TranslationTextComponent("sp.lesraisins.memento_mori.desc"));
            ITextComponent component = new StringTextComponent(""+getEnhancedAmmo(stack)).withStyle(TextFormatting.AQUA);
            tooltip.add(new TranslationTextComponent("sp.lesraisins.memento_mori.desc_2",component));
            tooltip.add(new TranslationTextComponent("sp.lesraisins.firefly")
                    .withStyle(TextFormatting.UNDERLINE)
                    .withStyle(TextFormatting.BOLD)
                    .withStyle(isEnhanced(stack) ? TextFormatting.AQUA : TextFormatting.WHITE));
            tooltip.add(new TranslationTextComponent("sp.lesraisins.firefly.desc"));
        }
    }

    @Override
    public void onGunReload(GunReloadEvent.Post event, ItemStack stack) {
        if(stack.getItem().equals(this) && hasDefeat(stack)){
            setDefeat(stack,false);
            setEnhancedAmmo(stack, 6);
        }else {
            setEnhancedAmmo(stack, 0);
        }
    }

    @Override
    public void onGunDefeat(LivingDeathEvent event, ItemStack stack, DamageSourceProjectile source) {
        if(stack.getItem() instanceof AceItem){
            setDefeat(stack,true);
            PlayerEntity player = (PlayerEntity) source.getEntity();
            Entity suffer = event.getEntity();

            if(((DamageSourceProjectile) event.getSource()).isHeadShot()){
                event.getEntity().level.explode(player,
                        suffer.getX(),suffer.getY()+1,suffer.getZ(),
                        2.0f, Explosion.Mode.NONE);

                EntityUtil.getEntitiesInRadius(event.getEntity().level, Entity.class,
                        event.getEntity().position(),3).forEach(entity -> entity.setSecondsOnFire(5));
            }
        }
    }

    @Override
    public void onGunFire(GunFireEvent.Post event, ItemStack stack) {
        if(stack.getItem() instanceof AceItem){
            if(isEnhanced(stack)){
                setEnhancedAmmo(stack,getEnhancedAmmo(stack)-1);
            }
        }
    }
}
