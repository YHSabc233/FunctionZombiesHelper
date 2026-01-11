package top.yhsabc233.fzh.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

import top.yhsabc233.fzh.FzhClient;
import top.yhsabc233.fzh.config.FzhConfig;

@SuppressWarnings("unused")
public class TimerHud{
	// TODO: 实现计时器。
	private static final Identifier TIMER_HUD_LAYER = Identifier.of(FzhClient.MOD_ID, "timer-hud-layer");
	
	public static void init() {
		HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, TIMER_HUD_LAYER, TimerHud::timerRender));
	}
	
	public static int minute = 0;
	public static int second = 0;
	public static int milliSecond = 0;
	
	private static void timer() {
		if (second == 60) {
			minute++;
			second = 0;
		} else {
			if (milliSecond == 20) {
				second++;
				milliSecond = 0;
			} else {
				milliSecond++;
			}
		}
	}
	
	private static void timerRender(DrawContext context, RenderTickCounter tickCounter) {
		if (FzhConfig.CONFIG.isEnabled/* && FzhConfig.CONFIG.timerSwitch*/) {
			MinecraftClient client = MinecraftClient.getInstance();
			TextRenderer textRenderer = client.textRenderer;
			
			int x = 50;
			int y = 50;
			
			float tickProgress = tickCounter.getTickProgress(false);
			
			Text displayText = Text.empty()
				.append(String.valueOf(minute))
				.append(":")
				.append(String.valueOf(second))
				.append(":")
				.append(String.valueOf(milliSecond));
			
			context.drawTextWithShadow(textRenderer, displayText, x, y, 0xFFFFFF);
		}
	}
}
