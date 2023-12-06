package me.xjqsh.lesraisinsadd.mixin;

import com.tac.guns.entity.ProjectileEntity;
import me.xjqsh.lesraisinsadd.block.interfaces.IBulletHit;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin {
    @Shadow(remap = false) public abstract ItemStack getWeapon();

    @Inject(method = "onHitBlock",remap = false,at=@At("RETURN"))
    public void onHitBlock(BlockState state, BlockPos pos, Direction face, Vector3d hitVec, CallbackInfo ci){
        if(state.getBlock() instanceof IBulletHit){
            ((IBulletHit) state.getBlock()).onBulletHit(getWeapon(),(ProjectileEntity)(Object)this,
                    state,pos,face,hitVec);
        }
    }
}
