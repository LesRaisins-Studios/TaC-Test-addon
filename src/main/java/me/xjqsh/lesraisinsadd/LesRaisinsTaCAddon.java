package me.xjqsh.lesraisinsadd;


import com.tac.guns.client.render.gun.ModelOverrides;
import com.tac.guns.common.ProjectileManager;
import com.tac.guns.entity.MissileEntity;
import me.xjqsh.lesraisinsadd.client.LRKeys;
import me.xjqsh.lesraisinsadd.client.animation.*;
import me.xjqsh.lesraisinsadd.client.render.entity.ArrowRender;
import me.xjqsh.lesraisinsadd.client.render.entity.BeamRender;
import me.xjqsh.lesraisinsadd.client.render.ModelLoader;
import me.xjqsh.lesraisinsadd.client.render.entity.NoRotGrenadeRenderer;
import me.xjqsh.lesraisinsadd.client.render.entity.ThrowableItemRenderer;
import me.xjqsh.lesraisinsadd.client.render.model.gun.*;
import me.xjqsh.lesraisinsadd.client.render.model.scope.af6_scope;
import me.xjqsh.lesraisinsadd.entity.CrossBowArrowEntity;
import me.xjqsh.lesraisinsadd.init.*;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import me.xjqsh.lesraisinsadd.network.PacketHandler;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.AreaEffectCloudRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class LesRaisinsTaCAddon {
    public static final Logger LOGGER = LogManager.getLogger();

    public LesRaisinsTaCAddon() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.REGISTER.register(bus);
        ModSounds.REGISTER.register(bus);
        ModEntities.REGISTER.register(bus);
        ModParticleTypes.REGISTER.register(bus);
        ModBlocks.REGISTER.register(bus);
        ModEffects.REGISTER.register(bus);

        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onCommonSetupComplete);

        ModelLoader.init();
        ModTags.init();
    }
    private void onCommonSetup(FMLCommonSetupEvent event){
        PacketHandler.init();
    }
    private void onCommonSetupComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(()->{
            ProjectileManager.getInstance().registerFactory(ModItems.CROSSBOW_ARROW.get(),
                    new CrossBowArrowEntity.ArrowFactory());

            ProjectileManager.getInstance().registerFactory(ModItems.M202_ROCKET.get(),
                    (worldIn, entity, weapon, item, modifiedGun, randP, randY) ->
                            new MissileEntity(com.tac.guns.init.ModEntities.RPG7_MISSILE.get(),
                                    worldIn, entity, weapon, item, modifiedGun)

            );
        });
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
        ModelOverrides.register(ModItems.ACE_OF_SPADES.get(),
                new ace_of_spades_animation());
        ModelOverrides.register(ModItems.FLINTLOCK.get(),
                new flintlock_animation());
        ModelOverrides.register(ModItems.FLINTLOCK_ROYAL.get(),
                new flintlock_royal_animation());
        ModelOverrides.register(ModItems.ENCORE.get(),
                new encore_animation());
        ModelOverrides.register(ModItems.MG42.get(),
                new mg42_animation());
        ModelOverrides.register(ModItems.NTW20.get(),
                new ntw20_animation());
        ModelOverrides.register(ModItems.BULLDOG.get(),
                new bulldog_animation());
        ModelOverrides.register(ModItems.HK433.get(),
                new hk433_animation());
        ModelOverrides.register(ModItems.MP18.get(),
                new mp18_animation());
        ModelOverrides.register(ModItems.AF6.get(),
                new af6_scope());

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
        ACEOFSPADESAnimationController.getInstance();
        FLINTLOCKAnimationController.getInstance();
        ENCOREAnimationController.getInstance();
        MG42AnimationController.getInstance();
        NTW20AnimationController.getInstance();
        BULLDOGAnimationController.getInstance();
        HK433AnimationController.getInstance();
        FLINTLOCKRAnimationController.getInstance();
        MP18AnimationController.getInstance();

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ARROW.get(), ArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BEAM.get(), BeamRender::new);

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.THROWABLE_SMOKE_GRENADE.get(), ThrowableItemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.THROWABLE_EXPLODE_GRENADE.get(), ThrowableItemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HOLY_GRENADE.get(), ThrowableItemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.DECOY_GRENADE.get(), NoRotGrenadeRenderer::new);

//        RenderingRegistry.registerEntityRenderingHandler(ModEntities.THROWABLE_EFFECT_GRENADE.get(), ThrowableItemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.EFFECT_CLOUD.get(), AreaEffectCloudRenderer::new);

        LRKeys.init();
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.obj15Block.get(), RenderType.cutout());
        });
    }
}
