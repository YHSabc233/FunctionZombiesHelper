package top.yhsabc233.fzh.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.scoreboard.ReadableScoreboardScore;
import net.minecraft.scoreboard.ScoreHolder;
import net.minecraft.scoreboard.ScoreboardObjective;

public class ScoreUtils {
	public static int playerStat(AbstractClientPlayerEntity player) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.world == null || client.player == null || client.getNetworkHandler() != null) {
			return 1;
		}
		
		ScoreHolder scoreHolder = ScoreHolder.fromProfile(player.getGameProfile());
		
		ScoreboardObjective statBoard = client.getNetworkHandler().getScoreboard().getNullableObjective("stat.player");
		
		if (statBoard == null) {
			return 1;
		}
		
		ReadableScoreboardScore score = statBoard.getScoreboard().getScore(scoreHolder, statBoard);
		if (score == null) {
			return 1;
		}
		
		return score.getScore();
	}
}
