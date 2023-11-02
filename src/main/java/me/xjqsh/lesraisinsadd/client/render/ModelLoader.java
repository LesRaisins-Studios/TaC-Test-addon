package me.xjqsh.lesraisinsadd.client.render;

import com.tac.guns.client.gunskin.SkinLoader;
import me.xjqsh.lesraisinsadd.init.ModItems;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;

public class ModelLoader {
    public static SkinLoader P90 = new SkinLoader(ModItems.P90.getId(),
            BODY,MAG_STANDARD,BOLT,
            MUZZLE_SILENCER,MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT);
    public static SkinLoader PP19 = new SkinLoader(ModItems.PP19.getId(),
            BODY,MAG_STANDARD,BOLT);
    public static SkinLoader PPK20 = new SkinLoader(ModItems.PPK20.getId(),
            BODY,MAG_STANDARD,BOLT);
    public static SkinLoader SVD = new SkinLoader(ModItems.SVD.getId(),
            BODY,MAG_STANDARD,BOLT,RAIL_SCOPE);
    public static SkinLoader AUG = new SkinLoader(ModItems.AUG.getId(),
            BODY,MAG_STANDARD,MAG_EXTENDED,BOLT,
            MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT,MUZZLE_SILENCER,
            SIGHT,SIGHT_FOLDED);
    public static SkinLoader QSZ92 = new SkinLoader(ModItems.QSZ92.getId(),
            BODY,MAG_STANDARD,SLIDE);
    public static SkinLoader ANGLE = new SkinLoader(ModItems.ANGEL.getId(),
            BODY,MAG_STANDARD,LOADER,BULLETS,HAMMER);
    public static SkinLoader SA58 = new SkinLoader(ModItems.SA58.getId(),
            BODY,HANDLE,GRIP_LIGHT,GRIP_TACTICAL,MAG_STANDARD,MAG_EXTENDED,
            MUZZLE_SILENCER,MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT);
    public static SkinLoader MARLIN_1895 = new SkinLoader(ModItems.MARLIN_1895.getId(),
            BODY,BOLT,BULLET,GRIP_LIGHT,GRIP_TACTICAL,HAMMER,LEVER,
            MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_SILENCER,
            RAIL_DEFAULT,RAIL_EXTENDED,
            STOCK_DEFAULT,STOCK_HEAVY,STOCK_LIGHT,STOCK_TACTICAL
            );

    public static SkinLoader CROSS_BOW = new SkinLoader(ModItems.CROSSBOW.getId(),
            BODY,ARM_L,ARM_R,STRING_L,STRING_R,WHEEL_L,WHEEL_R,ARROW);

    public static void init(){
        SkinLoader.register(ModItems.P90.getId(),P90);
        SkinLoader.register(ModItems.PP19.getId(),PP19);
        SkinLoader.register(ModItems.PPK20.getId(),PPK20);
        SkinLoader.register(ModItems.SVD.getId(),SVD);
        SkinLoader.register(ModItems.AUG.getId(),AUG);
        SkinLoader.register(ModItems.QSZ92.getId(),QSZ92);
        SkinLoader.register(ModItems.ANGEL.getId(),ANGLE);
        SkinLoader.register(ModItems.SA58.getId(),SA58);
        SkinLoader.register(ModItems.CROSSBOW.getId(), CROSS_BOW);
        SkinLoader.register(ModItems.MARLIN_1895.getId(),MARLIN_1895);
    }
}
