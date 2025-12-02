package com.yhsabc233.fzh.utils;

import net.minecraft.client.MinecraftClient;

public class LanguageUtils {
    public static String getGameLanguage() {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.getLanguageManager().getLanguage().toLowerCase();
    }
}
