package me.xjqsh.lesraisinsadd.common;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import me.xjqsh.lesraisinsadd.LesRaisinsTaCAddon;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class NetworkDataManager extends ReloadListener<Map<IMetaHolder<?>, JsonElement>> {
    public NetworkDataManager(){}
    private static NetworkDataManager INSTANCE;

    public static NetworkDataManager getInstance() {
        return INSTANCE;
    }

    private static final Gson GSON = Util.make(() -> {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    });

    @Override
    protected Map<IMetaHolder<?>, JsonElement> prepare(IResourceManager resourceManager, IProfiler profiler) {
        LesRaisinsTaCAddon.LOGGER.info("Start loading custom data...");
        Map<IMetaHolder<?>, JsonElement> map = Maps.newHashMap();

        ForgeRegistries.ITEMS.getValues().stream().filter(item -> item instanceof IMetaHolder<?>).forEach((item -> {
            ResourceLocation id = item.getRegistryName();
            IMetaHolder<?> metaHolder = (ThrowableItem<?>) item;

            if(id != null) {
                ResourceLocation rl = new ResourceLocation(String.format("%s:%s/%s.json", id.getNamespace(),metaHolder.getPath(), id.getPath()));
                try (
                        IResource iresource = resourceManager.getResource(rl);
                        InputStream inputstream = iresource.getInputStream();
                        Reader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
                ) {
                    JsonElement jsonelement = JSONUtils.fromJson(GSON, reader, JsonElement.class);
                    if (jsonelement != null) {
                        map.put(metaHolder, jsonelement);
                    } else {
                        LesRaisinsTaCAddon.LOGGER.error("Couldn't load data file {} as it's null or empty",rl);
                    }
                } catch (IllegalArgumentException | IOException | JsonParseException e) {
                    LesRaisinsTaCAddon.LOGGER.error("Couldn't parse data file {}: {}", rl , e);
                }
            }

        }));
        return map;
    }

    @Override
    protected void apply(Map<IMetaHolder<?>, JsonElement> map, IResourceManager resourceManager, IProfiler profiler) {
        map.forEach(((item, jsonElement) -> {
            item.loadFromJson(GSON,jsonElement);
        }));
    }

    @SubscribeEvent
    public static void addReloadListenerEvent(AddReloadListenerEvent event) {
        NetworkDataManager manager = new NetworkDataManager();
        event.addListener(manager);
        NetworkDataManager.INSTANCE = manager;
    }
}
