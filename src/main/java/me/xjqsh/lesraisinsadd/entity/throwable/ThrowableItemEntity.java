package me.xjqsh.lesraisinsadd.entity.throwable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public abstract class ThrowableItemEntity extends ThrowableEntity implements IEntityAdditionalSpawnData
{
    public float prevRotation;
    public float rotation;
    private ItemStack item = ItemStack.EMPTY;
    protected boolean shouldBounce;

    /* Should it break when it fell to the ground, only take effect when shouldBounce is true. */
    protected boolean brokeOnGround = false;
    private float gravityVelocity = 0.03F;

    /* The max life of the entity. If -1, will stay alive forever and will need to be explicitly removed. */
    private int maxLife = 20 * 10;

    public ThrowableItemEntity(EntityType<? extends ThrowableItemEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    public ThrowableItemEntity(EntityType<? extends ThrowableItemEntity> entityType, World world, LivingEntity player) {
        super(entityType, player, world);
    }


    public void setItem(ItemStack item)
    {
        this.item = item;
    }

    public ItemStack getItem()
    {
        return this.item;
    }

    protected void setShouldBounce(boolean shouldBounce)
    {
        this.shouldBounce = shouldBounce;
    }
    public void setBrokeOnGround(boolean brokeOnGround) {
        this.brokeOnGround = brokeOnGround;
    }

    protected void setGravityVelocity(float gravity)
    {
        this.gravityVelocity = gravity;
    }

    @Override
    protected float getGravity()
    {
        return this.gravityVelocity;
    }

    public void setMaxLife(int maxLife)
    {
        this.maxLife = maxLife;
    }

    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public void tick()
    {
        super.tick();
        if(this.shouldBounce && this.tickCount >= this.maxLife) {
            this.remove();
            this.onDeath();
        }
        this.prevRotation = this.rotation;
        double speed = this.getDeltaMovement().length();
        if (speed > 0.1) {
            this.rotation += (float) (speed * 50);
        }
        if (this.level.isClientSide()) {
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
    protected void onHit(RayTraceResult result)
    {
        if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockResult = (BlockRayTraceResult) result;
            if (this.shouldBounce) {
                Direction direction = blockResult.getDirection();
                switch (direction.getAxis()) {
                    case X:
                        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.5, 0.75, 0.75));
                        break;
                    case Y:
                        if (brokeOnGround) {
                            this.remove();
                            this.onDeath();
                            return;
                        } else {
                            this.setDeltaMovement(this.getDeltaMovement().multiply(0.75, -0.25, 0.75));
                            if (this.getDeltaMovement().y() < this.getGravity()) {
                                this.setDeltaMovement(this.getDeltaMovement().multiply(1, 0, 1));
                            }
                            break;
                        }
                    case Z:
                        this.setDeltaMovement(this.getDeltaMovement().multiply(0.75, 0.75, -0.5));
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
    public boolean isNoGravity()
    {
        return false;
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer)
    {
        buffer.writeBoolean(this.shouldBounce);
        buffer.writeFloat(this.gravityVelocity);
        buffer.writeItem(this.item);
    }

    @Override
    public void readSpawnData(PacketBuffer buffer)
    {
        this.shouldBounce = buffer.readBoolean();
        this.gravityVelocity = buffer.readFloat();
        this.item = buffer.readItem();
    }

    @Override
    public IPacket<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    protected void defineSynchedData() {

    }

}
