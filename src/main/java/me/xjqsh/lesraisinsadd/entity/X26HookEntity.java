package me.xjqsh.lesraisinsadd.entity;

import com.tac.guns.common.ReloadTracker;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class X26HookEntity extends AbstractProjectileEntity implements IEntityAdditionalSpawnData {
    public Entity hooked;
    private boolean inGround;
    private BlockState lastState;
    private int power = 5;
    private static final DataParameter<Integer> DATA_HOOKED_ENTITY = EntityDataManager.defineId(X26HookEntity.class, DataSerializers.INT);
    private int inGroundTime = 0;

    public X26HookEntity(EntityType<? extends AbstractProjectileEntity> p_i231584_1_, World p_i231584_2_) {
        super(p_i231584_1_, p_i231584_2_);
    }
    public X26HookEntity(PlayerEntity player) {
        super(ModEntities.X26_HOOK.get(), player.level);
        this.noCulling = true;
        this.setOwner(player);
        this.setPos(player.getX(),player.getEyeY(),player.getZ());
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderAtSqrDistance(double p_70112_1_) {
        return p_70112_1_ < 4096.0D;
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_HOOKED_ENTITY, 0);
    }

    public void tick() {
        super.tick();
        boolean flag = false;
        Vector3d vector3d = this.getDeltaMovement();
        if(this.getPlayerOwner()==null || !this.getPlayerOwner().isAlive()){
            this.remove();
            return;
        }

        if(!ModItems.X26.get().getItem().equals(this.getPlayerOwner().getMainHandItem().getItem()) || ReloadTracker.isPlayerReload(getPlayerOwner())){
            this.remove();
            return;
        }

        if(this.getPlayerOwner().position().distanceTo(this.position())>48f){
            this.remove();
        }

        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float f = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
            this.yRot = (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI));
            this.xRot = (float)(MathHelper.atan2(vector3d.y, f) * (double)(180F / (float)Math.PI));
            this.yRotO = this.yRot;
            this.xRotO = this.xRot;
        }

        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level.getBlockState(blockpos);
        if (!blockstate.getBlock().isAir(blockstate,this.level, blockpos) && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.level, blockpos);
            if (!voxelshape.isEmpty()) {
                Vector3d vector3d1 = this.position();

                for(AxisAlignedBB axisalignedbb : voxelshape.toAabbs()) {
                    if (axisalignedbb.move(blockpos).contains(vector3d1)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }

        if (this.inGround && !flag) {
            if (this.lastState != blockstate && this.shouldFall()) {
                this.startFalling();
            }

            if(inGroundTime++>200){
                this.remove();
            }

        } else {
            if(power<=0 && tickCount>300){
                this.remove();
                return;
            }

            if (this.hooked != null) {
                if (!this.hooked.isAlive()) {
                    this.hooked = null;
                } else {
                    if(!this.level.isClientSide()){

                        if(power>0){
                            boolean s = this.hooked.hurt(DamageSource.indirectMagic(this,this.getPlayerOwner()),20f);
                            if(s)power--;
                        }
                    }
                    this.setPos(this.hooked.getX(), this.hooked.getY(0.8), this.hooked.getZ());
                }
                return;
            }

            checkCollision();

            vector3d = this.getDeltaMovement();
            double d3 = vector3d.x;
            double d4 = vector3d.y;
            double d0 = vector3d.z;


            double d5 = this.getX() + d3;
            double d1 = this.getY() + d4;
            double d2 = this.getZ() + d0;
            float f1 = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
            this.yRot = (float) (MathHelper.atan2(d3, d0) * (double) (180F / (float) Math.PI));

            this.xRot = (float)(MathHelper.atan2(d4, f1) * (double)(180F / (float)Math.PI));
            this.xRot = lerpRotation(this.xRotO, this.xRot);
            this.yRot = lerpRotation(this.yRotO, this.yRot);
            float f2 = 0.99F;


            this.setDeltaMovement(vector3d.scale(f2));
            if (!this.isNoGravity() && !flag) {
                Vector3d vector3d4 = this.getDeltaMovement();
                this.setDeltaMovement(vector3d4.x, vector3d4.y - (double)0.05F, vector3d4.z);
            }

            this.setPos(d5, d1, d2);
            this.checkInsideBlocks();
        }
    }

    public PlayerEntity getPlayerOwner() {
        return (PlayerEntity) this.getOwner();
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeUUID(this.getOwner().getUUID());
    }

    @Override
    public void readSpawnData(PacketBuffer buffer) {
        this.setOwner(this.level.getPlayerByUUID(buffer.readUUID()));
    }

    private void checkCollision() {
        RayTraceResult raytraceresult = ProjectileHelper.getHitResult(this, this::canHitEntity);
        if (raytraceresult.getType() == RayTraceResult.Type.MISS || !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) this.onHit(raytraceresult);
    }

    protected void onHit(RayTraceResult p_70227_1_) {
        RayTraceResult.Type raytraceresult$type = p_70227_1_.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onHitEntity((EntityRayTraceResult)p_70227_1_);
        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            this.onHitBlock((BlockRayTraceResult)p_70227_1_);
        }

    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        if (!this.level.isClientSide) {
            this.setDeltaMovement(0,0,0);
            this.hooked = result.getEntity();
            this.setHookedEntity();
        }
    }

    protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
        this.lastState = this.level.getBlockState(p_230299_1_.getBlockPos());
        Vector3d vector3d = p_230299_1_.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vector3d);
        Vector3d vector3d1 = vector3d.normalize().scale(0.05F);
        this.setPosRaw(this.getX() - vector3d1.x, this.getY() - vector3d1.y, this.getZ() - vector3d1.z);

        this.inGround = true;
    }

    private boolean shouldFall() {
        return this.inGround && this.level.noCollision((new AxisAlignedBB(this.position(), this.position())).inflate(0.06D));
    }

    private void startFalling() {
        this.inGround = false;
        this.inGroundTime = 0;
        Vector3d vector3d = this.getDeltaMovement();
        this.setDeltaMovement(vector3d.multiply(this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F));
    }

    public void move(MoverType p_213315_1_, Vector3d p_213315_2_) {
        super.move(p_213315_1_, p_213315_2_);
        if (p_213315_1_ != MoverType.SELF && this.shouldFall()) {
            this.startFalling();
        }
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        if (DATA_HOOKED_ENTITY.equals(p_184206_1_)) {
            int i = this.getEntityData().get(DATA_HOOKED_ENTITY);
            this.hooked = i > 0 ? this.level.getEntity(i - 1) : null;
        }

        super.onSyncedDataUpdated(p_184206_1_);
    }

    private void setHookedEntity() {
        this.getEntityData().set(DATA_HOOKED_ENTITY, this.hooked.getId() + 1);
    }
}
