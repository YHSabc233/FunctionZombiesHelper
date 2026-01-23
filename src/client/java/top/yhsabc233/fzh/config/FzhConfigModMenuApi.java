package top.yhsabc233.fzh.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class FzhConfigModMenuApi implements ModMenuApi {
	@Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return FzhConfigYaclApi::createScreen;
    }
}
