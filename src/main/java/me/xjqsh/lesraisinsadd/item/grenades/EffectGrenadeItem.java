//package me.xjqsh.lesraisinsadd.item.grenades;
//
//import com.google.common.collect.Lists;
//import me.xjqsh.lesraisinsadd.entity.throwable.EffectCloudGrenadeEntity;
//import me.xjqsh.lesraisinsadd.entity.throwable.ThrowableItemEntity;
//import me.xjqsh.lesraisinsadd.init.ModEffects;
//import net.minecraft.client.util.ITooltipFlag;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.EffectInstance;
//import net.minecraft.potion.PotionUtils;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.world.World;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//import javax.annotation.Nullable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Function;
//
//public class EffectGrenadeItem extends ThrowableItem {
//    public static Function<EffectGrenadeItem,ItemStack> MOLOTOV = (item)->{
//        ItemStack stack = new ItemStack(item);
//
//        List<EffectInstance> effects = new ArrayList<>();
//        effects.add(new EffectInstance(ModEffects.BURNED.get(),60,0));
//
//        PotionUtils.setCustomEffects(stack,effects);
//        return stack;
//    };
//    public EffectGrenadeItem(Properties properties, float speed,Function<EffectGrenadeItem,ItemStack> createItem) {
//        super(properties,999, speed);
//        this.createItem = createItem;
//    }
//
//    public EffectGrenadeItem(Properties properties, float speed) {
//        super(properties,999, speed);
//    }
//
//    private Function<EffectGrenadeItem,ItemStack> createItem = (item)-> new ItemStack(this);
//
//    @Override
//    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items){
//        if(this.allowdedIn(group)){
//            items.add(createItem.apply(this));
//        }
//    }
//
//
//    @OnlyIn(Dist.CLIENT)
//    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
//        PotionUtils.addPotionTooltip(stack,tooltip,1.0F);
//    }
//
//    @Override
//    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft)
//    {
//        if(!worldIn.isClientSide())
//        {
//            int duration = this.getUseDuration(stack) - timeLeft;
//            if(duration >= 5)
//            {
//                if(!(entityLiving instanceof PlayerEntity) || !((PlayerEntity) entityLiving).isCreative())
//                    stack.shrink(1);
//                ThrowableItemEntity grenade = this.create(worldIn, entityLiving, stack);
//                grenade.shootFromRotation(entityLiving, entityLiving.xRot, entityLiving.yRot, 0.0F, Math.min(1.0F, duration / 20F)*this.speed, 1.5F);
//                worldIn.addFreshEntity(grenade);
//                this.onThrown(worldIn, grenade);
//            }
//        }
//    }
//
//    public ThrowableItemEntity create(World world, LivingEntity entity, ItemStack stack) {
//        EffectCloudGrenadeEntity grenade = new EffectCloudGrenadeEntity(world,entity,null);
//
////        grenade.addEffects(getEffects(stack));
////        grenade.setColor(PotionUtils.getColor(getEffects(stack)));
////
////        grenade.setBrokeOnGround(true);
////        grenade.setAreaHeight(areaHeight);
////        grenade.setMaxRadius(maxRadius);
////        grenade.setMinRadius(minRadius);
////        grenade.setAreaDuration(areaDuration);
////        grenade.setExtinguishBySmoke(extinguishBySmoke);
////        grenade.setSpreadPreTick(spreadPreTick);
////        grenade.setParticle(particle.get());
//
//        return grenade;
//    }
//    public List<EffectInstance> getEffects(ItemStack stack){
//        List<EffectInstance> effects = Lists.newArrayList();
//        if(stack.getTag()!=null){
//            PotionUtils.getCustomEffects(stack.getTag(),effects);
//        }
//        return effects;
//    }
//
//
//    public boolean canCook() {
//        return false;
//    }
//
//    @Override
//    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
//        return stack;
//    }
//
//}
