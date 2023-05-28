package net.remgack.testmod.networking;

import net.remgack.testmod.TestMod;
import net.remgack.testmod.networking.packet.HighDataSyncS2CPacket;
import net.remgack.testmod.networking.packet.SmokeDopeC2SPacket;
import net.remgack.testmod.networking.packet.ExampleC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;


public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(TestMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle)
                .add();

        net.messageBuilder(SmokeDopeC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SmokeDopeC2SPacket::new)
                .encoder(SmokeDopeC2SPacket::toBytes)
                .consumerMainThread(SmokeDopeC2SPacket::handle)
                .add();

        net.messageBuilder(HighDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(HighDataSyncS2CPacket::new)
                .encoder(HighDataSyncS2CPacket::toBytes)
                .consumerMainThread(HighDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}