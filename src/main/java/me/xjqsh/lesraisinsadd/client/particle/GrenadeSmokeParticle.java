package me.xjqsh.lesraisinsadd.client.particle;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

public class GrenadeSmokeParticle extends SpriteTexturedParticle {
    private final IAnimatedSprite spriteSetWithAge;

    private GrenadeSmokeParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, IAnimatedSprite spriteSetWithAge) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.spriteSetWithAge = spriteSetWithAge;
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        this.xd += motionX;
        this.yd += motionY;
        this.zd += motionZ;
        float f1 = 1.0F - (float)(Math.random() * (double)0.3F);
        this.rCol = f1;
        this.gCol = f1;
        this.bCol = f1;
        this.quadSize *= 1.875F;
        int i = (int)(8.0D / (Math.random() * 0.8D + 0.3D));
        this.lifetime = (int)Math.max((float)i * 2.5F, 1.0F);
        this.hasPhysics = false;
        this.pickSprite(spriteSetWithAge);
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public float getQuadSize(float scaleFactor) {
        return this.quadSize * MathHelper.clamp(((float)this.age + scaleFactor) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.spriteSetWithAge);
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.95F;
            this.yd *= 0.94F;
            this.zd *= 0.95F;

            if (this.onGround) {
                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }

            BlockPos bp = new BlockPos(this.x,this.y,this.z);
            BlockState bs = level.getBlockState(bp);
            if(!bs.getBlock().is(Blocks.AIR)){
                if(bs.getLightBlock(level, bp) != 0 && bs.canOcclude() || bs.is(Tags.Blocks.GLASS)){
                    this.remove();
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new GrenadeSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }

}
