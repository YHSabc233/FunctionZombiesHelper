package top.yhsabc233.fzh.util;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

/// Some tools use to `client` player.
@SuppressWarnings("unused")
public class PlayerUtils {
	public static final Text FEEDBACK_PREFIX = Text.of("§r§f[§c§lFZH§r§f] ");
	
	public static void sendSimpleChatFeedback(CommandContext<FabricClientCommandSource> context, Text text, FEEDBACK_TYPE success) {
		Text message = FEEDBACK_PREFIX.copy().append(text);
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (player == null) return;
		
		if (success != FEEDBACK_TYPE.NONE) {
			if (success == FEEDBACK_TYPE.SUCCESS)
				player.playSound(
					SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(),
					1f,
					2f
				);
			else if (success == FEEDBACK_TYPE.FAILED)
				player.playSound(
					SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(),
					1f,
					1f
				);
		}
		
		context.getSource().sendFeedback(message);
	}
	
	public static void sendSimpleMuteChatFeedback(CommandContext<FabricClientCommandSource> context, Text text) { sendSimpleChatFeedback(context, text, FEEDBACK_TYPE.NONE); }
	
	/// Send a message about:<br> "Unkown command, please type /fzh help to get help."
	public static void sendUnkownChatFeedback(CommandContext<FabricClientCommandSource> context) { sendSimpleMuteChatFeedback(context, Text.translatable("fzh.message.unkown")); }
	
}
