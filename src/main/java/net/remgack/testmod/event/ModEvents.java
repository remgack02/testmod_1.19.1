package net.remgack.testmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.LogicalSide;
import net.remgack.testmod.TestMod;
import net.remgack.testmod.high.PlayerHigh;
import net.remgack.testmod.item.ModItems;
import net.remgack.testmod.networking.ModMessages;
import net.remgack.testmod.networking.packet.HighDataSyncS2CPacket;
import net.remgack.testmod.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.remgack.testmod.high.PlayerHighProvider;

import java.util.List;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.EIGHT_BALL.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    stack,10,8,0.02F));
        }

        if(event.getType() == ModVillagers.JUMP_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BLUEBERRY.get(), 15);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack,10,8,0.02F));
        }

        if(event.getType() == ModVillagers.WEED_PLUG.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack blunt = new ItemStack(ModItems.BLUNT_ITEM.get(), 1);
            ItemStack weed = new ItemStack(ModItems.WEED.get(), 12);

            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    weed, 10, 8, 0.02F));

            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    blunt,10,8,0.02F));
        }

    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerHighProvider.PLAYER_HIGH).isPresent()) {
                event.addCapability(new ResourceLocation(TestMod.MOD_ID, "properties"), new PlayerHighProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerHighProvider.PLAYER_HIGH).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerHighProvider.PLAYER_HIGH).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerHigh.class);
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerHighProvider.PLAYER_HIGH).ifPresent(high -> {
                if(high.getHigh() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                    high.subHigh(1);
                    ModMessages.sendToPlayer(new HighDataSyncS2CPacket(high.getHigh()), ((ServerPlayer) event.player));
                }
            });
        }
    }
    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerHighProvider.PLAYER_HIGH).ifPresent(high -> {
                    ModMessages.sendToPlayer(new HighDataSyncS2CPacket(high.getHigh()), player);
                });
            }
        }
    }
}