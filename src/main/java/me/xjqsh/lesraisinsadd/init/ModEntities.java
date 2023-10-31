package me.xjqsh.lesraisinsadd.init;

import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.entity.CrossBowArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MOD_ID);

    public static final RegistryObject<EntityType<CrossBowArrowEntity>> ARROW = registerBasic("crossbow_arrow", CrossBowArrowEntity::new);

    private static <T extends Entity> RegistryObject<EntityType<T>> registerBasic(String id, BiFunction<EntityType<T>, World, T> function) {
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC)
                .sized(0.25F, 0.25F).setTrackingRange(100).setUpdateInterval(1)
                .noSummon()
                .fireImmune()
                .setShouldReceiveVelocityUpdates(true)
                .build(id);
        return REGISTER.register(id, () -> type);
    }
}
