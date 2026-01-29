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
import top.yhsabc233.fzh.function.HealthDisplay;

public class HealthDisplayHud {
	private static final Identifier HPDP_HUD_LAYER = Identifier.of(FzhClient.MOD_ID, "hpdp-hud-layer");
	private static final MinecraftClient client = MinecraftClient.getInstance();
	
	public static void register() {
		HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(
			IdentifiedLayer.CHAT,
			HPDP_HUD_LAYER,
			HealthDisplayHud::hpdpRender)
		);
	}
	
	private static void hpdpRender(DrawContext context, RenderTickCounter ignoredTickCounter) {
		if (!HealthDisplay.shouldRender() || client.world == null) return;
		
		int shown = 0;
		int x = FzhConfig.CONFIG.hpdpDisplayX;
		int y = FzhConfig.CONFIG.hpdpDisplayY;
		
		for (AbstractClientPlayerEntity player : client.world.getPlayers()) {
			if (player.isSpectator() || shown >= FzhConfig.CONFIG.maxPlayersToShow) continue;
			int health = (int) player.getHealth();
			
			String healthString = String.valueOf(health);
			String playerName = player.getName().getString();
			String displayIcon = "❤";
			
			Formatting healthTextColor;
			Formatting healthIconColor;
			
			// 根据血量选择颜色 1~5 红色 6~10 黄色 11+ 绿色
			if (health > 10) healthTextColor = healthIconColor = Formatting.GREEN;
			else if (health > 5) healthTextColor = healthIconColor = Formatting.YELLOW;
			else healthTextColor = healthIconColor = Formatting.RED;
			
			//TODO: add valuebeforeName
			/*MutableText displayText = FzhConfig.CONFIG.valueBeforeName
				? Text.empty().copy()
				.append(displayIcon.formatted(healthIconColor))
				.append(" ")
				.append(healthString).copy().formatted(healthTextColor)
				.append(" ")
				.append(playerName).formatted(Formatting.WHITE)
				
				: Text.empty().copy()
				.append(playerName.formatted(Formatting.WHITE))
				.append(" ")
				.append(healthString).copy().formatted(healthTextColor)
				.append(" ")
				.append(displayIcon.formatted(healthIconColor));*/
			
			MutableText displayText = Text.empty().copy()
				.append(displayIcon.formatted(healthIconColor))
				.append(" ")
				.append(healthString.formatted(healthTextColor))
				.append(" ")
				.append(playerName.formatted(Formatting.WHITE));
			
			context.drawTextWithShadow(client.textRenderer, displayText, x, y, Colors.WHITE);
			// 根据屏幕上下部分选择排序方式
			if (FzhConfig.CONFIG.hpdpDisplayY < (client.getWindow().getScaledHeight() / 2) - 20)
				y += FzhConfig.CONFIG.textMargin;
			else
				y -= FzhConfig.CONFIG.textMargin;
			shown++;
		}
	}
	
}
