package top.yhsabc233.fzh.util;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

/// Some tools use to Player
@SuppressWarnings("unused")
public class PlayerUtils {
	public static final Text FEEDBACK_PREFIX = Text.of("§r[§c§lFZH§r]");
	
	/// send a simple feedback for {@link MinecraftClient}
	public static void sendSimpleFeedback(CommandContext<FabricClientCommandSource> context, MutableText text, @Nullable Boolean soundEffect, @Nullable Boolean success) {
		Text message = FEEDBACK_PREFIX.copy().append(text);
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (player == null) return;
		
		if (soundEffect != null && soundEffect) {
			if (success != null && success) {
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
	
	/// send a simple feedback for {@link MinecraftClient}
	public static void sendSimpleFeedback(CommandContext<FabricClientCommandSource> context, MutableText text) {
		Text message = FEEDBACK_PREFIX.copy().append(text);
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (player == null) return;
		
		context.getSource().sendFeedback(message);
	}
}
