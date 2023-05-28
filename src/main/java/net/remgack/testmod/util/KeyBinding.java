package net.remgack.testmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_TEST = "key.category.testmod.test";
    public static final String KEY_SMOKE = "key.testmod.smoke";

    public static final KeyMapping SMOKING_KEY = new KeyMapping(KEY_SMOKE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_TEST);
}