package me.xjqsh.lesraisinsadd.client.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Hun100Particle extends SpriteTexturedParticle {
    private final IAnimatedSprite spriteSetWithAge;

    public Hun100Particle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, IAnimatedSprite spriteSetWithAge) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.spriteSetWithAge = spriteSetWithAge;
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.quadSize = 0.3f;
        this.lifetime = 50;
        this.hasPhysics = false;
        this.pickSprite(spriteSetWithAge);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        yd *= 0.7f;
        quadSize = Math.min(quadSize + 0.1f, 0.8f);
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
//            this.setSpriteFromAge(this.spriteSetWithAge);
            this.move(xd,yd,zd);
        }
    }
    @Override
    public void render(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        Vector3d projectedView = renderInfo.getPosition();
        float x = (float)(MathHelper.lerp(partialTicks, this.xo, this.x) - projectedView.x());
        float y = (float)(MathHelper.lerp(partialTicks, this.yo, this.y) - projectedView.y());
        float z = (float)(MathHelper.lerp(partialTicks, this.zo, this.z) - projectedView.z());

        Quaternion rotation;
        if (this.roll == 0.0F) {
            rotation = renderInfo.rotation();
        } else {
            rotation = new Quaternion(renderInfo.rotation());
            float angle = MathHelper.lerp(partialTicks, this.oRoll, this.roll);
            rotation.mul(Vector3f.ZP.rotation(angle));
        }

        Quaternion copy = new Quaternion(rotation);
        int r = (age/10)%2*30-15;
        copy.mul(Vector3f.ZN.rotationDegrees(r));

        Vector3f[] vertices = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float scale = this.getQuadSize(partialTicks);

        for(int i = 0; i < 4; ++i) {
            Vector3f vertex = vertices[i];
            vertex.transform(copy);
            vertex.mul(scale);
            vertex.add(x, y, z);
        }

        float minU = this.getU0();
        float maxU = this.getU1();
        float minV = this.getV0();
        float maxV = this.getV1();
        int light = this.getLightColor(partialTicks);
        buffer.vertex(vertices[0].x(), vertices[0].y(), vertices[0].z()).uv(maxU, maxV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vertices[1].x(), vertices[1].y(), vertices[1].z()).uv(maxU, minV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vertices[2].x(), vertices[2].y(), vertices[2].z()).uv(minU, minV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vertices[3].x(), vertices[3].y(), vertices[3].z()).uv(minU, maxV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
    }


    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;
        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new Hun100Particle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
