package net.remgack.testmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TEST_TAB = new CreativeModeTab("test_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TEST_ITEM.get());
        }
    };
}
