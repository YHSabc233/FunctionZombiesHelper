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
public class TitleScreenMixin extends Screen{
	
	protected TitleScreenMixin(Text title) {
		super(title);
	}
	
	@Inject(at = @At("RETURN"), method = "render")
	private void fzhLoadedTextRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		MinecraftClient client = MinecraftClient.getInstance();
		TitleScreen screen = (TitleScreen)(Object)this;
		Text customText = Text.literal("FZH Loaded");
		
		int textWidth = client.textRenderer.getWidth(customText);
		
		int x = screen.width - textWidth - 2;
		int y = screen.height - 20;
		
		context.drawTextWithShadow(client.textRenderer, customText, x, y, 0xFFFFFF);
	}
}
