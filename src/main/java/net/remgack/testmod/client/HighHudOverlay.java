package net.remgack.testmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.remgack.testmod.TestMod;

public class HighHudOverlay {
    private static final ResourceLocation FILLED_HIGH = new ResourceLocation(TestMod.MOD_ID,
            "textures/high/filled_high.png");
    private static final ResourceLocation EMPTY_HIGH = new ResourceLocation(TestMod.MOD_ID,
            "textures/high/empty_high.png");

    public static final IGuiOverlay HUD_HIGH = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_HIGH);
        for(int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack,x - 94 + (i * 9), y - 50,0,0,8,8,
                    8,8);
        }

        RenderSystem.setShaderTexture(0, FILLED_HIGH);
        for(int i = 0; i < 10; i++) {
            if(ClientHighData.getPlayerHigh() > i) {
                GuiComponent.blit(poseStack,x - 94 + (i * 9),y - 50,0,0,8,8,
                        8,8);
            } else {
                break;
            }
        }
    });
}
