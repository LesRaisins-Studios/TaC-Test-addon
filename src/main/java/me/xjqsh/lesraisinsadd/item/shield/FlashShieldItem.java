package me.xjqsh.lesraisinsadd.item.shield;

import com.google.common.collect.Multimap;
import com.tac.guns.Config;
import com.tac.guns.init.ModEffects;
import me.xjqsh.lesraisinsadd.init.ModSounds;
import me.xjqsh.lesraisinsadd.item.interfaces.IAmmoable;
import me.xjqsh.lesraisinsadd.util.EntityUtil;
import me.xjqsh.lesraisinsadd.util.SightTraceUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.KeybindTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class FlashShieldItem extends RiotShieldItem implements IAmmoable {
    public FlashShieldItem(Properties properties, Multimap<Attribute, AttributeModifier> modifiers) {
        super(properties, modifiers);
    }
    public FlashShieldItem(Properties properties, float atk, float atk_speed, float movement_speed) {
        super(properties,atk,atk_speed,movement_speed);
    }

    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list) {
        if (this.allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            setAmmo(stack,3);
            list.add(stack);
        }
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(getAmmo(itemstack)>0 && player.getMainHandItem().equals(itemstack)){
            player.startUsingItem(hand);
            return ActionResult.pass(itemstack);
        }else {
            return ActionResult.fail(itemstack);
        }
    }
    @Override
    public Item getAmmoItem(){
        return Items.REDSTONE;
    }
    @Override
    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.NONE;
    }
    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }
    @Override
    public void onUseTick(World world, LivingEntity caster, ItemStack itemstack, int tick) {
        int useTick = this.getUseDuration(itemstack) - tick;
        if(useTick==20){
            if(caster instanceof PlayerEntity && (((PlayerEntity) caster).isCreative() || consume(itemstack,1))){
                CooldownTracker tracker = ((PlayerEntity) caster).getCooldowns();
                tracker.addCooldown(this, 100);
            }else{
                caster.stopUsingItem();
                return;
            }

            if(!world.isClientSide()) {
                // Calculate bounds of area where potentially effected players may be
                double diameter = Math.max(Config.COMMON.stunGrenades.deafen.criteria.radius.get(), Config.COMMON.stunGrenades.blind.criteria.radius.get()) * 2 + 1;
                for(LivingEntity target : EntityUtil.getEntitiesInRadius(world, LivingEntity.class, caster.position(), diameter)) {
                    if(caster.ignoreExplosion())
                        continue;

                    // Apply effects as determined by their criteria
                    if(SightTraceUtil.calculateAndApplyEffect(ModEffects.DEAFENED.get(),
                            Config.COMMON.stunGrenades.deafen.criteria, caster, target)
                            && Config.COMMON.stunGrenades.deafen.panicMobs.get()) {
                        caster.setLastHurtByMob(caster);
                    }

                    if(SightTraceUtil.calculateAndApplyEffect(ModEffects.BLINDED.get(),
                            Config.COMMON.stunGrenades.blind.criteria, caster, target)
                            && Config.COMMON.stunGrenades.blind.blindMobs.get()
                            && target instanceof MobEntity) {
                        ((MobEntity) target).setTarget(null);
                    }
                }
                // blink the caster's screen
                caster.addEffect(new EffectInstance(ModEffects.BLINDED.get(),10,0,false,false));
            }else{
                caster.playSound(ModSounds.flash_shield_fire.get(),1.0f,1.0f);
            }
        }else if(useTick == 30){
            caster.stopUsingItem();
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        KeybindTextComponent key = new KeybindTextComponent(Minecraft.getInstance().options.keyUse.getName());
        p_77624_3_.add(new TranslationTextComponent("tooltip.lesraisins.flash_shield",key)
                .withStyle(TextFormatting.YELLOW));
    }
}
