package top.yhsabc233.fzh.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import top.yhsabc233.fzh.config.FzhConfigYaclApi;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class FzhCommand {
	public static void register() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			var client = MinecraftClient.getInstance();
			
			dispatcher.register(
				literal("fzhconfig")
					.executes(context -> {
						client.send(() -> client.setScreen(FzhConfigYaclApi.init(client.currentScreen)));
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
