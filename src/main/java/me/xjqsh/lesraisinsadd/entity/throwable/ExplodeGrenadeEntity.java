package me.xjqsh.lesraisinsadd.entity.throwable;


import com.tac.guns.entity.DamageSourceExplosion;
import com.tac.guns.entity.IExplosionProvider;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.common.data.grenades.ExplodeGrenadeMeta;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import me.xjqsh.lesraisinsadd.util.ExplodeUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class ExplodeGrenadeEntity extends ThrowableItemEntity<ExplodeGrenadeMeta> implements IExplosionProvider {
    public ExplodeGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    public ExplodeGrenadeEntity(World world, LivingEntity player, ThrowableItem<ExplodeGrenadeMeta> meta, int useTick) {
        super(ModEntities.THROWABLE_EXPLODE_GRENADE.get(), world, player, meta);
        this.maxLife = meta.getMeta().getMaxLife() - useTick;
    }

    @Override
    public void onDeath() {
        if(!this.level.isClientSide()){
            ExplodeUtil.createExplosion(this,this.getMeta().getPower(),5,null);
        }
    }

    @Override
    public DamageSourceExplosion createDamageSource() {
        return new DamageSourceExplosion(this,this.getItem().getItem().getRegistryName());
    }
}
