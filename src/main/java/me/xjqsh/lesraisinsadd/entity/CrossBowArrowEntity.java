package me.xjqsh.lesraisinsadd.entity;

import com.tac.guns.common.Gun;
import com.tac.guns.entity.ProjectileEntity;
import com.tac.guns.interfaces.IProjectileFactory;
import com.tac.guns.item.GunItem;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CrossBowArrowEntity extends ProjectileEntity{

    public CrossBowArrowEntity(EntityType<? extends Entity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    public CrossBowArrowEntity(EntityType<? extends Entity> entityType, World worldIn, LivingEntity shooter, ItemStack weapon, GunItem item, Gun modifiedGun) {
        super(entityType, worldIn, shooter, weapon, item, modifiedGun, 0, 0);
    }

//    @Override
//    protected void onHit(RayTraceResult result, Vector3d startVec, Vector3d endVec) {
//        if (this.modifiedGun != null) {
//            if (!MinecraftForge.EVENT_BUS.post(new GunProjectileHitEvent(result, this))) {
//                if (result instanceof BlockRayTraceResult) {
//                    BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult)result;
//                    if (blockRayTraceResult.getType() != RayTraceResult.Type.MISS) {
//                        Vector3d hitVec = result.getLocation();
//                        BlockPos pos = blockRayTraceResult.getBlockPos();
//                        BlockState state = this.level.getBlockState(pos);
//                        Block block = state.getBlock();
//
//                        if (!state.getMaterial().isReplaceable()){
//                            this.onGround = true;
//                            this.setDeltaMovement(Vector3d.ZERO);
//                        }
//
//
//                        if (!Objects.requireNonNull(block.getRegistryName()).getPath().contains("_button")) {
//                            if (Config.COMMON.gameplay.enableGunGriefing.get() && (block instanceof BreakableBlock || block instanceof PaneBlock) && state.getMaterial() == Material.GLASS) {
//                                this.level.destroyBlock(blockRayTraceResult.getBlockPos(), Config.COMMON.gameplay.glassDrop.get(), this.shooter);
//                            }
//
//                            this.onHitBlock(state, pos, blockRayTraceResult.getDirection(), hitVec.x, hitVec.y, hitVec.z);
//                            if (block instanceof BellBlock) {
//                                BellBlock bell = (BellBlock)block;
//                                bell.onHit(this.level, state, blockRayTraceResult, (PlayerEntity)this.shooter, true);
//                            }
//
//                            int fireStarterLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FIRE_STARTER.get(), this.getWeapon());
//                            if (fireStarterLevel > 0 && Config.COMMON.gameplay.fireStarterCauseFire.get()) {
//                                BlockPos offsetPos = pos.relative(blockRayTraceResult.getDirection());
//                                if (AbstractFireBlock.canBePlacedAt(this.level, offsetPos, blockRayTraceResult.getDirection())) {
//                                    BlockState fireState = AbstractFireBlock.getState(this.level, offsetPos);
//                                    this.level.setBlock(offsetPos, fireState, 11);
//                                    ((ServerWorld)this.level).sendParticles(ParticleTypes.LAVA, hitVec.x - 1.0 + this.random.nextDouble() * 2.0, hitVec.y, hitVec.z - 1.0 + this.random.nextDouble() * 2.0, 4, 0.0, 0.0, 0.0, 0.0);
//                                }
//                            }
//
//                        }
//                    }
//                } else {
//                    if (result instanceof ExtendedEntityRayTraceResult) {
//                        ExtendedEntityRayTraceResult entityRayTraceResult = (ExtendedEntityRayTraceResult)result;
//                        Entity entity = entityRayTraceResult.getEntity();
//                        if (entity.getId() == this.shooterId && !(Boolean)Config.COMMON.development.bulletSelfHarm.get()) {
//                            return;
//                        }
//
//                        int fireStarterLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FIRE_STARTER.get(), this.getWeapon());
//                        if (fireStarterLevel > 0) {
//                            entity.setSecondsOnFire(2);
//                        }
//
//                        if (!entity.isAlive()) {
//                            entity.invulnerableTime = 0;
//                        } else if (entity.isAlive()) {
//                            this.onHitEntity(entity, result.getLocation(), startVec, endVec, entityRayTraceResult.isHeadshot());
//                            int collateralLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.COLLATERAL.get(), this.getWeapon());
//                            if (collateralLevel == 0) {
//                                this.remove();
//                            }
//
//                            entity.invulnerableTime = 0;
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//
//    public void tick() {
//        super.tick();
//        this.updateHeading();
//        this.onProjectileTick();
//        if (!this.level.isClientSide()) {
//            Vector3d startVec = this.position();
//            if (this.tickCount < 1) {
//                this.startPos = startVec;
//            }
//
//            Vector3d endVec = startVec.add(this.getDeltaMovement());
//            if (this.shooter instanceof PlayerEntity) {
//                Vector3d v = cachePlayerVelocity.get((PlayerEntity)this.shooter);
//                startVec = startVec.subtract(v);
//                endVec = endVec.subtract(v);
//            }
//
//            RayTraceResult result = rayTraceBlocks(this.level, new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
//            if (result.getType() != RayTraceResult.Type.MISS) {
//                endVec = result.getLocation();
//            }
//
//            List<EntityResult> hitEntities = null;
//            int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.COLLATERAL.get(), this.getWeapon());
//            if (level == 0) {
//                EntityResult entityResult = this.findEntityOnPath(startVec, endVec);
//                if (entityResult != null) {
//                    hitEntities = Collections.singletonList(entityResult);
//                }
//            } else {
//                hitEntities = this.findEntitiesOnPath(startVec, endVec);
//            }
//
//            if (hitEntities != null && !hitEntities.isEmpty()) {
//
//                for (EntityResult entityResult : hitEntities) {
//                    result = new ExtendedEntityRayTraceResult(entityResult);
//                    if (((EntityRayTraceResult) result).getEntity() instanceof PlayerEntity) {
//                        PlayerEntity player = (PlayerEntity) ((EntityRayTraceResult) result).getEntity();
//                        if (this.shooter instanceof PlayerEntity && !((PlayerEntity) this.shooter).canHarmPlayer(player) && !(Boolean) Config.COMMON.development.bulletSelfHarm.get()) {
//                            result = null;
//                        }
//                    }
//
//                    if (result != null) {
//                        this.onHit(result, startVec, endVec);
//                    }
//                }
//            } else {
//                this.onHit(result, startVec, endVec);
//            }
//        }
//
//         if(!onGround) {
//            double nextPosX = this.getX() + this.getDeltaMovement().x();
//            double nextPosY = this.getY() + this.getDeltaMovement().y();
//            double nextPosZ = this.getZ() + this.getDeltaMovement().z();
//            this.setPos(nextPosX, nextPosY, nextPosZ);
//            if (this.projectile.isGravity()) {
//                this.setDeltaMovement(this.getDeltaMovement().add(0.0, this.modifiedGravity, 0.0));
//            }
//        }
//
//        if (this.tickCount >= this.life) {
//            if (this.isAlive()) {
//                this.onExpired();
//            }
//
//            this.remove();
//        }
//
//    }
//
//    @Override
//    protected void onProjectileTick() {
//        LOGGER.info(""+tickCount);
//    }

    public static class ArrowFactory implements IProjectileFactory {
        @Override
        public CrossBowArrowEntity create(World world, LivingEntity entity, ItemStack weapon, GunItem gunItem, Gun gun, float randP, float randY) {
            return new CrossBowArrowEntity(ModEntities.ARROW.get(), world, entity, weapon, gunItem, gun);
        }
    }
}
