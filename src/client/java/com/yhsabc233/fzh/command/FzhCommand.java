package com.yhsabc233.fzh.command;

import com.yhsabc233.fzh.gui.screen.FzhConfigScreen;
import com.yhsabc233.fzh.gui.screen.PositionModifyScreen;
import com.yhsabc233.fzh.util.PlayerUtils;
import net.fabricmc.fabric.api.client.command.v2.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class FzhCommand{
	public static void init(){
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			// TODO: 重构 /fzh 指令功能。
			/*dispatcher.register(
				literal("fzh")
					.executes(context -> {
						client.send(() -> {
							PlayerUtils.sendFeedback(context, Text.of("你好，这是一条测试命令， \n§a换行测试。" + Formatting.RED + "233").copy(), true, true);
						});
						return 1;
					})
			);*/
			
			dispatcher.register(
				literal("fzhconfig")
					.executes(context -> {
						client.send(() -> {
							client.setScreen(new FzhConfigScreen(client.currentScreen));
						});
						return 1;
					})
			);
			
			dispatcher.register(
				literal("fzhhud")
					.executes(context -> {
						client.send(() -> {
							client.setScreen(new PositionModifyScreen(client.currentScreen));
						});
						return 1;
					})
			);
			
		});
	}
}
