package com.yhsabc233.fzh.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.text.Text;

public class FzhConfigScreen extends GameOptionsScreen {
    public FzhConfigScreen(Screen previous) {
        super(previous, MinecraftClient.getInstance().options, Text.translatable("fzh.screen.config.title"));
    }
    
    @Override
    protected void addOptions() {
    }
    
    @Override
    public void removed() {
        FzhConfigManager.saveConfig();
    }
}
