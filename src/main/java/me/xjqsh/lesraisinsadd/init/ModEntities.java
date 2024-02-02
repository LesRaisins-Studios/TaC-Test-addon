package me.xjqsh.lesraisinsadd.init;

import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.entity.BeamEntity;
import me.xjqsh.lesraisinsadd.entity.CrossBowArrowEntity;
import me.xjqsh.lesraisinsadd.entity.ModifiedAreaEffectCloud;
import me.xjqsh.lesraisinsadd.entity.X26HookEntity;
import me.xjqsh.lesraisinsadd.entity.throwable.*;
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
    public static final RegistryObject<EntityType<SmokeGrenadeEntity>> THROWABLE_SMOKE_GRENADE = registerBasic("smoke_grenade", SmokeGrenadeEntity::new);
    public static final RegistryObject<EntityType<ExplodeGrenadeEntity>> THROWABLE_EXPLODE_GRENADE = registerBasic("explode_grenade", ExplodeGrenadeEntity::new);
    public static final RegistryObject<EntityType<HolyGrenadeEntity>> HOLY_GRENADE = registerBasic("holy_grenade", HolyGrenadeEntity::new);
    public static final RegistryObject<EntityType<DecoyGrenadeEntity>> DECOY_GRENADE = registerBasic("decoy_grenade", DecoyGrenadeEntity::new);
    public static final RegistryObject<EntityType<X26HookEntity>> X26_HOOK = registerBasic("x26_hook", X26HookEntity::new);

    public static final RegistryObject<EntityType<AreaGrenadeEntity>> AREA_GRENADE = registerBasic("area_grenade", AreaGrenadeEntity::new);
    public static final RegistryObject<EntityType<EffectGrenadeEntity>> EFFECT_GRENADE = registerBasic("effect_grenade", EffectGrenadeEntity::new);
    public static final RegistryObject<EntityType<ModifiedAreaEffectCloud>> EFFECT_CLOUD = registerBasic("modified_effect_cloud",ModifiedAreaEffectCloud::new);
    private static <T extends Entity> RegistryObject<EntityType<T>> registerBeam(String id, BiFunction<EntityType<T>, World, T> function) {
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC)
                .sized(1F, 1F)
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
                .noSave()
                .build(id);
        return REGISTER.register(id, () -> type);
    }
}
