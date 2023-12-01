package me.xjqsh.lesraisinsadd;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static class Client{
        public final ForgeConfigSpec.BooleanValue autoReload;
        private Client(ForgeConfigSpec.Builder builder) {
            builder.push("client");
            {
                autoReload = builder.comment("Reload gun automatically when empty.").define("autoReload",true);
            }
            builder.pop();
        }
    }

    public static final Client client;
    public static final ForgeConfigSpec clientSpec;

    static {
        final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);

        client = clientSpecPair.getLeft();
        clientSpec = clientSpecPair.getRight();
    }
}
