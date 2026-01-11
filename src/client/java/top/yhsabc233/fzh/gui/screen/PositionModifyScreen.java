package top.yhsabc233.fzh.gui.screen;

import net.minecraft.client.gui.tooltip.Tooltip;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.config.FzhConfigManager;
import top.yhsabc233.fzh.gui.widget.DraggableWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PositionModifyScreen extends Screen {
	
	private final Screen parent;
	
	public PositionModifyScreen(Screen parent) {
		super(Text.translatable("fzh.screen.positionModify.title"));
		this.parent = parent;
	}
	
	public ButtonWidget doneButton;
	public ButtonWidget resetButton;
	public DraggableWidget hpdpPositionDrag;
	//public DraggableWidget timerPositionDrag;
	
	@Override
	protected void init() {
		doneButton = ButtonWidget.builder(Text.translatable("fzh.options.done"), button -> close())
			.dimensions(width / 2 - 150, height - 25, 100, 20)
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
		
		/*timerPositionDrag = new DraggableWidget(
			FzhConfig.CONFIG.timerDisplayX,
			FzhConfig.CONFIG.timerDisplayY,
			50,
			10,
			"timerPositionDrag"
		);*/
		
		addDrawableChild(doneButton);
		addDrawableChild(resetButton);
		addDrawableChild(hpdpPositionDrag);
		//addDrawableChild(timerPositionDrag);
	}
	
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		
		context.drawTextWithShadow(textRenderer, Text.translatable("fzh.screen.positionModify.tips"), width / 2 - 80, height / 2 - 280, 0xFFFFFF);
		context.drawTextWithShadow(textRenderer, Text.translatable("fzh.screen.positionModify.title"), width / 2 - 50, height / 2 - 250, 0xFFFFFF);
	}
	
	@Override
	public void close() {
		if (client == null) return;
		client.setScreen(parent);
		FzhConfigManager.saveConfig();
	}
}
