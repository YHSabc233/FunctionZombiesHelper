package com.yhsabc233.fzh.function;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;

public class ShowToast {
    public static void create(Text title, Text description) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return;
        }
        
        ToastManager toastManager = client.getToastManager();
        SystemToast.add(toastManager, SystemToast.Type.NARRATOR_TOGGLE, title, description);
    }
}
