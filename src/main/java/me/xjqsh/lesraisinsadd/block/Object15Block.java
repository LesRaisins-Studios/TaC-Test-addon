package me.xjqsh.lesraisinsadd.block;

import com.tac.guns.entity.ProjectileEntity;
import me.xjqsh.lesraisinsadd.block.interfaces.IBulletHit;
import me.xjqsh.lesraisinsadd.util.ExplodeUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Object15Block extends Block implements IBulletHit {
    private static final VoxelShape shape;

    static {
        shape = Block.box(5, 0, 5, 11, 12, 11);
    }
    public Object15Block() {
        super(Properties.of(Material.DIRT).strength(0.2f).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }

    @Override
    public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
        super.onBlockExploded(state, world, pos, explosion);

        if(world instanceof ServerWorld){
            Explosion e2 = ExplodeUtil.createExplosion(world,pos, 2.5f);
            ((ServerWorld) world).sendParticles(ParticleTypes.LAVA,pos.getX(),pos.getY()+0.4,pos.getZ(),
                    5,0,0.1,0,0.02);
            for(BlockPos blockPos : explosion.getToBlow()){
                BlockState blockstate = world.getBlockState(blockPos);
                Block block = blockstate.getBlock();
                if(block.equals(this) || block.equals(Blocks.TNT)) {
                    block.onBlockExploded(blockstate,world,blockPos,e2);
                }
            }
        }

    }

    @Override
    public void onBulletHit(ItemStack weapon, ProjectileEntity entity, BlockState state, BlockPos pos, Direction face, Vector3d hitVec) {
        if(entity.level instanceof ServerWorld){
            Explosion explosion = ExplodeUtil.createExplosion(entity.getShooter().level,pos, 2.5f);
            ((ServerWorld) entity.level).sendParticles(ParticleTypes.LAVA,pos.getX(),pos.getY()+0.4,pos.getZ(),
                    5,0,0.1,0,0.02);
            state.onBlockExploded(entity.level,pos,explosion);
        }

    }

}
