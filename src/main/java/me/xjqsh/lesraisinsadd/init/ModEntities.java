package me.xjqsh.lesraisinsadd.init;

import com.tac.guns.entity.ThrowableStunGrenadeEntity;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.entity.BeamEntity;
import me.xjqsh.lesraisinsadd.entity.CrossBowArrowEntity;
import me.xjqsh.lesraisinsadd.entity.ModifiedAreaEffectCloud;
import me.xjqsh.lesraisinsadd.entity.throwable.EffectCloudGrenadeEntity;
import me.xjqsh.lesraisinsadd.entity.throwable.ThrowableSmokeGrenadeEntity;
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
    public static final RegistryObject<EntityType<BeamEntity>> BEAM = registerBeam("beam", BeamEntity::new);
    public static final RegistryObject<EntityType<ThrowableSmokeGrenadeEntity>> THROWABLE_SMOKE_GRENADE = registerBasic("throwable_smoke_grenade", ThrowableSmokeGrenadeEntity::new);
    public static final RegistryObject<EntityType<EffectCloudGrenadeEntity>> THROWABLE_EFFECT_GRENADE = registerBasic("throwable_effect_grenade", EffectCloudGrenadeEntity::new);
    public static final RegistryObject<EntityType<ModifiedAreaEffectCloud>> EFFECT_CLOUD = registerBasic("modified_effect_cloud",ModifiedAreaEffectCloud::new);
    private static <T extends Entity> RegistryObject<EntityType<T>> registerBeam(String id, BiFunction<EntityType<T>, World, T> function) {
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC)
                .sized(40F, 40F)
                .setTrackingRange(100)
                .setUpdateInterval(1)
                .noSummon()
                .fireImmune()
                .setShouldReceiveVelocityUpdates(false)
                .build(id);
        return REGISTER.register(id, () -> type);
    }
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
