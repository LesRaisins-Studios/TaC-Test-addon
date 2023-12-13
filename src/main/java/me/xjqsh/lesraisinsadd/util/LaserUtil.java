package me.xjqsh.lesraisinsadd.util;

import me.xjqsh.lesraisinsadd.entity.BeamEntity;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class LaserUtil {
    public static void createBeams(PlayerEntity player, int amount, BeamEntity.BeamInfo info){
        Vector3d viewVector = player.getViewVector(1.0f);

        Vector3d v = player.getEyePosition(1.0f)
                .add(viewVector.x*2,viewVector.y*2,viewVector.z*2);

        for (int i = 0; i < amount; i++) {
            info.setXr(player.getViewXRot(1.0f));
            info.setYr(player.getViewYRot(1.0f));
            BeamEntity entity = new BeamEntity(ModEntities.BEAM.get(),player.level,v,info,i);

            player.level.addFreshEntity(entity);

            v = v.add(viewVector.x*10,viewVector.y*10,viewVector.z*10);
        }
    }

//    public static void rayTrace(PlayerEntity player, int maxLength){
//        Vector3d viewVector = player.getViewVector(1.0f);
//
//        Vector3d start = player.getEyePosition(1.0f)
//                .add(viewVector.x*2,viewVector.y*2,viewVector.z*2);
//
//        Vector3d end = start.add(viewVector.multiply(50,50,50));
//
//
//        IBlockReader.traverseBlocks(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, player),
//                (ctx,blockPos)->{
//
//            return null;
//        },(ctx)->{
//            return null;
//        });
//
//
//        if(result.getType() == RayTraceResult.Type.MISS)return;
//
//        BlockPos blockPos = result.getBlockPos();
//
//        player.level.explode(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(),4, Explosion.Mode.BREAK);
//    }
}
