package me.xjqsh.lesraisinsadd;


import com.tac.guns.client.render.gun.ModelOverrides;
import com.tac.guns.common.ProjectileManager;
import com.tac.guns.entity.MissileEntity;
import me.xjqsh.lesraisinsadd.client.render.ArrowRender;
import me.xjqsh.lesraisinsadd.client.render.ModelLoader;
import me.xjqsh.lesraisinsadd.client.render.animation.*;
import me.xjqsh.lesraisinsadd.client.render.model.gun.*;
import me.xjqsh.lesraisinsadd.entity.CrossBowArrowEntity;
import me.xjqsh.lesraisinsadd.init.ModEntities;
import me.xjqsh.lesraisinsadd.init.ModItems;
import me.xjqsh.lesraisinsadd.init.ModSounds;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
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
        ModEntities.REGISTER.register(bus);

        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);
        ModelLoader.init();

    }

    private void onCommonSetup(FMLLoadCompleteEvent event) {
        ProjectileManager.getInstance().registerFactory(ModItems.CROSSBOW_ARROW.get(),
                new CrossBowArrowEntity.ArrowFactory());

        ProjectileManager.getInstance().registerFactory(ModItems.M202_ROCKET.get(),
                (worldIn, entity, weapon, item, modifiedGun, randP, randY) ->
                    new MissileEntity(com.tac.guns.init.ModEntities.RPG7_MISSILE.get(),
                            worldIn, entity, weapon, item, modifiedGun, 1.5F)

        );
    }

    private void onClientSetup(FMLClientSetupEvent event) {
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
                ModItems.ANGEL.get(),
                new angle_animation()
        );
        ModelOverrides.register(ModItems.SA58.get(),
                new sa58_animation()
        );
        ModelOverrides.register(ModItems.CROSSBOW.get(),
                new crossbow_animation()
        );
        ModelOverrides.register(ModItems.MARLIN_1895.get(),
                new marlin_1895_animation());
        ModelOverrides.register(ModItems.M202.get(),
                new m202_animation());
        ModelOverrides.register(ModItems.LOK1.get(),
                new lok1_animation());
        ModelOverrides.register(ModItems.THE_LAST_WORD.get(),
                new the_last_word_animation());
        ModelOverrides.register(ModItems.THE_FIRST_CURSE.get(),
                new the_first_curse_animation());


        P90AnimationController.getInstance();
        PP19AnimationController.getInstance();
        PPK20AnimationController.getInstance();
        SVDAnimationController.getInstance();
        AUGAnimationController.getInstance();
        QSZ92AnimationController.getInstance();
        ANGLEAnimationController.getInstance();
        SA58AnimationController.getInstance();
        CROSSBOWAnimationController.getInstance();
        MARLIN1895AnimationController.getInstance();
        M202AnimationController.getInstance();
        LOK1AnimationController.getInstance();
        THELASTWORDAnimationController.getInstance();
        THEFIRSTCURSEAnimationController.getInstance();

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ARROW.get(), ArrowRender::new);
    }
}
