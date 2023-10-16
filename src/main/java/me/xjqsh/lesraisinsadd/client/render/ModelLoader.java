package me.xjqsh.lesraisinsadd.client.render;

import com.tac.guns.client.gunskin.SkinLoader;
import me.xjqsh.lesraisinsadd.init.ModItems;

import static com.tac.guns.client.gunskin.ModelComponent.*;

public class ModelLoader {
    public static SkinLoader HCAR = new SkinLoader(ModItems.HCAR.getId(),
            BODY,MAG_STANDARD,MAG_EXTENDED,PULL);
    public static SkinLoader P90 = new SkinLoader(ModItems.P90.getId(),
            BODY,MAG_STANDARD,MUZZLE_SILENCER,MUZZLE_BRAKE,MUZZLE_COMPENSATOR);
    public static SkinLoader PP19 = new SkinLoader(ModItems.PP19.getId(),
            BODY,MAG_STANDARD,BOLT);
    public static SkinLoader PPK20 = new SkinLoader(ModItems.PPK20.getId(),
            BODY,MAG_STANDARD,BOLT);
    public static SkinLoader SVD = new SkinLoader(ModItems.SVD.getId(),
            BODY,MAG_STANDARD,BOLT,RAIL_SCOPE);
    public static void init(){
        SkinLoader.register(ModItems.HCAR.getId(),HCAR);
        SkinLoader.register(ModItems.P90.getId(),P90);
        SkinLoader.register(ModItems.PP19.getId(),PP19);
        SkinLoader.register(ModItems.PPK20.getId(),PPK20);
        SkinLoader.register(ModItems.SVD.getId(),SVD);
    }
}
