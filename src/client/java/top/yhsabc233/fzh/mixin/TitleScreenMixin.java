package top.yhsabc233.fzh.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.yhsabc233.fzh.FzhClient;
import top.yhsabc233.fzh.gui.screen.DebugScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Text title) {
		super(title);
	}
	
	@Inject(at = @At("RETURN"), method = "render")
	private void fzhClientRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		var client = MinecraftClient.getInstance();
		Text customText = Text.translatable("fzh.loaded");
		
		int textWidth = client.textRenderer.getWidth(customText);
		int x = client.getWindow().getWidth() - textWidth - 2;
		int y = client.getWindow().getHeight() - 20;
		
		if (FzhClient.DEBUG) {
			ButtonWidget debugButton = ButtonWidget.builder(Text.literal("Debug"), button -> client.send(() -> client.setScreen(new DebugScreen(this))))
				.dimensions(0, 0, 50, 20)
				.build();
			
			addDrawableChild(debugButton);
		}
		
		context.drawTextWithShadow(client.textRenderer, customText, x, y, 0xFFFFFF);
	}
	
}
