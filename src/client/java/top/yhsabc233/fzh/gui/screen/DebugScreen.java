package top.yhsabc233.fzh.gui.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.gui.widget.DraggableWidget;

public class DebugScreen extends Screen {
	private final Screen parent;
	
	public DebugScreen(Screen parent) {
		super(Text.empty());
		this.parent = parent;
	}
	
	public DraggableWidget testDrag1;
	public DraggableWidget testDrag2;
	
	@Override
	protected void init() {
		
		testDrag1 = new DraggableWidget(
			FzhConfig.CONFIG.hpdpDisplayX,
			FzhConfig.CONFIG.hpdpDisplayY,
			80,
			40,
			"hpdpPositionDrag"
		);
		
		testDrag2 = new DraggableWidget(
			FzhConfig.CONFIG.zhfDisplayX,
			FzhConfig.CONFIG.zhfDisplayY,
			45,
			10,
			"zhfPositionDrag"
		);
		
		ButtonWidget openPositionModifyButton = ButtonWidget.builder(
			Text.literal("PositionModifyScreen"),
			button -> {if (client != null) client.setScreen(new PositionModifyScreen(this));}
		).dimensions(this.width / 2 - 100, this.height - 30, 180, 20).build();
		
		this.addDrawableChild(testDrag1);
		this.addDrawableChild(testDrag2);
		this.addDrawableChild(openPositionModifyButton);
	}
	
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
		super.render(context, mouseX, mouseY, deltaTicks);
		
		context.drawCenteredTextWithShadow(
			this.textRenderer,
			Text.literal("Debug Screen"),
			this.width / 2,
			10,
			0xFFFFFF
		);
	}
	
	@Override
	public void close() {
		if (client != null) client.setScreen(parent);
	}
	
}
