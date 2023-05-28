package net.remgack.testmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.remgack.testmod.TestMod;
import net.remgack.testmod.block.custom.JumpyBlock;
import net.remgack.testmod.item.ModCreativeModeTab;
import net.remgack.testmod.item.ModItems;
import net.remgack.testmod.item.custom.BlueBerryCropBlock;
import net.remgack.testmod.item.custom.ZirconLampBlock;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);

    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
        .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> JUMPY_BLOCK = registerBlock("jumpy_block",
            () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> BLUEBERRY_CROP = BLOCKS.register("blueberry_crop",
            () -> new BlueBerryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> WEED_CROP = BLOCKS.register("weed_crop",
            () -> new BlueBerryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> ZIRCON_LAMP= registerBlock("zircon_lamp",
            () -> new ZirconLampBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(ZirconLampBlock.LIT) ? 15 : 0)), ModCreativeModeTab.TEST_TAB);
    public static final RegistryObject<Block> WEIRD_BLOCK = registerBlock("weird_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.TEST_TAB);
      public static final RegistryObject<Block> TEST_ORE = registerBlock("test_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> DEEPSLATE_TEST_ORE = registerBlock("deepslate_test_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> NETHERRACK_TEST_ORE = registerBlock("netherrack_test_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> ENDSTONE_TEST_ORE = registerBlock("endstone_test_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModCreativeModeTab.TEST_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name,
                                                                            RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {

        BLOCKS.register(eventBus);
    }
}
