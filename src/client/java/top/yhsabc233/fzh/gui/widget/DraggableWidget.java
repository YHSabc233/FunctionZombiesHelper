package top.yhsabc233.fzh.gui.widget;

import top.yhsabc233.fzh.config.FzhConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;

public class DraggableWidget extends ClickableWidget {
	/// <summary>
	/// 仅适用于 {@link PositionModifyScreen}。
	/// </summary>
	public DraggableWidget(int x, int y, int width, int height, String widgetName) {
		super(x, y, width, height, Text.of(widgetName));
	}
	
	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		
		int startColor;
		int endColor;
		
		if (isMouseOver(mouseX, mouseY)) {
			startColor = 0xFFFFFFFF;
			endColor = 0xFFFFFFFF;
		} else {
			startColor = 0x00000000;
			endColor = 0x00000000;
		}
		
		context.fillGradient(getX(), getY(), getX() + this.width, getY() + this.height, startColor, endColor);
		MultilineText text = MultilineText.create(MinecraftClient.getInstance().textRenderer, Text.empty()
				.append("Player001 §a❤ 20\n")
				.append("Player002 §a❤ 20\n")
				.append("Player003 §a❤ 20\n")
				.append("Player004 §a❤ 20")
		);
		
		text.drawWithShadow(context, getX(), getY(), 10, 0xFFFFFF);
	}
	
	@Override
	protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
		this.setPosition((int) mouseX, (int) mouseY);
		
		FzhConfig.CONFIG.hpdpDisplayX = (int) mouseX;
		FzhConfig.CONFIG.hpdpDisplayY = (int) mouseY;
	}
	
	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
	}
}