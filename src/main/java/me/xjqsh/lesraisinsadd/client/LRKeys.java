package me.xjqsh.lesraisinsadd.client;

import com.tac.guns.client.TacKeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class LRKeys {
    public static final TacKeyMapping MORE_EFFECT_HOLD =
            new TacKeyMapping.TacKeyBuilder( "key.lesraisinsadd.more_effect_hold" )
                    .withKeyboardKey( GLFW.GLFW_KEY_LEFT_CONTROL )
                    .withConflictContext( KeyConflictContext.GUI )
                    .buildAndRegis();

    public static void init(){}
}
