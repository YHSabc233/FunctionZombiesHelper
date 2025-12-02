package com.yhsabc233.fzh.config;

import com.mojang.brigadier.context.CommandContext;
import com.yhsabc233.fzh.FzhClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yhsabc233.fzh.command.FzhCommand;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FzhConfigManager {
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "fzh.json";
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();
    public static FzhConfig CONFIG = FzhConfig.createDefault();
    
    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                FzhConfig loadedConfig = GSON.fromJson(reader, FzhConfig.class);
                
                if (loadedConfig != null) {
                    CONFIG = loadedConfig;
                    
                    if (CONFIG.version < 2) {
                        FzhClient.LOGGER.warn("[FZH] config version is outdated! upgrading...");
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
    
    // 游戏内可视化版本loadConfig();
    public static void reloadConfig(CommandContext<FabricClientCommandSource> context) {
        loadConfig();
        FzhCommand.sendFeedback(context.getSource(), Text.translatable("fzh.message.reload"), true, true);
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void saveConfig() {
        
        CONFIG_FILE.getParentFile().mkdirs();
        
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(CONFIG, writer);
        } catch (Exception e) {
            FzhClient.LOGGER.error("[FZH] Failed to save config file! beacuse ", e);
        }
    }
}