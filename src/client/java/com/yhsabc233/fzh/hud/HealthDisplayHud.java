package com.yhsabc233.fzh.hud;

import com.yhsabc233.fzh.config.FzhConfigManager;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class HealthDisplayHud {
    
    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
                hpdpRender(drawContext)
        );
    }
    
    public static void hpdpRender(DrawContext context) {
        if (FzhConfigManager.CONFIG.isEnabled) {
            MinecraftClient client = MinecraftClient.getInstance();
            TextRenderer renderer = client.textRenderer;
            if (client.world == null) return;
            List<AbstractClientPlayerEntity> players = client.world.getPlayers();
            
            if (client.player == null) return;
            
            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();
            
            int shown = 0;
            
            int x = 0;
            int y = 0;
            
            if (FzhConfigManager.CONFIG.position.contains("CUSTOM")) {
                x = (screenWidth - FzhConfigManager.CONFIG.hpdpDisplayX) / 2;
                y = (screenHeight - FzhConfigManager.CONFIG.hpdpDisplayY) / 2;
            }
            
            Formatting healthTextColor;
            Formatting healthIconColor;
            
            Formatting distanceTextColor;
            Formatting distanceIconColor;
            
            Formatting pingTextColor;
            Formatting pingIconColor;
            
            Formatting displayIconColor;
            Formatting displayTextColor;
            
            Text displayText;
            String displayIcon = " ? ";
            String displayTextType = String.format("%.0f", 0.0);
            
            if (!client.options.hudHidden || FzhConfigManager.CONFIG.alwaysDisplayed) {
                if (players != null) {
                    for (AbstractClientPlayerEntity player : players) {
                        
                        if (player.isSpectator() || shown >= FzhConfigManager.CONFIG.maxPlayersToShow) continue;
                        
                        float health = player.getHealth();
                        float distance = client.player.distanceTo(player);
                        
                        var networkHandler = MinecraftClient.getInstance().getNetworkHandler();
                        if (networkHandler == null) continue;
                        var entry = networkHandler.getPlayerListEntry(player.getUuid());
                        if (entry == null) continue;
                        float ping = entry.getLatency();
                        
                        switch (FzhConfigManager.CONFIG.colorScheme.toLowerCase()) {
                            case "both":
                                if (health >= 10) {
                                    healthTextColor = Formatting.GREEN;
                                    healthIconColor = Formatting.GREEN;
                                } else if (health >= 5) {
                                    healthTextColor = Formatting.YELLOW;
                                    healthIconColor = Formatting.YELLOW;
                                } else {
                                    healthTextColor = Formatting.RED;
                                    healthIconColor = Formatting.RED;
                                }
                                
                                if (distance < 10) {
                                    distanceTextColor = Formatting.GREEN;
                                    distanceIconColor = Formatting.GREEN;
                                } else if (distance < 50) {
                                    distanceTextColor = Formatting.YELLOW;
                                    distanceIconColor = Formatting.YELLOW;
                                } else {
                                    distanceTextColor = Formatting.RED;
                                    distanceIconColor = Formatting.RED;
                                }
                                
                                if (ping == 0) {
                                    pingTextColor = Formatting.GRAY;
                                    pingIconColor = Formatting.GRAY;
                                } else if (ping < 80) {
                                    pingTextColor = Formatting.GREEN;
                                    pingIconColor = Formatting.GREEN;
                                } else if (ping < 200) {
                                    pingTextColor = Formatting.YELLOW;
                                    pingIconColor = Formatting.YELLOW;
                                } else {
                                    pingTextColor = Formatting.RED;
                                    pingIconColor = Formatting.RED;
                                }
                                
                                break;
                            case "icon":
                                healthTextColor = Formatting.GREEN;
                                distanceTextColor = Formatting.GREEN;
                                pingTextColor = Formatting.GREEN;
                                
                                if (health >= 10) {
                                    healthIconColor = Formatting.GREEN;
                                } else if (health >= 5) {
                                    healthIconColor = Formatting.YELLOW;
                                } else {
                                    healthIconColor = Formatting.RED;
                                }
                                
                                if (distance < 10) {
                                    distanceIconColor = Formatting.GREEN;
                                } else if (distance < 50) {
                                    distanceIconColor = Formatting.YELLOW;
                                } else {
                                    distanceIconColor = Formatting.RED;
                                }
                                
                                if (ping == 0) {
                                    pingIconColor = Formatting.GRAY;
                                } else if (ping < 80) {
                                    pingIconColor = Formatting.GREEN;
                                } else if (ping < 200) {
                                    pingIconColor = Formatting.YELLOW;
                                } else {
                                    pingIconColor = Formatting.RED;
                                }
                                
                                break;
                            case "text":
                                healthIconColor = Formatting.RED;
                                distanceIconColor = Formatting.YELLOW;
                                pingIconColor = Formatting.GREEN;
                                
                                if (health >= 10) {
                                    healthTextColor = Formatting.GREEN;
                                } else if (health >= 5) {
                                    healthTextColor = Formatting.YELLOW;
                                } else {
                                    healthTextColor = Formatting.RED;
                                }
                                
                                if (distance < 20) {
                                    distanceTextColor = Formatting.GREEN;
                                } else if (distance < 35) {
                                    distanceTextColor = Formatting.YELLOW;
                                } else {
                                    distanceTextColor = Formatting.RED;
                                }
                                
                                if (ping == 0) {
                                    pingTextColor = Formatting.GRAY;
                                } else if (ping < 80) {
                                    pingTextColor = Formatting.GREEN;
                                } else if (ping < 200) {
                                    pingTextColor = Formatting.YELLOW;
                                } else {
                                    pingTextColor = Formatting.RED;
                                }
                                
                                break;
                            default:
                                healthTextColor = Formatting.GREEN;
                                healthIconColor = Formatting.RED;
                                
                                distanceTextColor = Formatting.GREEN;
                                distanceIconColor = Formatting.YELLOW;
                                
                                pingTextColor = Formatting.GREEN;
                                pingIconColor = Formatting.GREEN;
                                break;
                        }
                        
                        switch (FzhConfigManager.CONFIG.displayMode.toLowerCase()) {
                            case "hp":
                                displayIcon = " ❤ ";
                                displayTextType = String.format("%.0f", health);
                                displayIconColor = healthIconColor;
                                displayTextColor = healthTextColor;
                                break;
                            case "dist":
                                displayIcon = " 📏 ";
                                displayTextType = String.format("%.0f", distance);
                                displayIconColor = distanceIconColor;
                                displayTextColor = distanceTextColor;
                                break;
                            case "ping":
                                displayIcon = " 📶 ";
                                displayTextType = String.format("%.0fms", ping);
                                displayIconColor = pingIconColor;
                                displayTextColor = pingTextColor;
                                break;
                            default:
                                displayIconColor = Formatting.WHITE;
                                displayTextColor = Formatting.WHITE;
                                break;
                        }
                        
                        if (FzhConfigManager.CONFIG.valueBeforeName) {
                            displayText = Text.empty().copy()
                                    .append(displayIcon.formatted(displayIconColor))
                                    .append(displayTextType).copy().formatted(displayTextColor)
                                    .append(" ")
                                    .append(player.getName().copy().formatted(Formatting.WHITE)
                                    );
                        } else {
                            displayText = Text.empty().copy()
                                    .append(player.getName().copy().formatted(Formatting.WHITE))
                                    .append(" ")
                                    .append(displayTextType).copy().formatted(displayTextColor)
                                    .append(displayIcon.formatted(displayIconColor)
                                    );
                            
                        }
                        
                        if (FzhConfigManager.CONFIG.displayWhen == "GAME_STARTED") {
                            if (ShowSpawnTimeHud.gamePlayers.contains(client.player.getName().getString())) {
                                context.drawTextWithShadow(renderer, displayText, x, y, 0xFFFFFF);
                                y += FzhConfigManager.CONFIG.textMargin;
                                shown++;
                            }
                        } else {
                            context.drawTextWithShadow(renderer, displayText, x, y, 0xFFFFFF);
                            y += FzhConfigManager.CONFIG.textMargin;
                            shown++;
                        }
                    }
                }
            }
        }
    }
}
