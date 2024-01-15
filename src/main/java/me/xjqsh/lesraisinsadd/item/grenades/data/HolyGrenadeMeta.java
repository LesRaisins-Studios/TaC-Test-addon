package me.xjqsh.lesraisinsadd.item.grenades.data;

import net.minecraft.network.PacketBuffer;

public class HolyGrenadeMeta extends ThrowableMeta {
    private boolean createBeam = false;
    private float hostileDamage = 20.0f;
    private float friendlyHeal = 12.0f;
    @Override
    public void readBuffer(PacketBuffer buffer) {
        super.readBuffer(buffer);
        this.createBeam = buffer.readBoolean();
        this.hostileDamage = buffer.readFloat();
        this.friendlyHeal = buffer.readFloat();
    }

    @Override
    public void writeBuffer(PacketBuffer buffer) {
        super.writeBuffer(buffer);
        buffer.writeBoolean(createBeam);
        buffer.writeFloat(hostileDamage);
        buffer.writeFloat(friendlyHeal);
    }

    public boolean createBeam() {
        return createBeam;
    }

    public float getHostileDamage() {
        return hostileDamage;
    }

    public float getFriendlyHeal() {
        return friendlyHeal;
    }
}
