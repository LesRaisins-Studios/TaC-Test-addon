package me.xjqsh.lesraisinsadd.network.message;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSpawnBeamMsg {
    private final double x;
    private final double y;
    private final double z;
    private final float yRot;
    private final float xRot;

    public SSpawnBeamMsg(Vector3d pos, float yRot, float xRot){
        this.x = pos.x();
        this.y = pos.y();
        this.z = pos.z();
        this.yRot = yRot;
        this.xRot = xRot;
    }

    public static void encode(SSpawnBeamMsg msg, PacketBuffer buffer) {
        buffer.writeDouble(msg.x);
        buffer.writeDouble(msg.y);
        buffer.writeDouble(msg.z);
        buffer.writeFloat(msg.yRot);
        buffer.writeFloat(msg.xRot);
    }
    public static SSpawnBeamMsg decode(PacketBuffer buffer) {
        return new SSpawnBeamMsg(
               new Vector3d(
                       buffer.readDouble(),
                       buffer.readDouble(),
                       buffer.readDouble()
               ),
                buffer.readFloat(),
                buffer.readFloat()
        );
    }

    public static void handle(SSpawnBeamMsg msg, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.setPacketHandled(true);
        context.enqueueWork(()-> {

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
    public float getYRot() {
        return yRot;
    }
    public float getXRot() {
        return xRot;
    }
}
