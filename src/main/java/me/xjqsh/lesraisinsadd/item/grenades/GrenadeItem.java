package me.xjqsh.lesraisinsadd.item.grenades;

import com.tac.guns.init.ModSounds;
import me.xjqsh.lesraisinsadd.entity.throwable.ThrowableItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class GrenadeItem extends Item {
    protected int maxCookTime;
    protected float speed;

    public GrenadeItem(Properties properties, int maxCookTime, float speed)
    {
        super(properties);
        this.maxCookTime = maxCookTime;
        this.speed = speed;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return this.maxCookTime;
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count)
    {
        if(!this.canCook()) return;

        int duration = this.getUseDuration(stack) - count;
        if(duration == 5)
            player.level.playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.ITEM_GRENADE_PIN.get(), SoundCategory.PLAYERS, 1.0F, 1.0F, false);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        playerIn.startUsingItem(handIn);
        return ActionResult.consume(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving)
    {
        if(this.canCook() && !worldIn.isClientSide())
        {
            if(!(entityLiving instanceof PlayerEntity) || !((PlayerEntity) entityLiving).isCreative())
                stack.shrink(1);
            ThrowableItemEntity grenade = this.create(worldIn, entityLiving, 0);
            grenade.onDeath();
        }
        return stack;
    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if(!worldIn.isClientSide()) {
            int duration = this.getUseDuration(stack) - timeLeft;
            if(duration >= 5) {
                if(!(entityLiving instanceof PlayerEntity) || !((PlayerEntity) entityLiving).isCreative())
                    stack.shrink(1);

                ThrowableItemEntity grenade = this.create(worldIn, entityLiving, this.maxCookTime - duration);
                grenade.shootFromRotation(entityLiving, entityLiving.xRot, entityLiving.yRot,
                        0.0F, Math.min(1.0F, duration / 20F)*this.speed, 1.5F);

                worldIn.addFreshEntity(grenade);

                this.onThrown(worldIn, grenade);
            }
        }
    }

    public ThrowableItemEntity create(World world, LivingEntity entity, int timeLeft) {
        return null;
    }


    public boolean canCook() {
        return true;
    }

    protected void onThrown(World world, ThrowableItemEntity entity) {
    }
}
