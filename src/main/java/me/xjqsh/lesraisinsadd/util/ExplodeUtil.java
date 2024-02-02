package me.xjqsh.lesraisinsadd.util;

import com.tac.guns.Config;
import com.tac.guns.entity.IExplosionProvider;
import com.tac.guns.interfaces.IExplosionDamageable;
import com.tac.guns.world.ProjectileExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SExplosionPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ExplodeUtil {
    public static Explosion createExplosion(World world, BlockPos blockPos, float power) {
        if (world.isClientSide())
            return null;


        ProjectileExplosion explosion = new ProjectileExplosion(world, null, null, null,
                blockPos.getX(), blockPos.getY(), blockPos.getZ(), power,3, Explosion.Mode.NONE);

        if (ForgeEventFactory.onExplosionStart(world, explosion))
            return null;

        // Do explosion logic
        explosion.explode();
        explosion.finalizeExplosion(false);

        for (ServerPlayerEntity player : ((ServerWorld) world).players()) {
            if (player.distanceToSqr(blockPos.getX(), blockPos.getY(), blockPos.getZ()) < 4096) {
                player.connection.send(new SExplosionPacket(blockPos.getX(), blockPos.getY(), blockPos.getZ(), power,
                        new ArrayList<>(), explosion.getHitPlayers().get(player)));
            }
        }

        return explosion;
    }

    public static void createExplosion(Entity entity, float power, float radius, @Nullable Vector3d hitVec) {
        World world = entity.level;
        if (!world.isClientSide()) {
            Explosion.Mode mode = Config.COMMON.gameplay.enableExplosionBreak.get() ? Explosion.Mode.BREAK : Explosion.Mode.NONE;
            DamageSource source = null;
            if (entity instanceof IExplosionProvider) {
                source = ((IExplosionProvider)entity).createDamageSource();
            }

            ProjectileExplosion explosion;
            if (hitVec == null) {
                explosion = new ProjectileExplosion(world, entity, source, null, entity.getX(), entity.getY(), entity.getZ(), power, radius, mode);
            } else {
                explosion = new ProjectileExplosion(world, entity, source, null, hitVec.x(), hitVec.y(), hitVec.z(), power, radius, mode);
            }

            if (!ForgeEventFactory.onExplosionStart(world, explosion)) {

                explosion.explode();
                explosion.finalizeExplosion(true);
                explosion.getToBlow().forEach((pos) -> {
                    if (world.getBlockState(pos).getBlock() instanceof IExplosionDamageable) {
                        ((IExplosionDamageable)world.getBlockState(pos).getBlock()).onProjectileExploded(world, world.getBlockState(pos), pos, entity);
                    }

                });

                if (mode == Explosion.Mode.NONE) {
                    explosion.clearToBlow();
                }

                for (ServerPlayerEntity player : ((ServerWorld) world).players()) {
                    if (player.distanceToSqr(entity.getX(), entity.getY(), entity.getZ()) < 4096.0) {
                        player.connection.send(new SExplosionPacket(entity.getX(), entity.getY(), entity.getZ(), radius, explosion.getToBlow(), explosion.getHitPlayers().get(player)));
                    }
                }

            }
        }
    }
}
