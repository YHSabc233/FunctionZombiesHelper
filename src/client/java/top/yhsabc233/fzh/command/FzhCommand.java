package top.yhsabc233.fzh.command;

import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import top.yhsabc233.fzh.config.FzhConfigYaclApi;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;

public class FzhCommand {
	public static void register() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			var client = MinecraftClient.getInstance();
			
			LiteralCommandNode<FabricClientCommandSource> fzhConfigNode = ClientCommandManager
				.literal("fzhconfig")
				.executes(context -> {
					client.execute(() -> client.setScreen(FzhConfigYaclApi.createScreen(client.currentScreen)));
					return 1;
				})
				.build();
			
			LiteralCommandNode<FabricClientCommandSource> fzhHudNode = ClientCommandManager
				.literal("fzhhud")
				.executes(context -> {
					client.execute(() -> client.setScreen(new PositionModifyScreen(client.currentScreen)));
					return 1;
				})
				.build();
			
			dispatcher.getRoot().addChild(fzhConfigNode);
			dispatcher.getRoot().addChild(fzhHudNode);
		});
	}
	
}
