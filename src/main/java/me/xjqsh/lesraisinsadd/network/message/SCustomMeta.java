package me.xjqsh.lesraisinsadd.network.message;

import com.google.gson.JsonElement;
import me.xjqsh.lesraisinsadd.common.data.IMetaHolder;
import me.xjqsh.lesraisinsadd.common.data.NetworkDataManager;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SCustomMeta {
    private Map<ResourceLocation, JsonElement> metaMap;

    public SCustomMeta(Map<ResourceLocation, JsonElement> metaMap){
        this.metaMap = metaMap;
    }

    public void encode(PacketBuffer buffer) {
//        Map<ResourceLocation, JsonElement> metaMap = NetworkDataManager.getInstance().getMetaMap();
        buffer.writeInt(metaMap.size());
        for(Map.Entry<ResourceLocation, JsonElement> e : metaMap.entrySet()){
            buffer.writeResourceLocation(e.getKey());
            buffer.writeUtf(e.getValue().toString());
        }
    }
    public static SCustomMeta decode(PacketBuffer buffer) {
        int length = buffer.readInt();
        Map<ResourceLocation, JsonElement> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            ResourceLocation rl = buffer.readResourceLocation();
            String str = buffer.readUtf();
            JsonElement element = JSONUtils.parse(str);
            map.put(rl,element);
        }
        return new SCustomMeta(map);
    }

    public static void handle(SCustomMeta msg, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(()-> {
            for(Map.Entry<ResourceLocation, JsonElement> e : msg.metaMap.entrySet()){
                ResourceLocation rl = e.getKey();
                Item item = ForgeRegistries.ITEMS.getValue(rl);
                if(item instanceof IMetaHolder){
                    ((IMetaHolder<?>) item).loadFromJson(NetworkDataManager.GSON,e.getValue());
                }
            }
        });

        context.setPacketHandled(true);
    }

}
