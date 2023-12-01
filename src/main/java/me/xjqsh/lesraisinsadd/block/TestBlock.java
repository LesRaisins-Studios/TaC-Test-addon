//package me.xjqsh.lesraisinsadd.block;
//
//import com.tac.guns.entity.ProjectileEntity;
//import me.xjqsh.lesraisinsadd.block.interfaces.IBulletHit;
//import me.xjqsh.lesraisinsadd.util.ExplodeUtil;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.material.Material;
//import net.minecraft.item.ItemStack;
//import net.minecraft.particles.ParticleTypes;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.shapes.ISelectionContext;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraft.world.Explosion;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.World;
//import net.minecraft.world.server.ServerWorld;
//
//public class TestBlock extends Block  {
//
//    public TestBlock() {
//        super(Properties.of(Material.DIRT).strength(0.2f).noOcclusion());
//    }
//
//    @Override
//    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
//        return 15;
//    }
//}
