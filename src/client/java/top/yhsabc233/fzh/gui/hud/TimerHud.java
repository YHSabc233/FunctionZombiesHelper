package top.yhsabc233.fzh.gui.hud;

import top.yhsabc233.fzh.config.FzhConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class TimerHud{
	// TODO: 实现计时器。
	@SuppressWarnings("deprecation")
	public static void init() {
		HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
			timerRender(drawContext));
	}
	
	public static int minute = 0;
	public static int second = 0;
	public static int milliSecond = 0;
	
	private static void timerRender(DrawContext context) {
		if (FzhConfig.CONFIG.isEnabled && FzhConfig.CONFIG.timerSwitch) {
			MinecraftClient client = MinecraftClient.getInstance();
			TextRenderer textRenderer = client.textRenderer;
			
			int x = 50;
			int y = 50;
			
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
