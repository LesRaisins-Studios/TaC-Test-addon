package me.xjqsh.lesraisinsadd.item.shield;

import com.google.common.collect.Multimap;
import com.tac.guns.Config;
import com.tac.guns.init.ModEffects;
import me.xjqsh.lesraisinsadd.util.EntityUtil;
import me.xjqsh.lesraisinsadd.util.StunCheck;
import net.minecraft.command.arguments.NBTTagArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class FlashShieldItem extends RiotShieldItem {

    public FlashShieldItem(Properties properties, Multimap<Attribute, AttributeModifier> modifiers) {
        super(properties, modifiers);
    }

    public FlashShieldItem(Properties properties, float atk, float atk_speed, float movement_speed) {
        super(properties,atk,atk_speed,movement_speed);
    }

    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list) {
        if (this.allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("flash",3);
            stack.setTag(nbt);
            list.add(stack);
        }
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(itemstack.hasTag()){
            if (itemstack.getTag() != null && itemstack.getTag().contains("flash", Constants.NBT.TAG_INT)) {
                int ammo = itemstack.getTag().getInt("flash");
                if(ammo<1){
                    return ActionResult.fail(itemstack);
                }else {
                    player.startUsingItem(hand);
                    return ActionResult.pass(itemstack);
                }
            }
        }
        return ActionResult.fail(itemstack);
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
    public void onUseTick(World world, LivingEntity entity, ItemStack itemstack, int tick) {
        int useTick = this.getUseDuration(itemstack) - tick;
        if(useTick==20){
            if (itemstack.getTag() != null && itemstack.getTag().contains("flash", Constants.NBT.TAG_INT)) {
                int ammo = itemstack.getTag().getInt("flash");
                if(ammo>0){
//                    entity.sendMessage(new StringTextComponent("remain: " + ammo), Util.NIL_UUID);
                    itemstack.getTag().putInt("flash",ammo-1);
                    if(entity instanceof PlayerEntity){
                        CooldownTracker tracker = ((PlayerEntity) entity).getCooldowns();
                        tracker.addCooldown(this, 100);
                    }
                }else {
                    entity.stopUsingItem();
                    return;
                }
            }else {
                entity.stopUsingItem();
                return;
            }

            if(!world.isClientSide()) {
                // Calculate bounds of area where potentially effected players may be
                double diameter = Math.max(Config.COMMON.stunGrenades.deafen.criteria.radius.get(), Config.COMMON.stunGrenades.blind.criteria.radius.get()) * 2 + 1;
                for(LivingEntity target : EntityUtil.getEntitiesInRadius(world, LivingEntity.class, entity.position(), diameter)) {
                    if(entity.ignoreExplosion())
                        continue;

                    // Apply effects as determined by their criteria
                    if(StunCheck.calculateAndApplyEffect(ModEffects.DEAFENED.get(),
                            Config.COMMON.stunGrenades.deafen.criteria, entity, target)
                            && Config.COMMON.stunGrenades.deafen.panicMobs.get()) {
                        entity.setLastHurtByMob(entity);
                    }

                    if(StunCheck.calculateAndApplyEffect(ModEffects.BLINDED.get(),
                            Config.COMMON.stunGrenades.blind.criteria, entity, target)
                            && Config.COMMON.stunGrenades.blind.blindMobs.get()
                            && entity instanceof MobEntity) {
                        ((MobEntity) entity).setTarget(null);
                    }
                }
                entity.addEffect(new EffectInstance(ModEffects.BLINDED.get(),10,0,false,false));
            }else{
                entity.playSound(SoundEvents.ITEM_BREAK,1.0f,1.0f);
            }
        }else if(useTick == 30){
            entity.stopUsingItem();
        }
    }

}
