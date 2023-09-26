package me.xjqsh.lesraisinsadd;

import com.tac.guns.client.render.gun.ModelOverrides;
import me.xjqsh.lesraisinsadd.client.render.ModelLoader;
import me.xjqsh.lesraisinsadd.client.listener.PlayerRenderingHandle;
import me.xjqsh.lesraisinsadd.client.render.animation.HCARAnimationController;
import me.xjqsh.lesraisinsadd.client.render.animation.P90AnimationController;
import me.xjqsh.lesraisinsadd.client.render.animation.PP19AnimationController;
import me.xjqsh.lesraisinsadd.client.render.animation.PPK20AnimationController;
import me.xjqsh.lesraisinsadd.client.render.model.gun.hcar_animation;
import me.xjqsh.lesraisinsadd.client.render.model.gun.p90_animation;
import me.xjqsh.lesraisinsadd.client.render.model.gun.pp19_animation;
import me.xjqsh.lesraisinsadd.client.render.model.gun.ppk20_animation;
import me.xjqsh.lesraisinsadd.init.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class ExampleTaCAddon {
    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleTaCAddon() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.REGISTER.register(bus);

        bus.addListener(this::onClientSetup);
        ModelLoader.init();
        MinecraftForge.EVENT_BUS.register(new PlayerRenderingHandle());
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        ModelOverrides.register(
                ModItems.HCAR.get(),
                new hcar_animation()
        );
        ModelOverrides.register(
                ModItems.P90.get(),
                new p90_animation()
        );
        ModelOverrides.register(
                ModItems.PP19.get(),
                new pp19_animation()
        );
        ModelOverrides.register(
                ModItems.PPK20.get(),
                new ppk20_animation()
        );
        HCARAnimationController.getInstance();
        P90AnimationController.getInstance();
        PP19AnimationController.getInstance();
        PPK20AnimationController.getInstance();
    }
}
