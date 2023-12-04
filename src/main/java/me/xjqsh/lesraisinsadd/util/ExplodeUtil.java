package me.xjqsh.lesraisinsadd.util;

import com.tac.guns.world.ProjectileExplosion;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SExplosionPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.ArrayList;

public class ExplodeUtil {
    public static Explosion createExplosion(World world, BlockPos blockPos, float power) {
        if (world.isClientSide())
            return null;


        ProjectileExplosion explosion = new ProjectileExplosion(world, null, null, null,
                blockPos.getX(), blockPos.getY(), blockPos.getZ(), power, Explosion.Mode.NONE);

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
}
