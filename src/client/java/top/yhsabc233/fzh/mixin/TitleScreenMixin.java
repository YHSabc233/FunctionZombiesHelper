package top.yhsabc233.fzh.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Text title) {
		super(title);
	}
	
	@Inject(at = @At("RETURN"), method = "render")
	private void fzhLoadedTextRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		var client = MinecraftClient.getInstance();
		Text customText = Text.translatable("fzh.loaded");
		
		int textWidth = client.textRenderer.getWidth(customText);
		
		int x = client.getWindow().getWidth() - textWidth - 2;
		int y = client.getWindow().getHeight() - 20;
		
		context.drawTextWithShadow(client.textRenderer, customText, x, y, 0xFFFFFF);
	}
}
