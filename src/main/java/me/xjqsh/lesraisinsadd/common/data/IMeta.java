package me.xjqsh.lesraisinsadd.common.data;

import net.minecraft.network.PacketBuffer;

public interface IMeta {
    void writeBuffer(PacketBuffer buffer);

    void readBuffer(PacketBuffer buffer);
}
