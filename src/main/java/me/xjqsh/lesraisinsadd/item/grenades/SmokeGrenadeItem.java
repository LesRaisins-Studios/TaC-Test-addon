package me.xjqsh.lesraisinsadd.item.grenades;


import me.xjqsh.lesraisinsadd.entity.throwable.ThrowableItemEntity;
import me.xjqsh.lesraisinsadd.entity.throwable.ThrowableSmokeGrenadeEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class SmokeGrenadeItem extends GrenadeItem
{
    public SmokeGrenadeItem(Properties properties, int maxCookTime, float power, float speed)
    {
        super(properties, maxCookTime, speed);
    }

    public ThrowableItemEntity create(World world, LivingEntity entity, int timeLeft)
    {
        return new ThrowableSmokeGrenadeEntity(world, entity);
    }

    public boolean canCook()
    {
        return false;
    }

    protected void onThrown(World world, ThrowableItemEntity entity) {
    }
}
