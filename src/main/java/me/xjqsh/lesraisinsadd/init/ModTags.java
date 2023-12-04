package me.xjqsh.lesraisinsadd.init;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static final Tags.IOptionalNamedTag<EntityType<?>> caster =
            EntityTypeTags.createOptional(new ResourceLocation("lesraisinsadd:caster"));

    public static void init(){}
}
