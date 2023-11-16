package me.xjqsh.lesraisinsadd.client.render;

import com.tac.guns.client.gunskin.SkinLoader;
import me.xjqsh.lesraisinsadd.init.ModItems;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.LOADER;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.WHEEL_L;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.WHEEL_R;

public class ModelLoader {
    public static SkinLoader P90 = register(new SkinLoader(ModItems.P90.getId(),
            BODY,MAG_STANDARD,BOLT,
            MUZZLE_SILENCER,MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT));
    public static SkinLoader PP19 = register(new SkinLoader(ModItems.PP19.getId(),
            BODY,MAG_STANDARD,BOLT));
    public static SkinLoader PPK20 = register(new SkinLoader(ModItems.PPK20.getId(),
            BODY,MAG_STANDARD,BOLT));
    public static SkinLoader SVD = register(new SkinLoader(ModItems.SVD.getId(),
            BODY,MAG_STANDARD,BOLT,RAIL_SCOPE));
    public static SkinLoader AUG = register(new SkinLoader(ModItems.AUG.getId(),
            BODY,MAG_STANDARD,MAG_EXTENDED,BOLT,
            MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT,MUZZLE_SILENCER,
            SIGHT,SIGHT_FOLDED));
    public static SkinLoader QSZ92 = register(new SkinLoader(ModItems.QSZ92.getId(),
            BODY,MAG_STANDARD,SLIDE));
    public static SkinLoader ANGLE = register(new SkinLoader(ModItems.ANGEL.getId(),
            BODY,MAG_STANDARD,LOADER,BULLETS,HAMMER));
    public static SkinLoader SA58 = register(new SkinLoader(ModItems.SA58.getId(),
            BODY,HANDLE,GRIP_LIGHT,GRIP_TACTICAL,MAG_STANDARD,MAG_EXTENDED,
            MUZZLE_SILENCER,MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT));
    public static SkinLoader MARLIN_1895 = register(new SkinLoader(ModItems.MARLIN_1895.getId(),
            BODY,BOLT,BULLET,GRIP_LIGHT,GRIP_TACTICAL,HAMMER,LEVER,
            MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_SILENCER,
            RAIL_DEFAULT,RAIL_EXTENDED,
            STOCK_DEFAULT,STOCK_HEAVY,STOCK_LIGHT,STOCK_TACTICAL)
            );
    public static SkinLoader M202 = register(new SkinLoader(ModItems.M202.getId(),
            BODY,COVER_BACK,COVER_FRONT,ROCKET_EMPTY,ROCKET_FULL,
            ROCKET_HANDLE_EMPTY,ROCKET_HANDLE_FULL)
    );
    public static SkinLoader CROSS_BOW = register(new SkinLoader(ModItems.CROSSBOW.getId(),
            BODY,ARM_L,ARM_R,STRING_L,STRING_R,WHEEL_L,WHEEL_R,ARROW));
    public static SkinLoader LOK1 = register(new SkinLoader(ModItems.LOK1.getId(),
            BODY,BOLT,MAG_STANDARD,SIGHT,GRIP_MAG));

    public static SkinLoader THE_LAST_WORD = register(new SkinLoader(ModItems.THE_LAST_WORD.getId(),
            BODY,BULLET,HAMMER,MAG_1,MAG_2,MAG_3,SIGHT,SIGHT_LEFT,SIGHT_RIGHT));

    public static SkinLoader THE_FIRST_CURSE = register(new SkinLoader(ModItems.THE_FIRST_CURSE.getId(),
            BODY,BULLET,MAG_1,MAG_2,MAG_3));

    public static SkinLoader register(SkinLoader skinLoader){
        SkinLoader.register(skinLoader.getGun(),skinLoader);
        return skinLoader;
    }

    public static void init(){}
}
