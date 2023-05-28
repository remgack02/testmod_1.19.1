package net.remgack.testmod.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;
import net.remgack.testmod.client.ClientHighData;

import java.util.function.Supplier;

public class HighDataSyncS2CPacket {
    private final int high;
    private static final String MESSAGE_SMOKE_DOPE = "message.testmod.smoke_dope";
    private static final String MESSAGE_NO_DOPE = "message.testmod.no_dope";

    public HighDataSyncS2CPacket(int high) {
            this.high = high;
        }

    public HighDataSyncS2CPacket(FriendlyByteBuf buf) {
            this.high = buf.readInt();
        }

        public void toBytes(FriendlyByteBuf buf) {
            buf.writeInt(high);
        }

        public boolean handle(Supplier<NetworkEvent.Context> supplier) {
            NetworkEvent.Context context = supplier.get();
            context.enqueueWork(() -> {
                // HERE WE ARE ON THE CLIENT!
                ClientHighData.set(high);
            });
            return true;
        }

}