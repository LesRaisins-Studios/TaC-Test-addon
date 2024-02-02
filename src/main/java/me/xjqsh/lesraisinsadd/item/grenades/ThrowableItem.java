package me.xjqsh.lesraisinsadd.item.grenades;


import me.xjqsh.lesraisinsadd.common.data.IMetaHolder;
import me.xjqsh.lesraisinsadd.entity.throwable.ThrowableItemEntity;
import me.xjqsh.lesraisinsadd.common.data.grenades.ThrowableMeta;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public abstract class ThrowableItem<T extends ThrowableMeta> extends Item implements IMetaHolder<T> {
    @FunctionalInterface
    public interface ThrowableEntityFactory<T extends ThrowableMeta>{
        ThrowableItemEntity<T> create(World world, LivingEntity entity, int useTick, ThrowableItem<T> meta);
    }
    private final ThrowableEntityFactory<T> factory;
    private T meta;
    public ThrowableItem(Properties properties, ThrowableEntityFactory<T> factory) {
        super(properties);
        this.factory = factory;
    }
    public void setMeta(T meta) {
        this.meta = meta;
    }
    public T getMeta() {
        return meta;
    }
    @Override
    public String getPath() {
        return "throwables";
    }
    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return this.meta.getMaxCookTime();
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {

    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        playerIn.startUsingItem(handIn);
        return ActionResult.consume(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(!worldIn.isClientSide() && this.canCook()) {
            ItemStack copy = stack.copy();
            if(!(entityLiving instanceof PlayerEntity) || !((PlayerEntity) entityLiving).isCreative())
                stack.shrink(1);

            ThrowableItemEntity<T> grenade = this.create(worldIn, entityLiving,  0,this);
            grenade.setItem(copy);

            grenade.onDeath();
            grenade.remove();
        }
        return stack;
    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if(!worldIn.isClientSide()) {
            int duration = this.getUseDuration(stack) - timeLeft;
            if(duration >= 5) {
                ItemStack copy = stack.copy();
                if(!(entityLiving instanceof PlayerEntity) || !((PlayerEntity) entityLiving).isCreative())
                    stack.shrink(1);

                ThrowableItemEntity<T> grenade = this.create(worldIn, entityLiving,  duration,this);
                grenade.setItem(copy);

                grenade.shootFromRotation(entityLiving, entityLiving.xRot, entityLiving.yRot,
                        0.0F, Math.min(1.0F, duration / 20F) * this.meta.getSpeed(), 1.5F);

                worldIn.addFreshEntity(grenade);

                this.onThrown(worldIn, grenade);
            }
        }
    }

    public ThrowableItemEntity<T> create(World world, LivingEntity entity, int useTick, ThrowableItem<T> meta){
        return this.factory.create(world, entity, useTick, meta);
    }

    public boolean canCook() {
        return this.meta.canCook();
    }

    protected void onThrown(World world, ThrowableItemEntity<T> entity) {}

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return super.initCapabilities(stack, nbt);
    }
}
