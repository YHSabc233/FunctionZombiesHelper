package top.yhsabc233.fzh.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class FzhConfigYaclApi {
	
	private static final FzhConfig config = FzhConfig.CONFIG;
	
	public static Screen createScreen(Screen parent) {
		return YetAnotherConfigLib.createBuilder()
			.title(Text.literal("FZH Config Title"))
			.category(ConfigCategory.createBuilder()
				.name(Text.translatable("fzh.screen.config.title"))
				.tooltip(Text.literal("基于 YACL 制作的新 FZH 配置界面。"))
				
				.group(OptionGroup.createBuilder()
					.name(Text.literal("血量显示 配置"))
					.description(OptionDescription.of(Text.literal("FZH 的血量显示配置。")))
					
					.option(
						Option.<Boolean>createBuilder()
							.name(Text.literal("血量显示 功能开关"))
							.description(OptionDescription.of(Text.literal("调整 FZH 的血量显示功能开关状态。")))
							.binding(true, () -> config.hpdpSwitch, newVal -> config.hpdpSwitch = newVal)
							.controller(TickBoxControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.options.config.hpdpDisplayX"))
							.description(OptionDescription.of(Text.literal("设置血量显示的X坐标。")))
							.binding(10, () -> config.hpdpDisplayX, newVal -> config.hpdpDisplayX = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.options.config.hpdpDisplayY"))
							.description(OptionDescription.of(Text.literal("设置血量显示的Y坐标。")))
							.binding(10, () -> config.hpdpDisplayY, newVal -> config.hpdpDisplayY = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					/*.option(
						Option.<String>createBuilder()
							.name(Text.translatable("fzh.options.config.colorScheme"))
							.description(OptionDescription.of(Text.literal("设置血量显示的配色方案。\n§l(因技术原因，需手动且只能输入§e“both”，“icon”，“text”§f三个选项。\n§l其余内容均会导致失效。)")))
							.binding("both", () -> config.colorScheme, newVal -> config.colorScheme = newVal)
							.controller(StringControllerBuilder::create)
							.build())*/
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.options.config.maxPlayersToShow"))
							.description(OptionDescription.of(Text.literal("设置血量显示的最大玩家显示数量。\n超过此数量会将其余玩家信息隐藏。")))
							.binding(10, () -> config.maxPlayersToShow, newVal -> config.maxPlayersToShow = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					
					.build())
				
				.group(OptionGroup.createBuilder()
					.name(Text.literal("全局 配置"))
					.description(OptionDescription.of(Text.literal("FZH 的全局配置。")))
					
					.option(
						Option.<Boolean>createBuilder()
							.name(Text.translatable("fzh.options.config.isEnabled"))
							.description(OptionDescription.of(Text.literal("调整FZH的全局开关状态。")))
							.binding(true, () -> config.isEnabled, newVal -> config.isEnabled = newVal)
							.controller(TickBoxControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.options.config.textMargin"))
							.description(OptionDescription.of(Text.literal("调整FZH的全局上下文本间距。")))
							.binding(10, () -> config.textMargin, newVal -> config.textMargin = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					.option(
						Option.<Boolean>createBuilder()
							.name(Text.translatable("key.fzh.zhf"))
							.description(OptionDescription.of(Text.literal("调整ZHF功能的开关状态。\n(如果聊天栏显示ZHF开启但实际是关着的，\n可以在这里手动调整修复。)")))
							.binding(false, () -> config.zhfSwitch, newVal -> config.zhfSwitch = newVal)
							.controller(TickBoxControllerBuilder::create)
							.build())
					
					.build())
				
				.build())
			.save(FzhConfigManager::saveConfig)
			.build()
			.generateScreen(parent);
	}
	
	public static Screen init(Screen parent) {
		return createScreen(parent);
	}
	
}
