package top.yhsabc233.fzh.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import top.yhsabc233.fzh.gui.screen.FzhConfigScreen;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;
import top.yhsabc233.fzh.util.PlayerUtils;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class FzhCommand {
	public static void init() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			/* 完成情况：
			/fzh + ↓
				Y help
				N set
				N switch
				N beta
			*/
			dispatcher.register(
				literal("fzh")
					.executes(context -> {
						PlayerUtils.sendUseFzhHelpFeedback(context);
						return 0;
					})
					
					.then(literal("help")
						.executes(context -> {
							PlayerUtils.sendFzhHelpFeedback(context);
							return 0;
						})
						
						.then(literal(""))
					)
			);
			
			dispatcher.register(
				literal("fzhconfig")
					.executes(context -> {
						client.send(() -> client.setScreen(new FzhConfigScreen(client.currentScreen)));
						return 0;
					})
			);
			
			dispatcher.register(
				literal("fzhhud")
					.executes(context -> {
						client.send(() -> client.setScreen(new PositionModifyScreen(client.currentScreen)));
						return 0;
					})
			);
			
		});
	}
}
