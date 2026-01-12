package top.yhsabc233.fzh.gui.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.config.FzhConfigManager;
import top.yhsabc233.fzh.config.FzhConfigYACLApi;

public class FzhConfigScreen extends Screen {
    private final Screen parent;
    public final Text TITLE_TEXT = Text.translatable("fzh.screen.config.title");
    
    public FzhConfigScreen(Screen parent) {
        super(Text.translatable("fzh.screen.config.title"));
        this.parent = parent;
    }
    
    public ButtonWidget valueBeforeNameButton;
    public ButtonWidget isEnabledButton;
	
    public ButtonWidget alwayDisplayedButton;
    public SliderWidget textMarginSlider;
	
    public SliderWidget hpdpDisplayXSlider;
    public SliderWidget hpdpDisplayYSlider;
	
	public ButtonWidget openNewConfigScreenButton;
	
    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public void init() {
        valueBeforeNameButton = ButtonWidget.builder(Text.translatable(FzhConfig.CONFIG.valueBeforeName ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.valueBeforeName")), button -> {
		        FzhConfig.CONFIG.valueBeforeName = !FzhConfig.CONFIG.valueBeforeName;
	        })
                .dimensions(width / 2, 30, 180, 20)
                .tooltip(Tooltip.of(Text.literal("设置数值是否显示在玩家名称前。")))
                .build();
        
        isEnabledButton = ButtonWidget.builder(Text.translatable(FzhConfig.CONFIG
		        .isEnabled ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.isEnabled")), button ->{
		        FzhConfig.CONFIG.isEnabled = !FzhConfig.CONFIG.isEnabled;
	        })
                .dimensions(width / 2 - 200, 30, 180, 20)
                .tooltip(Tooltip.of(Text.literal("调整本模组的全局开关。")))
                .build();
        
        alwayDisplayedButton = ButtonWidget.builder(Text.translatable(FzhConfig.CONFIG.isEnabled ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.alwaysDisplayed")), button -> {
		        FzhConfig.CONFIG.alwaysDisplayed = !FzhConfig.CONFIG.alwaysDisplayed;
	        })
                .dimensions(width / 2 - 200, 60, 180, 20)
                .tooltip(Tooltip.of(Text.literal("调整是否让血量显示在玩家隐藏HUD时保持显示。")))
                .build();
        
        textMarginSlider = new SliderWidget(width / 2, 60, 180, 20, Text.translatable("fzh.options.config.textMargin").append(" : " + FzhConfig.CONFIG.textMargin), ((double) FzhConfig.CONFIG.textMargin / 30)) {
            @Override
            protected void updateMessage() {
                setMessage(Text.translatable("fzh.options.config.textMargin").append(" : " + FzhConfig.CONFIG.textMargin));
            }
            
            @Override
            protected void applyValue() {
                int maxValue = 30;
                double result = value * maxValue;
                FzhConfig.CONFIG.textMargin = Double.valueOf(result).intValue();
            }
            
        };
        
        hpdpDisplayXSlider = new SliderWidget(
			width / 2 - 200, 90, 380, 20,
	        Text.translatable("fzh.options.config.hpdpDisplayX")
		        .append(" : " + FzhConfig.CONFIG.hpdpDisplayX), ((double) FzhConfig.CONFIG.hpdpDisplayX / ((double) (client != null ? client.getWindow().getWidth() : 0) / 2 - 95))) {
            @Override
            protected void updateMessage() {
                setMessage(Text.translatable("fzh.options.config.hpdpDisplayX").append(" : " + Double.valueOf(FzhConfig.CONFIG.hpdpDisplayX).intValue()
                ));
            }
            
            @Override
            protected void applyValue() {
                if (client != null) {
                    int maxValue = client.getWindow().getWidth() / 2 - 95;
                    double result = value * maxValue;
                    FzhConfig.CONFIG.hpdpDisplayX = Double.valueOf(result).intValue();
                }
            }
        };
        
        hpdpDisplayYSlider = new SliderWidget(
			width / 2 - 200, 120, 380, 20,
	        Text.translatable("fzh.options.config.hpdpDisplayY")
		        .append(" : " + FzhConfig.CONFIG.hpdpDisplayY), ((double) FzhConfig.CONFIG.hpdpDisplayY / ((double) (client != null ? client.getWindow().getHeight() : 0) / 2 - 15))) {
            @Override
            protected void updateMessage() {
                setMessage(Text.translatable("fzh.options.config.hpdpDisplayY").append(" : " + Double.valueOf(FzhConfig.CONFIG.hpdpDisplayY).intValue()
                ));
            }
            
            @Override
            protected void applyValue() {
                if (client != null) {
                    int maxValue = client.getWindow().getHeight() / 2 - 15;
                    double result = value * maxValue;
                    FzhConfig.CONFIG.hpdpDisplayY = Double.valueOf(result).intValue();
                }
            }
        };
	    
	    openNewConfigScreenButton = ButtonWidget.builder(Text.literal("打开新配置界面"), button ->{
			    client.send(() -> {
					close();
					client.setScreen(FzhConfigYACLApi.createScreen(client.currentScreen));
			    });
		    })
		    .dimensions(width / 2 - 200, 150, 380, 30)
		    .tooltip(Tooltip.of(Text.literal("打开使用YACL制作的新配置修改界面。\n原来的界面（也就是当前的这个）将会在不久后被移除。")))
		    .build();
		
        this.addDrawableChild(valueBeforeNameButton);
        this.addDrawableChild(isEnabledButton);
        this.addDrawableChild(alwayDisplayedButton);
        this.addDrawableChild(textMarginSlider);
        this.addDrawableChild(hpdpDisplayXSlider);
        this.addDrawableChild(hpdpDisplayYSlider);
		this.addDrawableChild(openNewConfigScreenButton);
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        
        context.drawText(
			this.textRenderer,
	        TITLE_TEXT,
	        width / 2 - 205,
	        10,
	        0xFFFFFF,
	        true
        );
		
		if (this.client != null && this.client.isInSingleplayer() && Formatting.RED.getColorValue() != null) {
			context.drawText(
				this.textRenderer,
				Text.literal("当前处于单人存档中，本模组将处于失效状态。"),
				width / 2 - 205 + textRenderer.getWidth(TITLE_TEXT) + 30,
				10,
				Formatting.RED.getColorValue(),
				true
			);
		}
		
        valueBeforeNameButton.setMessage(
			Text.translatable(FzhConfig.CONFIG.valueBeforeName ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.valueBeforeName"))
        );
		
        isEnabledButton.setMessage(
			Text.translatable(FzhConfig.CONFIG.isEnabled ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.isEnabled"))
        );
		
        alwayDisplayedButton.setMessage(
			Text.translatable(FzhConfig.CONFIG.alwaysDisplayed ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.alwaysDisplayed"))
        );
		
    }
    
    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(this.parent);
        }
    }
    
    @Override
    public void removed() {
        FzhConfigManager.saveConfig();
    }
}
