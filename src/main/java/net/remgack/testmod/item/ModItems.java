package net.remgack.testmod.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.remgack.testmod.TestMod;
import net.remgack.testmod.block.ModBlocks;
import net.remgack.testmod.item.custom.BlueBerryCropBlock;
import net.remgack.testmod.item.custom.BluntItem;
import net.remgack.testmod.item.custom.EightBallItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));
    public static final RegistryObject<Item> RAW_TEST_ITEM = ITEMS.register("raw_test_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> BLUNT_ITEM = ITEMS.register("blunt_item",
            () -> new BluntItem(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB).stacksTo(8)));

    public static final RegistryObject<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP.get(),
                     new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).build())));

    public static final RegistryObject<Item> ROACH_ITEM = ITEMS.register("roach_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));
    public static final RegistryObject<Item> WEED_SEEDS = ITEMS.register("weed_seeds",
            () -> new ItemNameBlockItem(ModBlocks.WEED_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> WEED = ITEMS.register("weed",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB)));

    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball",
            () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.TEST_TAB).stacksTo(1)));
    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
}
