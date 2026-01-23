package top.yhsabc233.fzh.config;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class FzhKeyBinding {
	public static KeyBinding zhfKey;
	//public static KeyBinding playerHiddenKey;
	
	public static void register() {
		zhfKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.fzh.zhf",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_V,
			"key.fzh.category"
		));
		
		/*playerHiddenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.fzh.playerHidden",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_H,
			"key.fzh.category"
		));*/
	}
	
}
