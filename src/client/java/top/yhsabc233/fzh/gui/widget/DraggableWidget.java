package top.yhsabc233.fzh.gui.widget;

import net.minecraft.client.gui.widget.Widget;
import org.jetbrains.annotations.Nullable;
import top.yhsabc233.fzh.config.FzhConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;

public class DraggableWidget extends ClickableWidget {
	private final String widgetName;

	/// <summary>
	/// 该 {@link Widget} 目前仅适用于 {@link PositionModifyScreen}
	/// </summary>
	public DraggableWidget(int x, int y, int width, int height, @Nullable String widgetName) {
		super(x, y, width, height, Text.empty());
		this.widgetName = widgetName;
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
		MultilineText text = MultilineText.create(MinecraftClient.getInstance().textRenderer, Text.literal("ERROR"));

		if (widgetName == "hpdpPositionDrag") {
			text = MultilineText.create(MinecraftClient.getInstance().textRenderer, Text.empty()
					.append("Player001 §a❤ 20\n")
					.append("Player002 §a❤ 20\n")
					.append("Player003 §a❤ 20\n")
					.append("Player004 §a❤ 20"));
		} else if (widgetName == "timerPositionDrag") {
			text = MultilineText.create(MinecraftClient.getInstance().textRenderer, Text.empty()
					.append("00:00:00"));
		}

		text.drawWithShadow(context, getX(), getY(), 10, 0xFFFFFF);
	}

	@Override
	protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
		this.setPosition((int) mouseX, (int) mouseY);

		/*
		 * FzhConfig.CONFIG.hpdpDisplayX = (int) mouseX;
		 * FzhConfig.CONFIG.hpdpDisplayY = (int) mouseY;
		 */

		if (widgetName == "hpdpPositionDrag") {
			FzhConfig.CONFIG.hpdpDisplayX = (int) mouseX;
			FzhConfig.CONFIG.hpdpDisplayY = (int) mouseY;
		} else if (widgetName == "timerPositionDrag") {
			FzhConfig.CONFIG.timerDisplayX = (int) mouseX;
			FzhConfig.CONFIG.timerDisplayY = (int) mouseY;
		}
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
	}
}