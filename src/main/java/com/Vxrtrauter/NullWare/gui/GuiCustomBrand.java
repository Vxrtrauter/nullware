package com.Vxrtrauter.NullWare.gui;

import com.Vxrtrauter.NullWare.brand.BrandManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiCustomBrand extends GuiScreen {
    private final GuiScreen parentScreen;
    private GuiTextField brandInputField;

    public GuiCustomBrand(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.brandInputField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 10, 200, 20);
        this.brandInputField.setMaxStringLength(32);
        this.brandInputField.setText(BrandManager.getCurrentBrand());

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 + 20, 200, 20, I18n.format("gui.done")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            BrandManager.setCustomBrandName(this.brandInputField.getText());
            this.mc.displayGuiScreen(parentScreen);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen(parentScreen);
        } else {
            this.brandInputField.textboxKeyTyped(typedChar, keyCode);
        }
    }

    @Override
    public void updateScreen() {
        this.brandInputField.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Enter Custom Brand Name", this.width / 2, this.height / 2 - 30, 0xFFFFFF);
        this.brandInputField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.brandInputField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onGuiClosed() {
        BrandManager.setCustomBrandName(this.brandInputField.getText());
    }
}
