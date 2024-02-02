package me.xjqsh.lesraisinsadd.common.data;

import com.google.common.collect.Maps;
import com.google.gson.*;
import me.xjqsh.lesraisinsadd.LesRaisinsTaCAddon;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.item.grenades.ThrowableItem;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.item.Item;
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
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class NetworkDataManager extends ReloadListener<Map<Item, JsonElement>> {
    public NetworkDataManager(){}
    private static NetworkDataManager INSTANCE;

    public static NetworkDataManager getInstance() {
        return INSTANCE;
    }
    public static final JsonDeserializer<ResourceLocation> RESOURCE_LOCATION = (json, typeOfT, context) -> new ResourceLocation(json.getAsString());
    public static final Gson GSON = Util.make(() -> {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ResourceLocation.class,RESOURCE_LOCATION);
        return builder.create();
    });

    public Map<ResourceLocation, JsonElement> getMetaMap() {
        return metaMap;
    }

    private Map<ResourceLocation, JsonElement> metaMap = new HashMap<>();

    @Override
    protected Map<Item, JsonElement> prepare(IResourceManager resourceManager, IProfiler profiler) {
        LesRaisinsTaCAddon.LOGGER.info("Start loading custom data...");
        Map<Item, JsonElement> map = Maps.newHashMap();

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
                        map.put(item, jsonelement);
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
    protected void apply(Map<Item, JsonElement> map, IResourceManager resourceManager, IProfiler profiler) {
        metaMap.clear();
        map.forEach(((item, jsonElement) -> {
            if(item instanceof IMetaHolder){
                ((IMetaHolder<?>) item).loadFromJson(GSON,jsonElement);
                metaMap.put(item.getRegistryName(),jsonElement);
            }
        }));
    }

    @SubscribeEvent
    public static void addReloadListenerEvent(AddReloadListenerEvent event) {
        NetworkDataManager manager = new NetworkDataManager();
        event.addListener(manager);
        NetworkDataManager.INSTANCE = manager;
    }
}
