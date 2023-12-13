package me.xjqsh.lesraisinsadd.init;


import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.client.particle.Hun100Particle;
import me.xjqsh.lesraisinsadd.client.particle.ReloadingParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes
{
    public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Reference.MOD_ID);
    public static final RegistryObject<BasicParticleType> Hun100 = REGISTER.register("hun100", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> Reloading = REGISTER.register("reloading", () -> new BasicParticleType(true));


    @SubscribeEvent
    public static void onRegisterParticleFactory(ParticleFactoryRegisterEvent event)
    {
        ParticleManager particleManager = Minecraft.getInstance().particleEngine;
        particleManager.register(Hun100.get(), Hun100Particle.Factory::new);
        particleManager.register(Reloading.get(), ReloadingParticle.Factory::new);
    }
}
