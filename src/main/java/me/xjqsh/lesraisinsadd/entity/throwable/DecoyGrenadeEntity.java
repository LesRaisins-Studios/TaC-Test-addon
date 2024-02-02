package me.xjqsh.lesraisinsadd.entity.throwable;


import me.xjqsh.lesraisinsadd.common.data.grenades.ThrowableMeta;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import me.xjqsh.lesraisinsadd.util.EntityUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DecoyGrenadeEntity extends ThrowableItemEntity<ThrowableMeta> {
    private static final Set<Integer> beep = new HashSet<>(Arrays.asList(100,110,115,120,125));
    private ResourceLocation sound = new ResourceLocation("tac:item.glock17_fire");
    public DecoyGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    public DecoyGrenadeEntity(World world, LivingEntity player, int useTick, ThrowableItem<ThrowableMeta> meta) {
        super(ModEntities.DECOY_GRENADE.get(), world, player, meta);
    }

    public ResourceLocation getSound() {
        String s = this.getItem().getOrCreateTag().getString("Sound");
        if(s.isEmpty()){
            return sound;
        }

        ResourceLocation rl = ResourceLocation.tryParse(s);
        if(rl!=null){
            return rl;
        }else {
            return sound;
        }
    }

    public void setSound(ResourceLocation sound) {
        this.sound = sound;
    }

    public boolean shouldPlaySound(){
        int[] s = this.getItem().getOrCreateTag().getIntArray("beep");
        if(s.length>0){
            return Arrays.stream(s).anyMatch(i->i==tickCount);
        } else return beep.contains(tickCount);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level.isClientSide()){
            if(shouldPlaySound()){
                EntityUtil.playSound(getSound(), (float) this.getX(), (float) this.getY(), (float) this.getZ());
            }
        }
    }



    @Override
    public void readSpawnData(PacketBuffer buffer) {
        super.readSpawnData(buffer);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        super.writeSpawnData(buffer);
    }
}
