package com.yhsabc233.fzh.gui.screen;

import com.yhsabc233.fzh.config.FzhConfig;
import com.yhsabc233.fzh.config.FzhConfigManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class FzhConfigScreen extends Screen {
    // TODO: 优化此类，感觉太乱了。
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
	/*
	public ButtonWidget setColorSchemeToBothButton;
	public ButtonWidget setColorSchemeToIconButton;
	public ButtonWidget setColorSchemeToTextButton;
	*/
    public FzhConfig config = FzhConfig.CONFIG;
	
    @Override
    public void init() {
        valueBeforeNameButton = ButtonWidget.builder(Text.translatable(config.valueBeforeName ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.valueBeforeName")), button -> {
		        config.valueBeforeName = !config.valueBeforeName;
	        })
                .dimensions(width / 2, 30, 180, 20)
                .tooltip(Tooltip.of(Text.literal("设置数值是否显示在玩家名称前。")))
                .build();
        
        isEnabledButton = ButtonWidget.builder(Text.translatable(config.isEnabled ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.isEnabled")), button ->{
		        config.isEnabled = !config.isEnabled;
	        })
                .dimensions(width / 2 - 200, 30, 180, 20)
                .tooltip(Tooltip.of(Text.literal("调整本模组的全局开关。")))
                .build();
        
        alwayDisplayedButton = ButtonWidget.builder(Text.translatable(config.isEnabled ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.alwaysDisplayed")), button -> {
		        config.alwaysDisplayed = !config.alwaysDisplayed;
	        })
                .dimensions(width / 2 - 200, 60, 180, 20)
                .tooltip(Tooltip.of(Text.literal("调整是否让血量显示在玩家隐藏HUD时保持显示。")))
                .build();
        
        textMarginSlider = new SliderWidget(width / 2, 60, 180, 20, Text.translatable("fzh.options.config.textMargin").append(" : " + config.textMargin), ((double) config.textMargin / 30)) {
            @Override
            protected void updateMessage() {
                setMessage(Text.translatable("fzh.options.config.textMargin").append(" : " + config.textMargin));
            }
            
            @Override
            protected void applyValue() {
                int maxValue = 30;
                double result = value * maxValue;
                config.textMargin = Double.valueOf(result).intValue();
            }
            
        };
        
        hpdpDisplayXSlider = new SliderWidget(
			width / 2 - 200, 90, 380, 20,
	        Text.translatable("fzh.options.config.hpdpDisplayX")
		        .append(" : " + config.hpdpDisplayX), ((double) config.hpdpDisplayX / ((double) (client != null ? client.getWindow().getWidth() : 0) / 2 - 95))) {
            @Override
            protected void updateMessage() {
                setMessage(Text.translatable("fzh.options.config.hpdpDisplayX").append(" : " + Double.valueOf(config.hpdpDisplayX).intValue()
                ));
            }
            
            @Override
            protected void applyValue() {
                if (client != null) {
                    int maxValue = client.getWindow().getWidth() / 2 - 95;
                    double result = value * maxValue;
                    config.hpdpDisplayX = Double.valueOf(result).intValue();
                }
            }
        };
        
        hpdpDisplayYSlider = new SliderWidget(
			width / 2 - 200, 120, 380, 20,
	        Text.translatable("fzh.options.config.hpdpDisplayY")
		        .append(" : " + config.hpdpDisplayY), ((double) config.hpdpDisplayY / ((double) (client != null ? client.getWindow().getHeight() : 0) / 2 - 15))) {
            @Override
            protected void updateMessage() {
                setMessage(Text.translatable("fzh.options.config.hpdpDisplayY").append(" : " + Double.valueOf(config.hpdpDisplayY).intValue()
                ));
            }
            
            @Override
            protected void applyValue() {
                if (client != null) {
                    int maxValue = client.getWindow().getHeight() / 2 - 15;
                    double result = value * maxValue;
                    config.hpdpDisplayY = Double.valueOf(result).intValue();
                }
            }
        };
	    
	    /*setColorSchemeToBothButton = ButtonWidget.builder(Text.translatable("fzh.options.text", Text.translatable("fzh.options.config.colorScheme"), Text.literal("相同颜色")), button -> {
			    config.colorScheme = "BOTH";
		    })
		    .dimensions(width / 2 - 200, 150, 150, 20)
		    .tooltip(Tooltip.of(Text.literal("调整血量显示的配色方案至 相同颜色")))
		    .build();
	    
	    setColorSchemeToIconButton = ButtonWidget.builder(Text.translatable("fzh.options.text", Text.translatable("fzh.options.config.colorScheme"), Text.literal("仅图标变色")), button -> {
			    config.colorScheme = "ICON";
		    })
		    .dimensions(width / 2, 150, 150, 20)
		    .tooltip(Tooltip.of(Text.literal("调整血量显示的配色方案至 仅图标变色")))
		    .build();
	    
	    setColorSchemeToTextButton = ButtonWidget.builder(Text.translatable("fzh.options.text", Text.translatable("fzh.options.config.colorScheme"), Text.literal("仅数值变色")), button -> {
			    config.colorScheme = "TEXT";
		    })
		    .dimensions(width / 2 - 200, 180, 150, 20)
		    .tooltip(Tooltip.of(Text.literal("调整血量显示的配色方案至 仅数值变色")))
		    .build();
        */
        this.addDrawableChild(valueBeforeNameButton);
        this.addDrawableChild(isEnabledButton);
        this.addDrawableChild(alwayDisplayedButton);
        this.addDrawableChild(textMarginSlider);
        this.addDrawableChild(hpdpDisplayXSlider);
        this.addDrawableChild(hpdpDisplayYSlider);
		//this.addDrawableChild(setColorSchemeToBothButton);
	    //this.addDrawableChild(setColorSchemeToIconButton);
	    //this.addDrawableChild(setColorSchemeToTextButton);
	    // TODO: 修复设置配色方案的bug。
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
			Text.translatable(config.valueBeforeName ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.valueBeforeName"))
        );
		
        isEnabledButton.setMessage(
			Text.translatable(config.isEnabled ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.isEnabled"))
        );
		
        alwayDisplayedButton.setMessage(
			Text.translatable(config.alwaysDisplayed ? "options.on.composed" : "options.off.composed", Text.translatable("fzh.options.config.alwaysDisplayed"))
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
