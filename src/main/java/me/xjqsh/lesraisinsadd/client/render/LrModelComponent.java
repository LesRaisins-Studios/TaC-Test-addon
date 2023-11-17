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
    ARROW("arrow"),
    LEVER("lever"),
    COVER_BACK("cover_back"),
    COVER_FRONT("cover_front"),
    ROCKET_EMPTY("rocket_empty"),
    ROCKET_FULL("rocket_full"),
    ROCKET_HANDLE_EMPTY("rocket_handle_empty"),
    ROCKET_HANDLE_FULL("rocket_handle_full"),
    GRIP_MAG("grip_mag"),
    MAG_1("mag_1"),
    MAG_2("mag_2"),
    MAG_3("mag_3"),
    SIGHT_LEFT("sight_left"),
    SIGHT_RIGHT("sight_right"),
    BOTTLE("bottle"),
    STICK("stick"),
    PLATE("plate")
    ;
    public final String key;
    LrModelComponent(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
