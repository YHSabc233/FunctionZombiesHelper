package com.yhsabc233.fzh.hud;

import com.yhsabc233.fzh.config.FzhConfigManager;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class PowerUpHud {
    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
                powerupRender(drawContext)
        );
    }
    
    public static void powerupRender(DrawContext context) {
        if (FzhConfigManager.CONFIG.isEnabled) {
            MinecraftClient client = MinecraftClient.getInstance();
            TextRenderer renderer = client.textRenderer;
            NbtCompound nbt = new NbtCompound();
            nbt.put("Id", Identifier.CODEC, Identifier.of("gold_block"));
            Optional<Identifier> id = nbt.get("Id", Identifier.CODEC);
        }
    }
}
