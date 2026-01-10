package top.yhsabc233.fzh.config;

import top.yhsabc233.fzh.FzhClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FzhConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "fzh.json";
    public static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();
    
    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                FzhConfig loadedConfig = GSON.fromJson(reader, FzhConfig.class);
                
                if (loadedConfig != null) {
                    FzhConfig.CONFIG = loadedConfig;
					if (loadedConfig.displayMode == "HEALTH") {
						FzhConfig.CONFIG.displayMode = "HP";
						saveConfig();
					} else if (loadedConfig.displayMode == "DISTANCE") {
						FzhConfig.CONFIG.displayMode = "DIST";
						saveConfig();
					}
	                
	                if (FzhConfig.CONFIG.version < 2) {
                        saveConfig();
                    }
                    
                } else {
                    saveConfig();
                }
                
            } catch (Exception e) {
                FzhClient.LOGGER.error("[FZH] Failed to load config, using default. beacuse ", e);
                saveConfig();
            }
        } else {
            saveConfig();
        }
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void saveConfig() {
        CONFIG_FILE.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(FzhConfig.CONFIG, writer);
        } catch (Exception e) {
            FzhClient.LOGGER.error("[FZH] Failed to save config file! beacuse ", e);
        }
    }
}