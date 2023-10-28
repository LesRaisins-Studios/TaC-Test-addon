package me.xjqsh.lesraisinsadd.client.render;

import com.tac.guns.client.gunskin.IModelComponent;

public enum LrModelComponent implements IModelComponent {
    BULLETS("bullets"),
    LOADER("loader"),
    ARM_L("arm_left"),
    ARM_R("arm_right"),
    STRING_L("string_left"),
    STRING_R("string_right"),
    WHEEL_L("wheel_left"),
    WHEEL_R("wheel_right"),
    ARROW("arrow");
    public final String key;
    LrModelComponent(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
