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
    private static RegistryObject<SoundEvent> register(String key) {
        return REGISTER.register(key, () -> new SoundEvent(new ResourceLocation(Reference.MOD_ID, key)));
    }
}
