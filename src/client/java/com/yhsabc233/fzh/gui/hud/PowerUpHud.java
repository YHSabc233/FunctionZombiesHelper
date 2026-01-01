package com.yhsabc233.fzh.gui.hud;

import com.yhsabc233.fzh.config.FzhConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class PowerUpHud {
	// TODO: 实现powerup检测并显示。
    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
                powerupRender(drawContext)
        );
    }
    
    public static void powerupRender(DrawContext context) {
        if (FzhConfig.CONFIG.isEnabled) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null) {
                return;
            }
            if (client.player == null) {
                return;
            }
            List<AbstractClientPlayerEntity> players = client.world.getPlayers();
            TextRenderer renderer = client.textRenderer;
            NbtCompound nbt = new NbtCompound();
            nbt.put("Id", Identifier.CODEC, Identifier.of("gold_block"));
            Optional<Identifier> id = nbt.get("Id", Identifier.CODEC);
            for (AbstractClientPlayerEntity player : players);
        }
    }
}
