package com.yhsabc233.fzh.gui.hud;

import com.yhsabc233.fzh.FzhClient;
import com.yhsabc233.fzh.config.FzhConfig;

import com.yhsabc233.fzh.util.ScoreUtils;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class HealthDisplayHud {
	
	private static final Identifier EXAMPLE_LAYER = Identifier.of(FzhClient.MOD_ID, "hud-example-layer");
	
	public static void init() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
        hpdpRender(drawContext));
		
		HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> layeredDrawerWrapper.attachLayerBefore(IdentifiedLayer.CHAT, EXAMPLE_LAYER, HealthDisplayHud::testRender));
	}
	
	public static void testRender(DrawContext context, RenderTickCounter tickCounter) {
		int color = 0xFFFF0000; // Red
		int targetColor = 0xFF00FF00; // Green
		
		// You can use the Util.getMeasuringTimeMs() function to get the current time in milliseconds.
		// Divide by 1000 to get seconds.
		double currentTime = Util.getMeasuringTimeMs() / 1000.0;
		
		// "lerp" simply means "linear interpolation", which is a fancy way of saying "blend".
		float lerpedAmount = MathHelper.abs(MathHelper.sin((float) currentTime));
		int lerpedColor = ColorHelper.lerp(lerpedAmount, color, targetColor);
		
		// Draw a square with the lerped color.
		// x1, x2, y1, y2, z, color
		context.fill(0, 0, 10, 10, 0, lerpedColor);
	}
	
	public static Formatting displayIconColor;
	public static Formatting displayTextColor;
	
	public static Formatting playerNameColor;
	
	public static Text displayText;
	public static String displayIcon = " ❤ ";
	public static String displayTextType = "NaN";
	
	public static MinecraftClient client = MinecraftClient.getInstance();
	
    private static void hpdpRender(DrawContext context) {
        if (FzhConfig.CONFIG.isEnabled) {
	        
	        TextRenderer renderer = client.textRenderer;
	        if (client.world == null) return;
	        List<AbstractClientPlayerEntity> players = client.world.getPlayers();
	        
	        if (client.player == null) return;
	        
	        int screenWidth = client.getWindow().getScaledWidth();
	        int screenHeight = client.getWindow().getScaledHeight();
	        
	        int shown = 0;
	        
	        int x = 0;
	        int y = 0;
	        
	        if (FzhConfig.CONFIG.position.contains("CUSTOM")) {
		        //x = (screenWidth - FzhConfig.CONFIG.hpdpDisplayX) / 2;
		        //y = (screenHeight - FzhConfig.CONFIG.hpdpDisplayY) / 2;
		        x = FzhConfig.CONFIG.hpdpDisplayX;
		        y = FzhConfig.CONFIG.hpdpDisplayY + 5;
	        }
	        
	        Formatting healthTextColor;
	        Formatting healthIconColor;
	        
	        Formatting distanceTextColor;
	        Formatting distanceIconColor;
	        
	        Formatting pingTextColor;
	        Formatting pingIconColor;
	        
	        if (!client.options.hudHidden || FzhConfig.CONFIG.alwaysDisplayed) {
		        if (players != null) {
			        for (AbstractClientPlayerEntity player : players) {
				        
				        if (player.isSpectator() || shown >= FzhConfig.CONFIG.maxPlayersToShow) continue;
				        
				        float health = player.getHealth();
				        float distance = client.player.distanceTo(player);
				        
				        var networkHandler = MinecraftClient.getInstance().getNetworkHandler();
				        if (networkHandler == null) continue;
				        var entry = networkHandler.getPlayerListEntry(player.getUuid());
				        if (entry == null) continue;
				        float ping = entry.getLatency();
				        
				        switch (FzhConfig.CONFIG.colorScheme.toLowerCase()) {
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
				        
				        switch (FzhConfig.CONFIG.displayMode.toLowerCase()) {
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
						        displayIconColor = healthIconColor;
						        displayTextColor = healthTextColor;
						        break;
				        }
				        
						
						// TODO: 实现血量显示会根据玩家状态分颜色为：正常白色 倒地金色 死亡深红色 未知黑色。 目前的代码（疑似）无法正常运行
				        //stat.player
				        //1正常 2倒地 3死亡 10..旁观
				        int playerStat = ScoreUtils.playerStat(player);
				        if (playerStat <= 1) {
					        playerNameColor = Formatting.WHITE;
				        } else if (playerStat == 2) {
					        playerNameColor = Formatting.GOLD;
				        } else if (playerStat == 3) {
					        playerNameColor = Formatting.DARK_RED;
				        } else {
					        playerNameColor = Formatting.DARK_GRAY;
				        }
				        
				        if (FzhConfig.CONFIG.valueBeforeName) {
					        displayText = Text.empty().copy()
						        .append(displayIcon.formatted(displayIconColor))
						        .append(displayTextType).copy().formatted(displayTextColor)
						        .append(" ")
						        .append(player.getName().copy().formatted(playerNameColor)
						        );
				        } else {
					        displayText = Text.empty().copy()
						        .append(player.getName().copy().formatted(playerNameColor))
						        .append(" ")
						        .append(displayTextType).copy().formatted(displayTextColor)
						        .append(displayIcon.formatted(displayIconColor)
						        );
					        
				        }
				        
				        if (FzhConfig.CONFIG.hpdpDisplayY < (screenHeight / 2)) {
					        context.drawTextWithShadow(renderer, displayText, x, y, 0xFFFFFF);
					        y += FzhConfig.CONFIG.textMargin;
					        shown++;
				        } else {
					        context.drawTextWithShadow(renderer, displayText, x, y, 0xFFFFFF);
					        y -= FzhConfig.CONFIG.textMargin;
					        shown++;
				        }
			        }
		        }
	        }
        }
    }
}
