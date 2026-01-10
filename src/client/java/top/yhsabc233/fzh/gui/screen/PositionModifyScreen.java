package top.yhsabc233.fzh.gui.screen;

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
    
    @Override
    protected void init() {
        doneButton = ButtonWidget.builder(Text.translatable("fzh.options.done"), button -> {
			close();
		})
	        .dimensions(width / 2 - 205, height - 25, 100, 20)
	        .build();
		
        resetButton = ButtonWidget.builder(Text.translatable("fzh.options.reset"), button -> {
			hpdpPositionDrag.setPosition(0, 0);
			FzhConfig.CONFIG.hpdpDisplayX = 0;
			FzhConfig.CONFIG.hpdpDisplayY = 0;
		})
	        .dimensions(width / 2 + 5, height - 25, 100, 20)
	        .build();
	    
	     
		hpdpPositionDrag = new DraggableWidget(
			FzhConfig.CONFIG.hpdpDisplayX,
			FzhConfig.CONFIG.hpdpDisplayY,
			80,
			40
		);
	    
	    addDrawableChild(doneButton);
        addDrawableChild(resetButton);
		addDrawableChild(hpdpPositionDrag);
    }
	
	@Override
	public void render(DrawContext context,int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		
		context.drawTextWithShadow(textRenderer, Text.literal("拖动文本来修改血量显示的显示位置。"), width / 2 - 205, 50, 0xFFFFFF);
	}
    
    @Override
    public void close() {
        if (client != null) {
            client.setScreen(parent);
	        FzhConfigManager.saveConfig();
        }
    }
}
