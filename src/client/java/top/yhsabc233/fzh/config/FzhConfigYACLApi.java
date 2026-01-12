package top.yhsabc233.fzh.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class FzhConfigYACLApi {
	
	private static final FzhConfig config = FzhConfig.CONFIG;
	
	public static Screen createScreen(Screen parent) {
		return YetAnotherConfigLib.createBuilder()
			.title(Text.literal("FZH Config Title"))
			.category(ConfigCategory.createBuilder()
				.name(Text.translatable("fzh.screen.config.title"))
				.tooltip(Text.literal("基于 YACL 模组制作的全新 FZH 配置修改界面。"))
				
				.group(OptionGroup.createBuilder()
					.name(Text.literal("血量显示 配置"))
					.description(OptionDescription.of(Text.literal("FZH的血量显示配置。")))
					
					/*.option(
						Option.<Boolean>createBuilder()
							.name(Text.literal("血量显示 功能开关"))
							.description(OptionDescription.of(Text.literal("调整FZH的血量显示功能开关状态。")))
							.binding(true, () -> config.hpdpSwitch, newVal -> config.hpdpSwitch = newVal)
							.controller(TickBoxControllerBuilder::create)
							.build())*/
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
							.description(OptionDescription.of(Text.literal("设置血量显示的配色方案\n注：因技术原因，需手动输入“both”，“icon”，“text”三个选项。\n填其它内容均会导致血量显示失效。")))
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
					.description(OptionDescription.of(Text.literal("FZH的全局配置。")))
					
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
					
					.build())
				
				.build())
			.save(FzhConfigManager::saveConfig)
			.build()
			.generateScreen(parent);
	}
}
