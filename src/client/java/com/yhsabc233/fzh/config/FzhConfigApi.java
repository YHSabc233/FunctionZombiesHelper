package com.yhsabc233.fzh.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.OptionsScreen;

import java.util.Map;

public class FzhConfigApi implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return FzhConfigScreen::new;
    }
    
    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return Map.of("minecraft", parent -> new OptionsScreen(parent, MinecraftClient.getInstance().options));
    }
}
