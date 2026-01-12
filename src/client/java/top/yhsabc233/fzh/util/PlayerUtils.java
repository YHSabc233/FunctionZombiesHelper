package top.yhsabc233.fzh.util;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

/// <summary>
/// 针对 客户端玩家 使用的一系列工具。
/// </summary>
@SuppressWarnings("unused")
public class PlayerUtils {
	public static final Text FEEDBACK_PREFIX = Text.of("§r[§c§lFZH§r]");
	
	/// <summary>
	/// 发送仅 {@link MinecraftClient} 可见的聊天信息。
	/// </summary>
	public static void sendSimpleFeedback(CommandContext<FabricClientCommandSource> context, MutableText content, boolean soundEffect, boolean success) {
		Text message = FEEDBACK_PREFIX.copy().append(content);
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (player == null) return;
		
		if (soundEffect) {
			if (success) {
				player.playSound(
					SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(),
					1f,
					2f
				);
			} else {
				player.playSound(
					SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(),
					1f,
					1f
				);
			}
		}
		
		context.getSource().sendFeedback(message);
	}
}
