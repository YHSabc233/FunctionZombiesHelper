package top.yhsabc233.fzh.gui.widget;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import org.jetbrains.annotations.Nullable;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;

public class DraggableWidget extends ClickableWidget {
	private final String widgetName;
	
	/// 该功能目前仅适用于 {@link PositionModifyScreen}
	public DraggableWidget(int x, int y, int width, int height, @Nullable String widgetName) {
		super(x, y, width, height, Text.empty());
		this.widgetName = widgetName;
	}
	
	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		
		int startColor;
		int endColor;
		
		if (isMouseOver(mouseX, mouseY)) {
			startColor = Colors.WHITE;
			endColor = Colors.WHITE;
		} else {
			startColor = 0;
			endColor = 0;
		}
		
		context.fillGradient(getX(), getY(), getX() + this.width, getY() + this.height, startColor, endColor);
		MultilineText text =
			switch (widgetName) {
				case "hpdpPositionDrag" -> MultilineText.create(MinecraftClient.getInstance().textRenderer, Text.empty()
					.append("Player001 §a❤ 20\n")
					.append("Player002 §a❤ 20\n")
					.append("Player003 §a❤ 20\n")
					.append("Player004 §a❤ 20"));
				case "timerPositionDrag" -> MultilineText.create(MinecraftClient.getInstance().textRenderer, Text.empty()
					.append("00:00:00"));
				case "zhfPositionDrag" -> MultilineText.create(MinecraftClient.getInstance().textRenderer, Text.empty()
					.append("§eZHF: §cOFF"));
				case null, default -> MultilineText.create(MinecraftClient.getInstance().textRenderer, Text.empty());
			};
		
		text.drawWithShadow(context, getX(), getY(), 10, 0xFFFFFF);
	}
	
	@Override
	protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
		this.setPosition((int) mouseX, (int) mouseY);
		
		switch (widgetName) {
			case "hpdpPositionDrag" -> {
				FzhConfig.CONFIG.hpdpDisplayX = (int) mouseX;
				FzhConfig.CONFIG.hpdpDisplayY = (int) mouseY;
			}
			/*case "timerPositionDrag" -> {
				FzhConfig.CONFIG.timerDisplayX = (int) mouseX;
				FzhConfig.CONFIG.timerDisplayY = (int) mouseY;
			}*/
			case "zhfPositionDrag" -> {
				FzhConfig.CONFIG.zhfDisplayX = (int) mouseX;
				FzhConfig.CONFIG.zhfDisplayY = (int) mouseY;
			}
			case null, default -> {
			}
		}
	}
	
	@Override
	public void playDownSound(SoundManager soundManager) {
	}
	
	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
	}
	
}