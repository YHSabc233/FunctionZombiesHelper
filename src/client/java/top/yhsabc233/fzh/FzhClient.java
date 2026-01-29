package top.yhsabc233.fzh;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.yhsabc233.fzh.command.FzhCommand;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.config.FzhKeyBinding;
import top.yhsabc233.fzh.function.ZombiesHologramFix;
import top.yhsabc233.fzh.gui.hud.HealthDisplayHud;
import top.yhsabc233.fzh.gui.hud.ZombiesHologramFixHud;

public class FzhClient implements ClientModInitializer {
	public static final String MOD_ID = "yhs_fzh";
	public static final Logger LOGGER = LoggerFactory.getLogger("FZH");
	
	/// The Debug switch, if it's {@code true} will enable some special features for debug.
	public static final boolean DEBUG = true;
	/// is FZH working in beta version
	private static final boolean BETA = true;
	/// 是否已经提示过当前正在使用测试版本
	private boolean BETA_TIP_SHOWN = false;
	
	@Override
	public void onInitializeClient() {
		try {
			// 后端
			FzhKeyBinding.register();
			FzhCommand.register();
			FzhConfig.init();
			
			// 可视化
			HealthDisplayHud.register();
			ZombiesHologramFixHud.register();
			
			// 暂时仅用于提示模组是否处于测试版本
			ClientPlayConnectionEvents.JOIN.register((clientPlayNetworkHandler, packetSender, minecraftClient) -> {
				if (BETA && !BETA_TIP_SHOWN) {
					SystemToast.add(
						minecraftClient.getToastManager(),
						SystemToast.Type.NARRATOR_TOGGLE,
						Text.translatable("fzh.tips.usingbetaversion.title"),
						Text.translatable("fzh.tips.usingbetaversion.description")
					);
					BETA_TIP_SHOWN = true;
				}
			});
			
			// 暂时仅用于实现键位反馈
			ClientTickEvents.END_CLIENT_TICK.register(client -> {
				// 我不希望某个意外导致延迟变成负值然后ZHF爆炸 所以这里选择小于或等于 :(
				if (ZombiesHologramFix.latencyTick <= 0 && FzhKeyBinding.zhfKey.isPressed()) ZombiesHologramFix.toggle();
				if (ZombiesHologramFix.latencyTick > 0) ZombiesHologramFix.latencyTick--;
			});
			
			LOGGER.info("[FZH] Function Zombies Helper loaded successfully.");
		} catch (Exception exception) {
			LOGGER.error("[FZH] Failed to load Function Zombies Helper!");
			throw exception;
		}
	}
	
}