package com.yhsabc233.fzh;

import com.yhsabc233.fzh.command.FzhCommand;
import com.yhsabc233.fzh.config.FzhConfig;
import com.yhsabc233.fzh.gui.hud.HealthDisplayHud;

import com.yhsabc233.fzh.gui.hud.TimerHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FzhClient implements ClientModInitializer {
 
	public static String MOD_ID = "yhs_fzh";
	public static final boolean DEBUG = false;
    private static final boolean MOD_IS_BETA_VERSION = true;
    private boolean IS_BETA_TIPS_SHOWN = false;
	
    public static final Logger LOGGER = LoggerFactory.getLogger("FZH");
    
    @Override
    public void onInitializeClient() {
        try {
			// initialize mod.
	        FzhCommand.init();
            FzhConfig.init();
            
            ClientPlayConnectionEvents.JOIN.register(((clientPlayNetworkHandler, packetSender, minecraftClient) ->{
				if (!minecraftClient.isInSingleplayer()) {
					if (MOD_IS_BETA_VERSION && !IS_BETA_TIPS_SHOWN) {
						SystemToast.add(
							minecraftClient.getToastManager(),
							SystemToast.Type.NARRATOR_TOGGLE,
							Text.translatable("fzh.tips.usingbetaversion.title"),
							Text.translatable("fzh.tips.usingbetaversion.description")
						);
						IS_BETA_TIPS_SHOWN = true;
					}
					if (DEBUG) {
						SystemToast.add(
							minecraftClient.getToastManager(),
							SystemToast.Type.NARRATOR_TOGGLE,
							Text.literal("DEBUG"),
							Text.literal("模组正以DEBUG模式运行。")
						);
					}
					HealthDisplayHud.init();
					//ShowSpawnTimeHud.init();
					//PowerUpHud.init();
					//TimerHud.init();
				}
            }));
            
            LOGGER.info("[FZH] Function Zombies Helper loaded.");
        } catch (Exception exception) {
            LOGGER.error("[FZH] Failed to load Function Zombies Helper! beacuse: {}", String.valueOf(exception));
        }
    }
}