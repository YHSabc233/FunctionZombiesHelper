package com.yhsabc233.fzh.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.yhsabc233.fzh.gui.screen.FzhConfigScreen;

public class FzhConfigModMenuApi implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return FzhConfigScreen::new;
    }
}
