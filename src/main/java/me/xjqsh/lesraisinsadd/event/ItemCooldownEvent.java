package me.xjqsh.lesraisinsadd.event;

import net.minecraft.item.Item;
import net.minecraft.network.play.server.SCooldownPacket;
import net.minecraft.util.CooldownTracker;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;

/**Called after the client receive the {@link SCooldownPacket} and remove the {@link CooldownTracker}*/
@OnlyIn(Dist.CLIENT)
public class ItemCooldownEvent extends Event {
    private final Item item;
    public ItemCooldownEvent(Item item){
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
