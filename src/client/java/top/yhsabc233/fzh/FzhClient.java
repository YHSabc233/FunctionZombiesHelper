package top.yhsabc233.fzh;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.yhsabc233.fzh.command.FzhCommand;
import top.yhsabc233.fzh.config.FzhConfig;
import top.yhsabc233.fzh.gui.hud.HealthDisplayHud;

public class FzhClient implements ClientModInitializer {
 
	public static final String MOD_ID = "yhs_fzh";
    private static final boolean MOD_IS_BETA_VERSION = true;
    private boolean IS_BETA_TIPS_SHOWN = false;
	
    public static final Logger LOGGER = LoggerFactory.getLogger("FZH");
    
    @Override
    public void onInitializeClient() {
        try {
	        FzhCommand.init();
            FzhConfig.init();
	        
	        HealthDisplayHud.init();
	        //TimerHud.init();
            
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
					//ShowSpawnTimeHud.init();
					//PowerUpHud.init();
				}
            }));
            
            LOGGER.info("[FZH] Function Zombies Helper loaded.");
        } catch (Exception exception) {
            LOGGER.error("[FZH] Failed to load Function Zombies Helper! beacuse: {}", String.valueOf(exception));
        }
    }
}