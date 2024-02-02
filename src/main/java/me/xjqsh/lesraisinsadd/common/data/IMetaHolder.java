package me.xjqsh.lesraisinsadd.common.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface IMetaHolder<T extends IMeta> {
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
        T meta = parse(gson,json);
        if(meta!=null) {
            setMeta(meta);
        }
    }

    default T parse(Gson gson, JsonElement json){
        return gson.fromJson(json,getMetaType());
    }

}
