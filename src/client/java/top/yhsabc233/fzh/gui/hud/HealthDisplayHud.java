package top.yhsabc233.fzh.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import top.yhsabc233.fzh.FzhClient;
import top.yhsabc233.fzh.config.FzhConfig;

public class HealthDisplayHud {
	private static final Identifier HPDP_HUD_LAYER = Identifier.of(FzhClient.MOD_ID, "hpdp-hud-layer");
	
	public static void register() {
		HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(
			IdentifiedLayer.CHAT,
			HPDP_HUD_LAYER,
			HealthDisplayHud::hpdpRender)
		);
	}
	
	private static final MinecraftClient client = MinecraftClient.getInstance();
	
	private static void hpdpRender(DrawContext context, RenderTickCounter tickCounter) {
		if (FzhConfig.CONFIG.isEnabled) {
			if (client.world == null || client.player == null) return;
			int shown = 0;
			
			int y = FzhConfig.CONFIG.hpdpDisplayY + 5;
			
			//Formatting playerNameColor;
			Formatting healthTextColor;
			Formatting healthIconColor;
			
			MutableText displayText;
			
			if (!client.options.hudHidden || FzhConfig.CONFIG.alwaysDisplayed) {
				if (FzhConfig.CONFIG.hpdpSwitch) {
					for (AbstractClientPlayerEntity player : client.world.getPlayers()) {
						if (player.isSpectator() || shown >= FzhConfig.CONFIG.maxPlayersToShow) continue;
						
						float health = player.getHealth();
						//int playerStat = ScoreUtils.playerStat(player);
						String hp = String.format("%.0f", health);
						String displayIcon = "❤";
						
						// 根据血量选择颜色 0~4 红色 5~9 黄色 10+ 绿色
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
								break;
							case "icon":
								healthTextColor = Formatting.GREEN;
								if (health >= 10) healthIconColor = Formatting.GREEN;
								else if (health >= 5) healthIconColor = Formatting.YELLOW;
								else healthIconColor = Formatting.RED;
								break;
							case "text":
								healthIconColor = Formatting.RED;
								if (health >= 10) healthTextColor = Formatting.GREEN;
								else if (health >= 5) healthTextColor = Formatting.YELLOW;
								else healthTextColor = Formatting.RED;
								break;
							default:
								healthTextColor = Formatting.GREEN;
								healthIconColor = Formatting.RED;
								break;
						}
						
						// TODO: 实现血量显示会根据玩家状态分颜色显示
						// 已废弃
						// 正常白色 倒地金色 死亡深红色 未知深灰色
						// 调用stat.player
						// 1 正常 2 倒地 3 死亡 10..旁观
						/*if (playerStat <= 1) {
							playerNameColor = Formatting.WHITE;
						} else if (playerStat == 2) {
							playerNameColor = Formatting.GOLD;
						} else if (playerStat == 3) {
							playerNameColor = Formatting.DARK_RED;
						} else {
							playerNameColor = Formatting.DARK_GRAY;
						}*/
						
						if (FzhConfig.CONFIG.valueBeforeName) {
							displayText = Text.empty().copy()
								.append(displayIcon.formatted(healthIconColor))
								.append(" ")
								.append(hp).copy().formatted(healthTextColor)
								.append(" ")
								.append(player.getName().copy().formatted(Formatting.WHITE));
						} else {
							displayText = Text.empty().copy()
								.append(player.getName().copy().formatted(Formatting.WHITE))
								.append(" ")
								.append(hp).copy().formatted(healthTextColor)
								.append(" ")
								.append(displayIcon.formatted(healthIconColor));
						}
						
						context.drawTextWithShadow(client.textRenderer, displayText, FzhConfig.CONFIG.hpdpDisplayX, y, Colors.WHITE);
						// 根据屏幕上下部分选择排序方式
						if (FzhConfig.CONFIG.hpdpDisplayY < (client.getWindow().getScaledHeight() / 2) - 20) y += FzhConfig.CONFIG.textMargin;
						else y -= FzhConfig.CONFIG.textMargin;
						shown++;
					}
				}
			}
		}
	}
	
}
