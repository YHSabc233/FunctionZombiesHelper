package com.yhsabc233.fzh.util;

import net.minecraft.client.MinecraftClient;

public class LanguageUtils {
    public static String getCurrentLanguage() {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.getLanguageManager().getLanguage().toLowerCase();
    }
}
