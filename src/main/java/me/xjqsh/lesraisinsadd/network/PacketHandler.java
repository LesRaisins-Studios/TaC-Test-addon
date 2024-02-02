package me.xjqsh.lesraisinsadd.network;


import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.network.message.SCustomMeta;
import me.xjqsh.lesraisinsadd.network.message.SDefeatSpEffect;
import me.xjqsh.lesraisinsadd.network.message.SPlayerReload;
import me.xjqsh.lesraisinsadd.network.message.SSpawnBeamMsg;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.FMLHandshakeHandler;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    public static final String PROTOCOL_VERSION = "1";
    private static SimpleChannel playChannel;
    private static int nextMessageId = 0;

    public static void init() {
        playChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Reference.MOD_ID, "play"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();

        playChannel.messageBuilder(SCustomMeta.class,nextMessageId++)
                .encoder(SCustomMeta::encode)
                .decoder(SCustomMeta::decode)
                .consumer(SCustomMeta::handle)
                .add();


        playChannel.messageBuilder(SDefeatSpEffect.class,nextMessageId++)
                .encoder(SDefeatSpEffect::encode)
                .decoder(SDefeatSpEffect::decode)
                .consumer(SDefeatSpEffect::handle)
                .add();

        playChannel.messageBuilder(SPlayerReload.class,nextMessageId++)
                .encoder(SPlayerReload::encode)
                .decoder(SPlayerReload::decode)
                .consumer(SPlayerReload::handle)
                .add();
//        playChannel.messageBuilder(SSpawnBeamMsg.class,nextMessageId++)
//                .encoder(SSpawnBeamMsg::encode)
//                .decoder(SSpawnBeamMsg::decode)
//                .consumer(SSpawnBeamMsg::handle)
//                .add();
    }

    public static SimpleChannel getPlayChannel() {
        return playChannel;
    }
}
