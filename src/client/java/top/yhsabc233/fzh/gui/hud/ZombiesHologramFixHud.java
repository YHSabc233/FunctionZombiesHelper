package top.yhsabc233.fzh.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import top.yhsabc233.fzh.FzhClient;
import top.yhsabc233.fzh.config.FzhConfig;

public class ZombiesHologramFixHud {
	private static final Identifier ZHF_HUD_LAYER = Identifier.of(FzhClient.MOD_ID, "zhf-hud-layer");
	private static final MinecraftClient client = MinecraftClient.getInstance();
	public static void register() {
		HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(
			IdentifiedLayer.CHAT,
			ZHF_HUD_LAYER,
			ZombiesHologramFixHud::zhfRender)
		);
	}
	
	private static void zhfRender(DrawContext context, RenderTickCounter ignoredTickCounter) {
		var displayText = "ZHF: " + (FzhConfig.CONFIG.zhfSwitch ? "§aON" : "§cOFF");
		
		context.drawTextWithShadow(client.textRenderer, displayText, FzhConfig.CONFIG.zhfDisplayX, FzhConfig.CONFIG.zhfDisplayY, Colors.YELLOW);
	}
	
}
