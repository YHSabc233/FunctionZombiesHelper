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
	public DraggableWidget zhfPositionDrag;
	
	@Override
	protected void init() {
		doneButton = ButtonWidget.builder(Text.translatable("fzh.options.done"), button -> close())
			.dimensions(this.width / 2 - 150, this.height - 25, 100, 20)
			.tooltip(Tooltip.of(Text.literal("保存当前设置并关闭此界面。")))
			.build();
		
		resetButton = ButtonWidget.builder(Text.translatable("fzh.options.reset"), button -> {
				hpdpPositionDrag.setPosition(10, 30);
				FzhConfig.CONFIG.hpdpDisplayX = 10;
				FzhConfig.CONFIG.hpdpDisplayY = 30;
				
				zhfPositionDrag.setPosition(10, 10);
				FzhConfig.CONFIG.zhfDisplayX = 10;
				FzhConfig.CONFIG.zhfDisplayY = 10;
			})
			.dimensions(this.width / 2 + 50, this.height - 25, 100, 20)
			.tooltip(Tooltip.of(Text.literal("将所有物件的坐标重置为默认。")))
			.build();
		
		
		hpdpPositionDrag = new DraggableWidget(
			FzhConfig.CONFIG.hpdpDisplayX,
			FzhConfig.CONFIG.hpdpDisplayY,
			80,
			40,
			"hpdpPositionDrag"
		);
		
		zhfPositionDrag = new DraggableWidget(
			FzhConfig.CONFIG.zhfDisplayX,
			FzhConfig.CONFIG.zhfDisplayY,
			45,
			10,
			"zhfPositionDrag"
		);
		
		this.addDrawableChild(doneButton);
		this.addDrawableChild(resetButton);
		this.addDrawableChild(hpdpPositionDrag);
		this.addDrawableChild(zhfPositionDrag);
	}
	
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		
		context.drawCenteredTextWithShadow(
			this.textRenderer,
			Text.translatable("fzh.screen.positionModify.tips"),
			this.width / 2,
			this.height / 2,
			Colors.WHITE
		);
		
		context.drawCenteredTextWithShadow(
			this.textRenderer,
			Text.translatable("fzh.screen.positionModify.title"),
			this.width / 2,
			10,
			Colors.WHITE
		);
	}
	
	@Override
	public void close() {
		if (client != null) client.setScreen(parent);
		FzhConfigManager.saveConfig();
	}
}
