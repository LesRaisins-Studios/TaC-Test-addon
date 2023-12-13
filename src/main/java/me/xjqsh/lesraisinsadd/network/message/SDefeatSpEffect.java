package me.xjqsh.lesraisinsadd.network.message;

import me.xjqsh.lesraisinsadd.client.listener.ClientPlayHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SDefeatSpEffect {
    private final double x;
    private final double y;
    private final double z;

    public SDefeatSpEffect(Vector3d pos){
        this.x = pos.x();
        this.y = pos.y();
        this.z = pos.z();
    }

    public static void encode(SDefeatSpEffect msg,PacketBuffer buffer) {
        buffer.writeDouble(msg.x);
        buffer.writeDouble(msg.y);
        buffer.writeDouble(msg.z);

    }
    public static SDefeatSpEffect decode(PacketBuffer buffer) {
        return new SDefeatSpEffect(
               new Vector3d(
                       buffer.readDouble(),
                       buffer.readDouble(),
                       buffer.readDouble()
               )
        );
    }

    public static void handle(SDefeatSpEffect msg, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.setPacketHandled(true);
        context.enqueueWork(()-> {
            ClientPlayHandler.handleSpEffect(msg);
        });
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
