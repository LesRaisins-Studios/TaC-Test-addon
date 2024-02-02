package me.xjqsh.lesraisinsadd.effect;

import net.minecraft.entity.LivingEntity;

public interface ITickEffect{
    void applyTick(LivingEntity entity, int amplifier, int duration);
}
