package me.xjqsh.lesraisinsadd.item.grenades.data;

import net.minecraft.network.PacketBuffer;

public class ExplodeGrenadeMeta extends ThrowableMeta {
    private float power = 1.0f;
    private float radius = 5.0f;

    @Override
    public void readBuffer(PacketBuffer buffer) {
        super.readBuffer(buffer);
        this.power = buffer.readFloat();
    }

    @Override
    public void writeBuffer(PacketBuffer buffer) {
        super.writeBuffer(buffer);
        buffer.writeFloat(power);
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }
}
