package top.yhsabc233.fzh.util;

import net.minecraft.client.MinecraftClient;

@SuppressWarnings("unused")
public class LanguageUtils {
    public static String getCurrentLanguage() {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.getLanguageManager().getLanguage().toLowerCase();
    }
}
