package top.yhsabc233.fzh.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.config.FzhConfigManager;
import top.yhsabc233.fzh.config.FzhConfigYaclApi;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;
import top.yhsabc233.fzh.util.FEEDBACK_TYPE;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static top.yhsabc233.fzh.util.PlayerUtils.sendSimpleChatFeedback;
import static top.yhsabc233.fzh.util.PlayerUtils.sendUnkownChatFeedback;

public class FzhCommand {
	public static void register() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			var client = MinecraftClient.getInstance();
			
			LiteralCommandNode<FabricClientCommandSource> fzhConfigNode =
				literal("fzhconfig")
					.executes(context -> {
						client.send(() -> client.setScreen(FzhConfigYaclApi.createScreen(client.currentScreen)));
						return 1;
					}).build();
			
			LiteralCommandNode<FabricClientCommandSource> fzhHudNode =
				literal("fzhhud")
					.executes(context -> {
						client.send(() -> client.setScreen(new PositionModifyScreen(client.currentScreen)));
						return 1;
					}).build();
			
			dispatcher.getRoot().addChild(fzhConfigNode);
			dispatcher.getRoot().addChild(fzhHudNode);
			
			dispatcher.register(literal("fzh")
				.executes(context -> {
					sendUnkownChatFeedback(context);
					return 1;
				})
				
				.then(literal("set")
					.executes(context -> {
						sendUnkownChatFeedback(context);
						return 1;
					})
					
					.then(literal("colorScheme")
						.then(argument("colorScheme", StringArgumentType.word())
							.suggests((context, builder) -> builder
								.suggest("both")
								.suggest("icon")
								.suggest("text")
								.buildFuture())
							.executes(context -> {
								FzhConfig.CONFIG.colorScheme = StringArgumentType.getString(context, "colorScheme");
								FzhConfigManager.saveConfig();
								sendSimpleChatFeedback(context, Text.of("已将配色方案改为：" + StringArgumentType.getString(context, "colorScheme")), FEEDBACK_TYPE.SUCCESS);
								return 1;
							})
						)
					)
					
					.then(literal("valueBeforeName")
						.then(argument("valueBeforeName", StringArgumentType.word())
							.suggests((context, builder) -> builder
								.suggest("true")
								.suggest("false")
								.buildFuture())
							.executes(context -> {
								FzhConfig.CONFIG.valueBeforeName = Boolean.parseBoolean(StringArgumentType.getString(context, "valueBeforeName"));
								FzhConfigManager.saveConfig();
								sendSimpleChatFeedback(context, Text.of("已将数值是否显示在名称前开关设为：" + StringArgumentType.getString(context, "valueBeforeName")), FEEDBACK_TYPE.SUCCESS);
								return 1;
							})
						)
					)
					
					.then(literal("globalSwitch")
						.then(argument("globalSwitch", StringArgumentType.word())
							.suggests((context, builder) -> builder
								.suggest("true")
								.suggest("false")
								.buildFuture())
							.executes(context -> {
								FzhConfig.CONFIG.globalSwitch = Boolean.parseBoolean(StringArgumentType.getString(context, "globalSwitch"));
								FzhConfigManager.saveConfig();
								sendSimpleChatFeedback(context, Text.of("已将模组全局开关设为：" + StringArgumentType.getString(context, "globalSwitch")), FEEDBACK_TYPE.SUCCESS);
								return 1;
							})
						)
					)
				)
			);
			
			
		});
	}
	
}
