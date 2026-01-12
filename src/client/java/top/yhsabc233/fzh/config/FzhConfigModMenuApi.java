package top.yhsabc233.fzh.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

/// <summary>
/// 针对 {@link com.terraformersmc.modmenu.ModMenu} 使用的 Config API。
/// </summary>
public class FzhConfigModMenuApi implements ModMenuApi {
    /*@Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return FzhConfigScreen::new;
    }*/
	
	@Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return FzhConfigYACLApi::createScreen;
    }
}
