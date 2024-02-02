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
    public static class Server{
        public final ForgeConfigSpec.BooleanValue virusSpread;
        private Server(ForgeConfigSpec.Builder builder) {
            builder.push("server");
            {
                virusSpread = builder.comment("Should virus effect will only spread among players(or all living entity). " +
                        "It won't change if entities directly in the grenade smoke get affected or not, and you should change that via datapack.").define("virusSpread",true);
            }
            builder.pop();
        }
    }

    public static final Client client;
    public static final ForgeConfigSpec clientSpec;
    public static final Server server;
    public static final ForgeConfigSpec serverSpec;

    static {
        final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
        final Pair<Server, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(Server::new);

        client = clientSpecPair.getLeft();
        clientSpec = clientSpecPair.getRight();

        server = serverSpecPair.getLeft();
        serverSpec = serverSpecPair.getRight();
    }
}
