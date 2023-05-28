package net.remgack.testmod.world.feature;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.remgack.testmod.TestMod;
import net.remgack.testmod.block.ModBlocks;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, TestMod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TEST_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TEST_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TEST_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_TEST_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.ENDSTONE_TEST_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_TEST_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHERRACK_TEST_ORE.get().defaultBlockState())));


    public static final RegistryObject<ConfiguredFeature<?, ?>> TEST_ORE = CONFIGURED_FEATURES.register("test_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_TEST_ORES.get(),7)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> END_TEST_ORE = CONFIGURED_FEATURES.register("end_test_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_TEST_ORES.get(), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_TEST_ORE = CONFIGURED_FEATURES.register("nether_test_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_TEST_ORES.get(), 9)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}