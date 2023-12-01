package me.xjqsh.lesraisinsadd.block.interfaces;

import com.tac.guns.entity.ProjectileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public interface IBulletHit {
    void onBulletHit(ItemStack weapon, ProjectileEntity entity, BlockState state, BlockPos pos, Direction face, Vector3d hitVec);
}
