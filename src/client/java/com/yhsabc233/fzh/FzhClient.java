package com.yhsabc233.fzh;

import com.yhsabc233.fzh.command.FzhCommand;
import com.yhsabc233.fzh.config.FzhConfig;
import com.yhsabc233.fzh.hud.HealthDisplayHud;
import com.yhsabc233.fzh.function.ShowToast;

import com.yhsabc233.fzh.hud.ShowSpawnTimeHud;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import net.minecraft.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FzhClient implements ClientModInitializer {
    /**模组是否为BETA版*/
    private static final boolean MOD_IS_BETA_VERSION = true;
    
    public static final String MOD_ID = "FZH";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public boolean IS_BETA_TIPS_SHOWN = false;
    
    @Override
    public void onInitializeClient() {
        try {
            
            ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> FzhCommand.init(dispatcher));
            
            FzhConfig.init();
            
            ClientPlayConnectionEvents.JOIN.register(((clientPlayNetworkHandler, packetSender, minecraftClient) -> {
                if (MOD_IS_BETA_VERSION) {
                    if (!IS_BETA_TIPS_SHOWN) {
                        ShowToast.create(
                                Text.translatable("fzh.tips.usingbetaversion.title"),
                                Text.translatable("fzh.tips.usingbetaversion.description"));
                        IS_BETA_TIPS_SHOWN = true;
                    }
                }
                HealthDisplayHud.init();
                ShowSpawnTimeHud.init();
                //PowerUpHud.init();
            }));
            
            LOGGER.info("[FZH] Function Zombies Helper Loaded.");
        } catch (Exception exception) {
            LOGGER.error("[FZH] Failed to load Function Zombies Helper! beacuse: {}", String.valueOf(exception));
        }
    }
}