package top.yhsabc233.fzh.function;

import net.minecraft.client.MinecraftClient;
import top.yhsabc233.fzh.config.FzhConfig;

public class ZombiesHologramFix {
	public static int latencyTick;
	
	public static void toggle() {
		var client = MinecraftClient.getInstance();
		
		if (client.getNetworkHandler() != null) {
			client.getNetworkHandler().sendChatCommand("trigger zhf");
			FzhConfig.CONFIG.zhfSwitch = !FzhConfig.CONFIG.zhfSwitch;
			latencyTick = 5;
		}
	}
	
}
