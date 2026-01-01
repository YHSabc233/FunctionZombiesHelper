package com.yhsabc233.fzh.gui.hud;

import com.yhsabc233.fzh.config.FzhConfig;
import com.yhsabc233.fzh.function.ShowSpawnTime;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.scoreboard.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.yhsabc233.fzh.util.StringUtils.getNumbersFromString;

public class ShowSpawnTimeHud {
    
    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
                sstRender(drawContext)
        );
    }
    
    public static void sstRender(DrawContext context) {
        if (FzhConfig.CONFIG.isEnabled && FzhConfig.CONFIG.sstSwitch) {
	        MinecraftClient client = MinecraftClient.getInstance();
	        TextRenderer renderer = client.textRenderer;
	        int screenWidth = client.getWindow().getScaledWidth();
	        int screenHeight = client.getWindow().getScaledHeight();
	        int x = FzhConfig.CONFIG.sstDisplayX;
	        int y = ((screenHeight - FzhConfig.CONFIG.sstDisplayY) / 2);
	        if (client.world == null) {
	            return;
	        }
	        if (client.player == null) {
	            return;
	        }
	        List<AbstractClientPlayerEntity> players = client.world.getPlayers();
	        Scoreboard scoreboard = client.world.getScoreboard();
	        ScoreboardObjective gameScoreboard = scoreboard.getNullableObjective("game.scoreboard");
	        try {
	            if (gameScoreboard == null) {
	                return;
	            }
	            
	            Team gameRoundScoreboardTeam = gameScoreboard.getScoreboard().getTeam("game.scoreboard_line1");
	            Team gameLeftZombiesTeam = gameScoreboard.getScoreboard().getTeam("game.scoreboard_line2");
	            Team gameMapScoreboardTeam = gameScoreboard.getScoreboard().getTeam("game.scoreboard_line4");
	            Team gamePlayersTeam = gameScoreboard.getScoreboard().getTeam("game.player");
	            
	            List<Team> gamePlayerTeams = gameScoreboard.getScoreboard().getTeams().stream().toList();
	            if (gameRoundScoreboardTeam == null) {
	                return;
	            }
	            if (gameMapScoreboardTeam == null) {
	                return;
	            }
	            if (gameLeftZombiesTeam == null) {
	                return;
	            }
	            if (gamePlayersTeam == null) {
	                return;
	            }
	            
	            String gameRound = StringUtils.substringBetween(gameRoundScoreboardTeam.getPrefix().getString().formatted(Formatting.RESET), "第", "回");
	            String gameMap = gameMapScoreboardTeam.getSuffix().getString().formatted(Formatting.WHITE);
	            String gameRoundInfo = ShowSpawnTime.getRoundInfo(getNumbersFromString(gameRound));
	            String gameLeftZombies = gameLeftZombiesTeam.getSuffix().getString();
						
						MultilineText displayText = MultilineText.create(renderer, Text
						.of("当前回合：").copy()
						.append(gameRound)
						.append(" |\n")
						.append("当前游戏地图：")
						.append(gameMap)
						.append(" |\n")
						.append("回合信息：")
						.append(gameRoundInfo)
						.append(" |\n")
						.append("剩余僵尸：")
						.append(gameLeftZombies));
						
						displayText.drawWithShadow(context, x, y, FzhConfig.CONFIG.textMargin, 0xFFFFFF);
	        } catch (Exception ignored) {
	        } //为什么就上面这一行代码解决了游戏结束崩溃问题？？？
        }
    }
}
