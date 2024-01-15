package me.xjqsh.lesraisinsadd.entity.throwable;

import me.xjqsh.lesraisinsadd.item.grenades.data.ThrowableMeta;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class ThrowableItemEntity<T extends ThrowableMeta> extends ThrowableEntity implements IEntityAdditionalSpawnData {
    public float prevRotation;
    public float rotation;
    protected int maxLife;
    private ItemStack item = ItemStack.EMPTY;
    private T throwableMeta;

    public ThrowableItemEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
        this.throwableMeta = createEmptyMeta();
    }

    public ThrowableItemEntity(EntityType<? extends ThrowableItemEntity> entityType, World world, LivingEntity player, T meta) {
        super(entityType, player, world);
        this.throwableMeta = meta;
        this.maxLife = meta.getMaxLife();
    }
    public abstract T createEmptyMeta();
    public T getMeta() {
        return throwableMeta;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    @Override
    protected float getGravity() {
        return this.getMeta().getGravityVelocity();
    }

    @Override
    public void tick() {
        super.tick();
        if(this.tickCount >= this.maxLife) {
            this.remove();
            this.onDeath();
        }
        if (this.level.isClientSide()) {
            if(this.getMeta().shouldRot()){
                this.prevRotation = this.rotation;
                double speed = this.getDeltaMovement().length();

                if (speed > 0.1) {
                    this.rotation += (float) (speed * 50);
                }
            }

            renderTrailing();
        }
    }

    public void renderTrailing(){
        this.level.addParticle(ParticleTypes.SMOKE, true, this.getX(), this.getY() + 0.25, this.getZ(),
                0, 0.1, 0);
    }

    public void onDeath() {}

    protected void playImpactSound(BlockRayTraceResult result){
        BlockPos resultPos = result.getBlockPos();
        BlockState state = this.level.getBlockState(resultPos);
        SoundEvent sound = state.getBlock().getSoundType(state, this.level, resultPos, this).getStepSound();
        this.level.playSound(null, result.getLocation().x, result.getLocation().y, result.getLocation().z, sound, SoundCategory.AMBIENT, 1.0F, 1.0F);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockResult = (BlockRayTraceResult) result;
            if (this.getMeta().shouldBounce()) {
                Direction direction = blockResult.getDirection();
                double bf = this.getMeta().getBounceFactor();
                switch (direction.getAxis()) {
                    case X:
                        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.7*bf, bf, bf));
                        break;
                    case Y:
                        if (getMeta().isBrokeOnGround()) {
                            this.remove();
                            this.onDeath();
                            return;
                        } else {
                            this.setDeltaMovement(this.getDeltaMovement().multiply(bf, -0.5*bf, bf));
                            if (this.getDeltaMovement().y() < this.getMeta().getGravityVelocity()) {
                                this.setDeltaMovement(this.getDeltaMovement().multiply(1, 0, 1));
                            }
                            break;
                        }
                    case Z:
                        this.setDeltaMovement(this.getDeltaMovement().multiply(bf, bf, -0.7*bf));
                        break;
                }
                double speed = this.getDeltaMovement().length();
                if (speed > 0.1) {
                    this.playImpactSound(blockResult);
                }
            } else {
                this.remove();
                this.onDeath();
            }
        }
    }
    @Override
    public boolean isNoGravity() {
        return false;
    }
    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeItemStack(item,true);
        throwableMeta.writeBuffer(buffer);
        buffer.writeInt(maxLife);
    }
    @Override
    public void readSpawnData(PacketBuffer buffer) {
        this.setItem(buffer.readItem());
        throwableMeta.readBuffer(buffer);
        this.maxLife = buffer.readInt();
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    protected void defineSynchedData() {}
}
