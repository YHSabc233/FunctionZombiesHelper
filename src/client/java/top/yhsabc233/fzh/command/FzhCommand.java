package top.yhsabc233.fzh.command;

import top.yhsabc233.fzh.gui.screen.FzhConfigScreen;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;
import top.yhsabc233.fzh.util.PlayerUtils;
import net.fabricmc.fabric.api.client.command.v2.*;
import net.minecraft.client.MinecraftClient;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class FzhCommand{
	public static void init(){
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			/* TODO: 重构 /fzh 指令功能。
				完成情况：
				↓ + /fzh
				help    Y
				set     N
				switch  N
				beta    N
			*/
			dispatcher.register(
				literal("fzh")
					.executes(context -> {
						client.send(() -> PlayerUtils.sendFeedbackUseFzhHelp(context));
						return 0;
					})
				
					.then(literal("help")
						.executes(context -> {
							client.send(() -> PlayerUtils.sendFzhHelpFeedback(context));
							return 0;
						})
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
