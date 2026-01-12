package top.yhsabc233.fzh.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import top.yhsabc233.fzh.FzhClient;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.util.ScoreUtils;

import java.util.List;

public class HealthDisplayHud {
	//@SuppressWarnings("deprecation")
	/*public static void init() {
		HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
			hpdpRender(drawContext));
	}*/
	
	private static final Identifier HEALTH_DISPLAY_HUD_LAYER = Identifier.of(FzhClient.MOD_ID, "healthdisplay-hud-layer");
	
	public static void init() {
		HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, HEALTH_DISPLAY_HUD_LAYER, HealthDisplayHud::hpdpRender));
	}
	
	public static Formatting displayIconColor;
	public static Formatting displayTextColor;
	public static Formatting playerNameColor;
	
	public static Text displayText;
	
	public static String displayIcon = " ❤ ";
	public static String displayTextType = "NaN";
	
	public static final MinecraftClient client = MinecraftClient.getInstance();
	
	@SuppressWarnings("unused")
	private static void hpdpRender(DrawContext context, RenderTickCounter tickCounter) {
		if (FzhConfig.CONFIG.isEnabled) {
			
			if (client.world == null || client.player == null) return;
			
			TextRenderer renderer = client.textRenderer;
			List<AbstractClientPlayerEntity> players = client.world.getPlayers();
			
			if (players == null) return;
			
			//int screenWidth = client.getWindow().getScaledWidth();
			int screenHeight = client.getWindow().getScaledHeight();
			
			int shown = 0;
			
			int x = 0;
			int y = 0;
			
			if (FzhConfig.CONFIG.position.toLowerCase().contains("custom")) {
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
				for (AbstractClientPlayerEntity player : players) {
					
					if (player.isSpectator() || shown >= FzhConfig.CONFIG.maxPlayersToShow) continue;
					
					float health = player.getHealth();
					float distance = client.player.distanceTo(player);
					float ping;
					
					var networkHandler = MinecraftClient.getInstance().getNetworkHandler();
					if (networkHandler == null) continue;
					var entry = networkHandler.getPlayerListEntry(player.getUuid());
					if (entry == null) continue;
					ping = entry.getLatency();
					
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
					//调用stat.player
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
					//貌似删了就会出现血量显示不显示的bug :(
					
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
					
					context.drawTextWithShadow(renderer, displayText, x, y, 0xFFFFFF);
					// 根据屏幕上下部分选择排序
					if (FzhConfig.CONFIG.hpdpDisplayY < (screenHeight / 2) - 20) {
						y += FzhConfig.CONFIG.textMargin;
					} else {
						y -= FzhConfig.CONFIG.textMargin;
					}
					shown++;
				}
			}
		}
	}
}
