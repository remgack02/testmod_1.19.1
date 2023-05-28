package net.remgack.testmod.event;

import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.remgack.testmod.TestMod;
import net.remgack.testmod.client.HighHudOverlay;
import net.remgack.testmod.networking.ModMessages;
import net.remgack.testmod.networking.packet.ExampleC2SPacket;
import net.remgack.testmod.networking.packet.SmokeDopeC2SPacket;
import net.remgack.testmod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = TestMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.SMOKING_KEY.consumeClick()) {
                ModMessages.sendToServer(new SmokeDopeC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = TestMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.SMOKING_KEY);
        }
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("high", HighHudOverlay.HUD_HIGH);
        }
    }

}