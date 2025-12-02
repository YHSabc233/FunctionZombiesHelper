package com.yhsabc233.fzh.hud;

import com.yhsabc233.fzh.config.FzhConfigManager;
import com.yhsabc233.fzh.function.ShowSpawnTime;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.scoreboard.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static com.yhsabc233.fzh.utils.StringUtils.getNumbersFromString;

public class ShowSpawnTimeHud {
    public static List<Object> gamePlayers;
    
    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
                sstRender(drawContext)
        );
    }
    
    public static void sstRender(DrawContext context) {
        if (FzhConfigManager.CONFIG.isEnabled) {
            if (FzhConfigManager.CONFIG.sstSwitch) {
                MinecraftClient client = MinecraftClient.getInstance();
                TextRenderer renderer = client.textRenderer;
                int shown = 0;
                int screenWidth = client.getWindow().getScaledWidth();
                int screenHeight = client.getWindow().getScaledHeight();
                int x = (screenWidth - FzhConfigManager.CONFIG.hpdpDisplayX) / 2;
                int y = ((screenHeight - FzhConfigManager.CONFIG.hpdpDisplayY) / 2) - 20;
                if (client.world == null) {
                    return;
                }
                if (client.player == null) {
                    return;
                }
                List<AbstractClientPlayerEntity> players = client.world.getPlayers();
                Scoreboard scoreboard = client.world.getScoreboard();
                
                try {
                    for (AbstractClientPlayerEntity player : players) {
                        ScoreboardObjective gameScoreboard = scoreboard.getNullableObjective("game.scoreboard");
                        if (gameScoreboard == null) {
                            return;
                        }
                        
                        Team gameRoundScoreboardTeam = gameScoreboard.getScoreboard().getTeam("game.scoreboard_line1");
                        Team gameLeftZombiesTeam = gameScoreboard.getScoreboard().getTeam("game.scoreboard_line2");
                        Team gameMapScoreboardTeam = gameScoreboard.getScoreboard().getTeam("game.scoreboard_line4");
                        Team gamePlayersTeam = gameScoreboard.getScoreboard().getTeam("game.player");
                        Team clientPlayerTeam = gameScoreboard.getScoreboard().getScoreHolderTeam(client.player.getName().getString());
                        if (gameRoundScoreboardTeam == null) {
                            return;
                        }
                        if (gameMapScoreboardTeam == null) {
                            return;
                        }
                        if (gameLeftZombiesTeam == null) {
                            return;
                        }
                        if (clientPlayerTeam == null) {
                            return;
                        }
                        
                        String gameRound = StringUtils.substringBetween(gameRoundScoreboardTeam.getPrefix().getString().formatted(Formatting.RESET), "第", "回");
                        String gameMap = gameMapScoreboardTeam.getSuffix().getString().formatted(Formatting.WHITE);
                        String gameRoundInfo = ShowSpawnTime.getRoundInfo(getNumbersFromString(gameRound));
                        String gameLeftZombies = gameLeftZombiesTeam.getSuffix().getString();
                        if (gamePlayersTeam == null) {
                            return;
                        }
                        gamePlayers = Arrays.stream(gamePlayersTeam.getPlayerList().toArray()).toList();
                        
                        Text displayText = Text.of("当前回合：").copy()
                                .append(gameRound)
                                .append(" | ")
                                .append("当前游戏地图：")
                                .append(gameMap)
                                .append(" | ")
                                .append("回合信息：")
                                .append(gameRoundInfo)
                                .append(" | ")
                                .append("剩余僵尸：")
                                .append(gameLeftZombies);
                        
                        if (FzhConfigManager.CONFIG.displayWhen == "GAME_STARTED") {
                            if (gamePlayers.contains(client.player.getName().getString())) {
                                //context.drawTextWithShadow(renderer, displayText, 50, 50, 0xFFFFFF);
                                context.drawTextWithShadow(renderer, displayText, x, y, 0xFFFFFF);
                            }
                        } else {
                            context.drawTextWithShadow(renderer, displayText, x, y, 0xFFFFFF);
                        }
                    }
                } catch (Exception ignored) {
                } //为什么就上面这一行代码解决了游戏结束崩溃问题？？？
            }
        }
    }
}
