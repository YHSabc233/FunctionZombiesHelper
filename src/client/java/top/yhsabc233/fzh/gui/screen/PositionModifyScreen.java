package top.yhsabc233.fzh.gui.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.config.FzhConfigManager;
import top.yhsabc233.fzh.gui.widget.DraggableWidget;

public class PositionModifyScreen extends Screen {
	private final Screen parent;
	
	public PositionModifyScreen(Screen parent) {
		super(Text.translatable("fzh.screen.positionModify.title"));
		this.parent = parent;
	}
	
	public ButtonWidget doneButton;
	public ButtonWidget resetButton;
	public DraggableWidget hpdpPositionDrag;
	
	@SuppressWarnings("CodeBlock2Expr")
	@Override
	protected void init() {
		doneButton = ButtonWidget.builder(Text.translatable("fzh.options.done"), button -> {
			close();
		})
			.dimensions(this.width / 2 - 150, this.height - 25, 100, 20)
			.tooltip(Tooltip.of(Text.literal("保存当前设置并关闭此界面。")))
			.build();
		
		resetButton = ButtonWidget.builder(Text.translatable("fzh.options.reset"), button -> {
				hpdpPositionDrag.setPosition(0, 0);
				FzhConfig.CONFIG.hpdpDisplayX = 0;
				FzhConfig.CONFIG.hpdpDisplayY = 0;
			})
			.dimensions(width / 2 + 50, height - 25, 100, 20)
			.tooltip(Tooltip.of(Text.literal("重置血量显示的坐标至屏幕左上角。")))
			.build();
		
		
		hpdpPositionDrag = new DraggableWidget(
			FzhConfig.CONFIG.hpdpDisplayX,
			FzhConfig.CONFIG.hpdpDisplayY,
			80,
			40,
			"hpdpPositionDrag"
		);
		
		this.addDrawableChild(doneButton);
		this.addDrawableChild(resetButton);
		this.addDrawableChild(hpdpPositionDrag);
	}
	
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		context.drawCenteredTextWithShadow(
			textRenderer, Text.translatable("fzh.screen.positionModify.tips"), context.getScaledWindowWidth() / 2, context.getScaledWindowHeight() / 2, Colors.WHITE
		);
		
		context.drawCenteredTextWithShadow(
			textRenderer, Text.translatable("fzh.screen.positionModify.title"), context.getScaledWindowWidth() / 2, 20, Colors.WHITE
		);
	}
	
	@Override
	public void close() {
		if (client == null) return;
		client.setScreen(parent);
		FzhConfigManager.saveConfig();
	}
}
