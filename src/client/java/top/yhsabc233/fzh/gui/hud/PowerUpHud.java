package top.yhsabc233.fzh.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import top.yhsabc233.fzh.config.FzhConfig;

import java.util.List;

@SuppressWarnings("unused")
public class PowerUpHud {
	// TODO: 实现powerup检测并显示
	@SuppressWarnings("deprecation")
	public static void init() {
		HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
			powerupRender(drawContext)
		);
	}
	
	public static void powerupRender(DrawContext context) {
		if (FzhConfig.CONFIG.isEnabled) {
			MinecraftClient client = MinecraftClient.getInstance();
			if (client.world == null || client.player == null) {
				return;
			}
			List<AbstractClientPlayerEntity> players = client.world.getPlayers();
		}
	}
}
