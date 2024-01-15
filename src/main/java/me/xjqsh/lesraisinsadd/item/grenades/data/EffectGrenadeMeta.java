package me.xjqsh.lesraisinsadd.item.grenades.data;

import com.google.common.collect.Lists;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;

import java.util.List;

public class EffectGrenadeMeta extends ThrowableMeta {
    protected List<EffectInstance> effects = Lists.newArrayList();
    protected IParticleData particle = ParticleTypes.ENTITY_EFFECT;
    protected int color = 0;
    private float spreadPreTick = 0.01f;
    private float minRadius = 4.0f;
    private float maxRadius = 8.0f;
    private int areaDuration = 300;
    private float areaHeight = 0.5f;
    private boolean extinguishBySmoke = false;
    public EffectGrenadeMeta() {}
    public List<EffectInstance> getEffects() {
        return effects;
    }
    public IParticleData getParticle() {
        return particle;
    }
    public void setParticle(IParticleData particle) {
        this.particle = particle;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public float getSpreadPreTick() {
        return spreadPreTick;
    }
    public void setSpreadPreTick(float spreadPreTick) {
        this.spreadPreTick = spreadPreTick;
    }
    public float getMinRadius() {
        return minRadius;
    }
    public void setMinRadius(float minRadius) {
        this.minRadius = minRadius;
    }
    public float getMaxRadius() {
        return maxRadius;
    }
    public void setMaxRadius(float maxRadius) {
        this.maxRadius = maxRadius;
    }
    public int getAreaDuration() {
        return areaDuration;
    }
    public void setAreaDuration(int areaDuration) {
        this.areaDuration = areaDuration;
    }
    public float getAreaHeight() {
        return areaHeight;
    }
    public void setAreaHeight(float areaHeight) {
        this.areaHeight = areaHeight;
    }
    public boolean isExtinguishBySmoke() {
        return extinguishBySmoke;
    }
    public void setExtinguishBySmoke(boolean extinguishBySmoke) {
        this.extinguishBySmoke = extinguishBySmoke;
    }
}