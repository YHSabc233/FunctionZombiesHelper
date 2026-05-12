package top.yhsabc233.fzh;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.yhsabc233.fzh.command.FzhCommand;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.config.FzhKeyBinding;
import top.yhsabc233.fzh.gui.hud.HealthDisplayHud;

public class FzhClient implements ClientModInitializer {
	public static final String MOD_ID = "yhs_fzh";
	public static final Logger LOGGER = LoggerFactory.getLogger("FZH");
	
	/// The Debug switch, if it's {@code true} will enable some special features for debug.
	public static final boolean DEBUG = true;
	
	@Override
	public void onInitializeClient() {
		// 后端
		FzhKeyBinding.register();
		FzhCommand.register();
		FzhConfig.init();
		
		// 可视化
		HealthDisplayHud.register();
		
		// 提示模组是否处于测试版本
		ClientPlayConnectionEvents.JOIN.register((clientPlayNetworkHandler, packetSender, minecraftClient) -> {
			if (FzhConfig.CONFIG.betaTipEnabled) {
				SystemToast.add(
					minecraftClient.getToastManager(),
					SystemToast.Type.NARRATOR_TOGGLE,
					Text.translatable("fzh.tips.usingbetaversion.title"),
					Text.translatable("fzh.tips.usingbetaversion.description")
				);
			}
		});
		
		LOGGER.info("[FZH] Function Zombies Helper loaded successfully.");
	}
	
}
