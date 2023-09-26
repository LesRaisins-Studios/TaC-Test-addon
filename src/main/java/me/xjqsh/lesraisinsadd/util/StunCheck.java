package me.xjqsh.lesraisinsadd.util;

import com.tac.guns.Config;
import com.tac.guns.init.ModEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
/*sight ray trace from cgm*/
public class StunCheck {
    public static boolean calculateAndApplyEffect(Effect effect, Config.EffectCriteria criteria, LivingEntity starter, LivingEntity target) {
        // flash position
        Vector3d p = starter.position().add(0.0,1.0,0.0);
        // target eyes
        Vector3d eyes = target.getEyePosition(1.0F);
        // f to t
        Vector3d d1 = p.subtract(eyes);
        // t to f
        Vector3d d2 = eyes.subtract(p);

        double distanceMax = criteria.radius.get();
        double distance = d1.length();

        if(distance > distanceMax){
            return false;
        }

        // Calculate angle between eye-gaze line and eye-grenade line
        // 目标视线与两点连线的夹角
        double a1 = Math.toDegrees(Math.acos(target.getViewVector(1.0F).dot(d1.normalize())));
        // 释放者视线与两点连线的夹角
        double a2 = Math.toDegrees(Math.acos(starter.getViewVector(1.0F).dot(d2.normalize())));
        // 目标的视线范围
        double angleMax = 170.0 * 0.5;
        // 应用效果的角度(前方180度)
        double affectAngle = 90.0;

        if(a1 <= 0 || a2 <= 0 ||  a1 > angleMax || a2 > affectAngle){
            return false;
        }

        // Verify that light can pass through all blocks obstructing the entity's line of sight to the flash shield
        if(effect != ModEffects.BLINDED.get() || !Config.COMMON.stunGrenades.blind.criteria.raytraceOpaqueBlocks.get()
                || rayTraceOpaqueBlocks(starter,target.level, eyes, p, false, false, false) == null) {
            // Duration attenuated by distance
            int mx = criteria.durationMax.get();
            int mn = criteria.durationMin.get();
            int durationBlinded = (int) Math.round(mx - (mx - mn) * (distance / criteria.radius.get()) );

            // Duration further attenuated by angle
            durationBlinded = (int) (durationBlinded * (1.0 - (a1 * (1.0 - criteria.angleAttenuationMax.get())) / angleMax));

            target.addEffect(new EffectInstance(effect, durationBlinded));
            return !(target instanceof PlayerEntity);
        }

        return false;
    }

    @Nullable
    public static RayTraceResult rayTraceOpaqueBlocks(Entity caster, World world, Vector3d start, Vector3d end, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock)
    {
        if(!Double.isNaN(start.x) && !Double.isNaN(start.y) && !Double.isNaN(start.z))
        {
            if(!Double.isNaN(end.x) && !Double.isNaN(end.y) && !Double.isNaN(end.z))
            {
                int endX = MathHelper.floor(end.x);
                int endY = MathHelper.floor(end.y);
                int endZ = MathHelper.floor(end.z);
                int startX = MathHelper.floor(start.x);
                int startY = MathHelper.floor(start.y);
                int startZ = MathHelper.floor(start.z);
                BlockPos pos = new BlockPos(startX, startY, startZ);
                BlockState stateInside = world.getBlockState(pos);

                // Added light opacity check
                if(stateInside.getLightBlock(world, pos) != 0 && (!ignoreBlockWithoutBoundingBox || stateInside.getCollisionShape(world, pos) != VoxelShapes.empty()))
                {
                    return world.clip(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, caster));
                }

                RayTraceResult raytraceresult2 = null;
                int limit = 200;
                while(limit-- >= 0)
                {
                    if(Double.isNaN(start.x) || Double.isNaN(start.y) || Double.isNaN(start.z)) {
                        return null;
                    }

                    if(startX == endX && startY == endY && startZ == endZ) {
                        return null;
                    }

                    boolean completedX = true;
                    boolean completedY = true;
                    boolean completedZ = true;
                    double d0 = 999;
                    double d1 = 999;
                    double d2 = 999;

                    if(endX > startX) {
                        d0 = startX + 1;
                    } else if(endX < startX) {
                        d0 = startX;
                    } else {
                        completedX = false;
                    }

                    if(endY > startY) {
                        d1 = startY + 1;
                    } else if(endY < startY) {
                        d1 = startY;
                    } else {
                        completedY = false;
                    }

                    if(endZ > startZ) {
                        d2 = startZ + 1;
                    } else if(endZ < startZ) {
                        d2 = startZ;
                    } else {
                        completedZ = false;
                    }

                    double d3 = 999;
                    double d4 = 999;
                    double d5 = 999;
                    double d6 = end.x - start.x;
                    double d7 = end.y - start.y;
                    double d8 = end.z - start.z;

                    if(completedX) d3 = (d0 - start.x) / d6;

                    if(completedY) d4 = (d1 - start.y) / d7;

                    if(completedZ) d5 = (d2 - start.z) / d8;

                    if(d3 == -0) d3 = -1.0E-4D;

                    if(d4 == -0) d4 = -1.0E-4D;

                    if(d5 == -0) d5 = -1.0E-4D;

                    Direction direction;

                    if(d3 < d4 && d3 < d5)
                    {
                        direction = endX > startX ? Direction.WEST : Direction.EAST;
                        start = new Vector3d(d0, start.y + d7 * d3, start.z + d8 * d3);
                    }
                    else if(d4 < d5)
                    {
                        direction = endY > startY ? Direction.DOWN : Direction.UP;
                        start = new Vector3d(start.x + d6 * d4, d1, start.z + d8 * d4);
                    }
                    else
                    {
                        direction = endZ > startZ ? Direction.NORTH : Direction.SOUTH;
                        start = new Vector3d(start.x + d6 * d5, start.y + d7 * d5, d2);
                    }

                    startX = MathHelper.floor(start.x) - (direction == Direction.EAST ? 1 : 0);
                    startY = MathHelper.floor(start.y) - (direction == Direction.UP ? 1 : 0);
                    startZ = MathHelper.floor(start.z) - (direction == Direction.SOUTH ? 1 : 0);
                    pos = new BlockPos(startX, startY, startZ);
                    BlockState state = world.getBlockState(pos);

                    // Added light opacity check
                    if(state.getLightBlock(world, pos) != 0 && (!ignoreBlockWithoutBoundingBox || state.getMaterial() == Material.PORTAL || state.getCollisionShape(world, pos) != VoxelShapes.empty()))
                    {
                        return world.clip(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, caster));
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }
}
