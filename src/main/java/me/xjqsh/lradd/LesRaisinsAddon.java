package me.xjqsh.lradd;

import com.mojang.logging.LogUtils;
import com.tacz.guns.api.resource.ResourceManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(LesRaisinsAddon.MOD_ID)
public class LesRaisinsAddon {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "lradd";
    public static final String DEFAULT_PACK_NAME = "lradd_default_gun";
    public static final Logger LOGGER = LogUtils.getLogger();


    public LesRaisinsAddon() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        registerDefaultExtraGunPack();
    }

    private static void registerDefaultExtraGunPack() {
        String jarDefaultPackPath = String.format("/assets/%s/custom/%s", MOD_ID, DEFAULT_PACK_NAME);
        ResourceManager.registerExtraGunPack(LesRaisinsAddon.class, jarDefaultPackPath);
    }
}
