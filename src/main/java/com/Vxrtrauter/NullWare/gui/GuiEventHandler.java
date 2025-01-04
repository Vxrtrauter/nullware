package com.Vxrtrauter.NullWare.gui;

import com.Vxrtrauter.NullWare.brand.BrandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiEventHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final int BUTTON_HEIGHT = 20;
    private static final int PADDING = 4;

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        GuiScreen gui = event.gui;

        if (gui instanceof GuiMultiplayer) {
            String buttonText = BrandManager.getBrandButtonText();
            int buttonWidth = mc.fontRendererObj.getStringWidth(buttonText) + PADDING * 2;

            GuiButton brandButton = new GuiButton(99, 10, 10, buttonWidth, BUTTON_HEIGHT, buttonText);
            event.buttonList.add(brandButton);
        }
    }

    @SubscribeEvent
    public void onActionPerformed(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.button.id == 99 && event.gui instanceof GuiMultiplayer) {
            if (BrandManager.getCurrentBrand().equals("custom")) {
                mc.displayGuiScreen(new GuiCustomBrand(mc.currentScreen));
            } else {
                BrandManager.cycleNextBrand();
                updateButtonText(event.button);
            }
        }
    }


    private void updateButtonText(GuiButton button) {
        String newText = BrandManager.getBrandButtonText();
        button.displayString = newText;
        button.width = mc.fontRendererObj.getStringWidth(newText) + PADDING * 2;
    }
}
