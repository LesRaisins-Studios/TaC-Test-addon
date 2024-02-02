package me.xjqsh.lesraisinsadd.util;

import com.tac.guns.client.audio.GunShotSound;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityUtil {
    /** Get all entities within the radius of a cube centered on the given position and given radius.
     * @return the entities.
     * **/
    public static <T extends Entity> List<T> getEntitiesInRadius(World world, Class<? extends T> type, Vector3d center, double radius) {
        int minX = MathHelper.floor(center.x - radius);
        int maxX = MathHelper.floor(center.x + radius);
        int minY = MathHelper.floor(center.y - radius);
        int maxY = MathHelper.floor(center.y + radius);
        int minZ = MathHelper.floor(center.z - radius);
        int maxZ = MathHelper.floor(center.z + radius);
        AxisAlignedBB aabb = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
        return world.getEntitiesOfClass(type,aabb);
    }

    public static void playSound(ResourceLocation rl,float x,float y,float z) {
        Minecraft.getInstance().getSoundManager()
                .play(new GunShotSound(rl, SoundCategory.PLAYERS,
                        x,y,z, 2, 1, false));
    }
}
