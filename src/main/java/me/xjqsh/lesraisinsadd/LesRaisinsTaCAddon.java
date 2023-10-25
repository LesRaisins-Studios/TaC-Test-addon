package me.xjqsh.lesraisinsadd;

import com.tac.guns.client.render.gun.ModelOverrides;
import me.xjqsh.lesraisinsadd.client.render.ModelLoader;
import me.xjqsh.lesraisinsadd.client.listener.PlayerRenderingHandle;
import me.xjqsh.lesraisinsadd.client.render.animation.*;
import me.xjqsh.lesraisinsadd.client.render.model.gun.*;
import me.xjqsh.lesraisinsadd.init.ModItems;
import me.xjqsh.lesraisinsadd.init.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class LesRaisinsTaCAddon {
    private static final Logger LOGGER = LogManager.getLogger();

    public LesRaisinsTaCAddon() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.REGISTER.register(bus);
        ModSounds.REGISTER.register(bus);

        bus.addListener(this::onClientSetup);
        ModelLoader.init();

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
        ModelOverrides.register(
                ModItems.SVD.get(),
                new svd_animation()
        );
        ModelOverrides.register(
                ModItems.AUG.get(),
                new aug_animation()
        );
        ModelOverrides.register(
                ModItems.QSZ92.get(),
                new qsz92_animation()
        );
        ModelOverrides.register(
                ModItems.ANGLE.get(),
                new angle_animation()
        );
        HCARAnimationController.getInstance();
        P90AnimationController.getInstance();
        PP19AnimationController.getInstance();
        PPK20AnimationController.getInstance();
        SVDAnimationController.getInstance();
        AUGAnimationController.getInstance();
        QSZ92AnimationController.getInstance();
        ANGLEAnimationController.getInstance();
    }
}
