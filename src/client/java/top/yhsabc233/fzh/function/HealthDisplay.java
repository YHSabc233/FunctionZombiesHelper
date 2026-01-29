package top.yhsabc233.fzh.function;

import net.minecraft.client.MinecraftClient;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.gui.hud.HealthDisplayHud;

public class HealthDisplay {
	private static final MinecraftClient client = MinecraftClient.getInstance();
	
	/// Should {@linkplain HealthDisplayHud} render?
	public static boolean shouldRender() {
		return (!client.options.hudHidden || FzhConfig.CONFIG.alwaysDisplayed) && !client.getDebugHud().shouldShowDebugHud() && FzhConfig.CONFIG.hpdpSwitch && FzhConfig.CONFIG.globalSwitch;
	}
	
}
