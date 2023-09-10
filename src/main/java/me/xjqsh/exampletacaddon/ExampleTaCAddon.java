package me.xjqsh.exampletacaddon;

import com.tac.guns.client.render.gun.ModelOverrides;
import me.xjqsh.exampletacaddon.client.render.ModelLoader;
import me.xjqsh.exampletacaddon.client.render.animation.HCARAnimationController;
import me.xjqsh.exampletacaddon.client.render.gun.model.hcar_animation;
import me.xjqsh.exampletacaddon.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("exampletacaddon")
public class ExampleTaCAddon {
    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleTaCAddon() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.REGISTER.register(bus);

        bus.addListener(this::onClientSetup);
        ModelLoader.init();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        ModelOverrides.register(
                ModItems.HCAR.get(),
                new hcar_animation()
        );
        HCARAnimationController.getInstance();
    }
}
