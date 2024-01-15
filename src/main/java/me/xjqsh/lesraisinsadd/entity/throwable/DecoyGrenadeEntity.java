package me.xjqsh.lesraisinsadd.entity.throwable;


import com.tac.guns.entity.DamageSourceExplosion;
import com.tac.guns.entity.IExplosionProvider;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.item.grenades.data.ExplodeGrenadeMeta;
import me.xjqsh.lesraisinsadd.item.grenades.data.ThrowableMeta;
import me.xjqsh.lesraisinsadd.util.ExplodeUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DecoyGrenadeEntity extends ThrowableItemEntity<ThrowableMeta> {
    private static final SoundEvent fire = new SoundEvent(new ResourceLocation("lesraisinsadd:item.p90_fire"));
    private static final Set<Integer> a = new HashSet<>(Arrays.asList(100,110,115,120,125));
    public DecoyGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    public DecoyGrenadeEntity(World world, LivingEntity player, int useTick, ThrowableMeta meta) {
        super(ModEntities.DECOY_GRENADE.get(), world, player, meta);
    }

    @Override
    public ThrowableMeta createEmptyMeta() {
        return new ThrowableMeta();
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.level.isClientSide()){
            if(a.contains(tickCount)){
                this.level.playSound(null,this,fire,
                        SoundCategory.PLAYERS,10,1);
            }
        }

    }
}
