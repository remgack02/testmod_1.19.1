package net.remgack.testmod.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;
import net.remgack.testmod.high.PlayerHighProvider;
import net.remgack.testmod.networking.ModMessages;

import java.util.function.Supplier;

public class SmokeDopeC2SPacket {
    private static final String MESSAGE_SMOKE_DOPE = "message.testmod.smoke_dope";
    private static final String MESSAGE_NO_DOPE = "message.testmod.no_dope";

    public SmokeDopeC2SPacket() {

    }

    public SmokeDopeC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            if(hasWaterAroundThem(player, level, 2)) {
                // Notify the player that dope has been smoked
                player.sendSystemMessage(Component.translatable(MESSAGE_SMOKE_DOPE).withStyle(ChatFormatting.DARK_GREEN));
                // play the drinking sound
                level.playSound(null, player.getOnPos(), SoundEvents.SMOKER_SMOKE, SoundSource.PLAYERS,
                        0.5F, level.random.nextFloat() * 0.1F + 0.9F);

                // increase the water level / thirst level of player
                // Output the current thirst level
                player.getCapability(PlayerHighProvider.PLAYER_HIGH).ifPresent(high -> {
                    high.addHigh(1);
                    player.sendSystemMessage(Component.literal("Current High " + high.getHigh())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new HighDataSyncS2CPacket(high.getHigh()), player);
                });


            } else {
                // Notify the player that there is no water around!
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_DOPE).withStyle(ChatFormatting.RED));
                // Output the current thirst level
                player.getCapability(PlayerHighProvider.PLAYER_HIGH).ifPresent(high -> {
                    player.sendSystemMessage(Component.literal("Current High " + high.getHigh())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new HighDataSyncS2CPacket(high.getHigh()), player);
                });
            }
        });
        return true;
    }

    private boolean hasWaterAroundThem(ServerPlayer player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }
}