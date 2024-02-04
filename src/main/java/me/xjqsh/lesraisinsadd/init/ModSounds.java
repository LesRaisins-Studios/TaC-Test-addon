package me.xjqsh.lesraisinsadd.init;


import me.xjqsh.lesraisinsadd.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);
    public static final RegistryObject<SoundEvent> flash_shield_charged = register("item.shield_charged");
    public static final RegistryObject<SoundEvent> flash_shield_fire = register("item.shield_fire");
    public static final RegistryObject<SoundEvent> hun_100 = register("kill.hun100");
    public static final RegistryObject<SoundEvent> ENTITY_SMOKE_GRENADE_EXPLOSION = register("entity.smoke_grenade.explosion");
    public static final RegistryObject<SoundEvent> ENTITY_SMOKE_GRENADE_HIT = register("entity.smoke_grenade.hit");
    public static final RegistryObject<SoundEvent> ENTITY_MOLOTOV_EXPLOSION = register("entity.molotov.explosion");
    private static RegistryObject<SoundEvent> register(String key) {
        return REGISTER.register(key, () -> new SoundEvent(new ResourceLocation(Reference.MOD_ID, key)));
    }
}
