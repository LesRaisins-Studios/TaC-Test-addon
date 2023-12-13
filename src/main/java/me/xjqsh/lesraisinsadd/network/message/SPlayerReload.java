package me.xjqsh.lesraisinsadd.network.message;

import me.xjqsh.lesraisinsadd.client.listener.ClientPlayHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SPlayerReload {
    private UUID uuid;
    public SPlayerReload(UUID uuid){
        this.uuid = uuid;
    }

    public static void encode(SPlayerReload msg, PacketBuffer buffer) {
        buffer.writeUUID(msg.uuid);
    }
    public static SPlayerReload decode(PacketBuffer buffer) {
        return new SPlayerReload(buffer.readUUID());
    }

    public static void handle(SPlayerReload msg, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.setPacketHandled(true);
        context.enqueueWork(()-> {
            ClientPlayHandler.handleReload(msg);
        });
    }

    public UUID getUUID() {
        return uuid;
    }
}
