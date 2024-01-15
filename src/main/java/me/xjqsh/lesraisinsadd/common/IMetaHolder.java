package me.xjqsh.lesraisinsadd.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface IMetaHolder<T> {
    void setMeta(T meta);
    T getMeta();
    String getPath();

    default Class<T> getMetaType() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }
        throw new RuntimeException();
    }
    default void loadFromJson(Gson gson, JsonElement json){
        T meta = gson.fromJson(json,getMetaType());
        setMeta(meta);
    }

}
