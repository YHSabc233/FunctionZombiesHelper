package top.yhsabc233.fzh.util;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class PlayerUtils{
	public static final Text FEEDBACK_PREFIX = Text.of("§r[§c§lFZH§r]");
	
	/// <summary>
	/// 发送仅 {@link MinecraftClient} 可见的聊天信息。
	/// </summary>
	public static void sendFeedback(CommandContext<FabricClientCommandSource> context, MutableText content, boolean hasSoundEffect, boolean isSuccess) {
		Text message = FEEDBACK_PREFIX.copy().append(content);
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		context.getSource().sendFeedback(message);
		
		if (hasSoundEffect) {
			if (player != null && isSuccess) {
				player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 1f, 2f);
			} else if (player != null) {
				player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), 1f, 1f);
			}
		}
	}
	
	public static void sendFeedbackUseFzhHelp(CommandContext<FabricClientCommandSource> context) {
		sendFeedback(context, Text.translatable("fzh.message.unknown").copy(), true, true);
	}
	
	public static void sendFzhHelpFeedback(CommandContext<FabricClientCommandSource> context) {
		sendFeedback(context, Text.translatable("fzh.message.help_0").copy(), true, true);
		sendFeedback(context, Text.translatable("fzh.message.help_1").copy(), false, false);
		sendFeedback(context, Text.translatable("fzh.message.help_2").copy(), false, false);
		sendFeedback(context, Text.translatable("fzh.message.help_3").copy(), false, false);
		sendFeedback(context, Text.translatable("fzh.message.help_4").copy(), false, false);
		sendFeedback(context, Text.translatable("fzh.message.help_5").copy(), false, false);
		sendFeedback(context, Text.translatable("fzh.message.help_6").copy(), false, false);
	}
}
