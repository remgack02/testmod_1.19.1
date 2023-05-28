package net.remgack.testmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.remgack.testmod.block.ModBlocks;
import net.remgack.testmod.item.ModItems;
import net.remgack.testmod.networking.ModMessages;
import net.remgack.testmod.villager.ModVillagers;
import net.remgack.testmod.world.feature.ModConfiguredFeatures;
import net.remgack.testmod.world.feature.ModPlacedFeatures;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TestMod.MOD_ID)
public class TestMod {
    //change
    public static final String MOD_ID = "testmod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public TestMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // register mid items
        ModItems.register(modEventBus);
        //register mod blocks
        ModBlocks.register(modEventBus);

        ModVillagers.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
            ModVillagers.registerPOIs();
        });
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {}
    }
}
