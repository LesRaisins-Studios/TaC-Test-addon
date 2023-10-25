package me.xjqsh.lesraisinsadd.client.render;

import com.tac.guns.client.gunskin.IModelComponent;

public enum LrModelComponent implements IModelComponent {
    BULLETS("bullets"),
    LOADER("loader");
    public final String key;
    LrModelComponent(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
