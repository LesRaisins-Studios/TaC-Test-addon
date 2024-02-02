package me.xjqsh.lesraisinsadd.client.render;

import com.tac.guns.client.gunskin.SkinLoader;
import me.xjqsh.lesraisinsadd.init.ModItems;

import static com.tac.guns.client.gunskin.ModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.*;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.LOADER;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.WHEEL_L;
import static me.xjqsh.lesraisinsadd.client.render.LrModelComponent.WHEEL_R;

@SuppressWarnings("unused")
public class ModelLoader {
    public static SkinLoader P90 = register(new SkinLoader(ModItems.P90.getId(),
            BODY,MAG_STANDARD,BOLT,
            MUZZLE_SILENCER,MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT));
    public static SkinLoader PP19 = register(new SkinLoader(ModItems.PP19.getId(),
            BODY,MAG_STANDARD,BOLT));
    public static SkinLoader PPK20 = register(new SkinLoader(ModItems.PPK20.getId(),
            BODY,MAG_STANDARD,BOLT));
    public static SkinLoader SVD = register(new SkinLoader(ModItems.SVD.getId(),
            BODY,MAG_STANDARD,BOLT,RAIL_SCOPE,BULLET));
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
    public static SkinLoader ACE_OF_SPADES = register(new SkinLoader(ModItems.ACE_OF_SPADES.getId(),
            BODY,BULLET,MAG_1,MAG_2,MAG_3,SIGHT_LEFT,SIGHT_RIGHT,SIGHT,SMOKE));

    public static SkinLoader FLINTLOCK = register(new SkinLoader(ModItems.FLINTLOCK.getId(),
            BODY,BOTTLE,BULLET,HAMMER,PLATE,STICK,
            GRIP_LIGHT,GRIP_TACTICAL,
            RAIL_DEFAULT,RAIL_EXTENDED,
            STOCK_DEFAULT,STOCK_HEAVY,STOCK_LIGHT,STOCK_TACTICAL));
    public static SkinLoader FLINTLOCK_ROYAL = register(new SkinLoader(ModItems.FLINTLOCK_ROYAL.getId(),
            BODY,BOTTLE,BULLET,HAMMER,PLATE,STICK));
    public static SkinLoader ENCORE = register(new SkinLoader(ModItems.ENCORE.getId(),
            BODY,BULLET,HAMMER,RAIL_DEFAULT,BULLET_HEAD));
    public static SkinLoader MG42 = register(new SkinLoader(ModItems.MG42.getId(),
            BODY,BOLT,BULLET_CHAIN,CAP,MAG_STANDARD));
    public static SkinLoader NTW20 = register(new SkinLoader(ModItems.NTW20.getId(),
            BODY,BOLT,BARREL,BULLET,MAG_STANDARD,RAIL_DEFAULT));
    public static SkinLoader BULLDOG = register(new SkinLoader(ModItems.BULLDOG.getId(),
            BODY,HAMMER,MAG_STANDARD,RAIL_DEFAULT));
    public static SkinLoader HK433 = register(new SkinLoader(ModItems.HK433.getId(),
            BODY,BOLT,HANDLE,SIGHT,SIGHT_FOLDED,
            MAG_STANDARD,MAG_EXTENDED,
            MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT,MUZZLE_SILENCER,
            STOCK_LIGHT,STOCK_HEAVY,STOCK_TACTICAL));
    public static SkinLoader MP18 = register(new SkinLoader(ModItems.MP18.getId(),
            BODY,BOLT,MAG_STANDARD,MAG_EXTENDED));
    public static SkinLoader X26 = register(new SkinLoader(ModItems.X26.getId(),
            BODY,MAG_STANDARD));
    public static SkinLoader BAM4 = register(new SkinLoader(ModItems.BAM4.getId(),
            BODY,BOLT,SIGHT,
            MAG_STANDARD,MAG_EXTENDED,
            MUZZLE_BRAKE,MUZZLE_COMPENSATOR,MUZZLE_DEFAULT,MUZZLE_SILENCER,
            STOCK_LIGHT,STOCK_TACTICAL));

    public static SkinLoader ALEX = register(new SkinLoader(ModItems.ALEX.getId(),
            BODY,W_HEAD,W_ARM_L,W_ARM_R,W_LEG_L,W_LEG_R));
    public static SkinLoader XM8 = register(new SkinLoader(ModItems.XM8.getId(),
            BODY,BOLT,MAG_STANDARD,PULL));
    public static SkinLoader M1892 = register(new SkinLoader(ModItems.M1892.getId(),
            BODY,HAMMER,BULLET,CYLINDER,BULLET_SHELL));

    public static SkinLoader register(SkinLoader skinLoader){
        SkinLoader.register(skinLoader.getGun(),skinLoader);
        return skinLoader;
    }

    public static void init(){}
}
