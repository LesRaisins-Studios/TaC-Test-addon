package me.xjqsh.exampletacaddon.client.render;

import com.tac.guns.client.gunskin.SkinLoader;
import me.xjqsh.exampletacaddon.init.ModItems;

import static com.tac.guns.client.gunskin.ModelComponent.*;

public class ModelLoader {
    public static SkinLoader HCAR = new SkinLoader(ModItems.HCAR.getId(),
            BODY,MAG_STANDARD,MAG_EXTENDED,PULL);
    public static void init(){
        SkinLoader.register(ModItems.HCAR.getId(),HCAR);
    }
}
