package top.yhsabc233.fzh.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FzhConfigYaclApi {
	
	private static final FzhConfig config = FzhConfig.CONFIG;
	
	public static Screen createScreen(Screen parent) {
		return YetAnotherConfigLib.createBuilder()
			.title(translatable("fzh.screen.config.title"))
			.category(ConfigCategory.createBuilder()
				.name(translatable("fzh.screen.config.title"))
				.tooltip(translatable("fzh.screen.config.description"))
				
				.group(createHpdpGroup())
				.group(createGlobalGroup())
				
				.build())
			.save(FzhConfigManager::saveConfig)
			.build()
			.generateScreen(parent);
	}
	
	// ---------- 按功能拆分的组构建方法 ----------
	private static OptionGroup createHpdpGroup() {
		return OptionGroup.createBuilder()
			.name(translatable("fzh.config.group.hpdp.name"))
			.description(OptionDescription.of(translatable("fzh.config.group.hpdp.description")))
			
			.option(booleanOption("fzh.config.hpdpSwitch", () -> config.hpdpSwitch, v -> config.hpdpSwitch = v, true))
			.option(intOption("fzh.config.hpdpDisplayX", () -> config.hpdpDisplayX, v -> config.hpdpDisplayX = v, 10))
			.option(intOption("fzh.config.hpdpDisplayY", () -> config.hpdpDisplayY, v -> config.hpdpDisplayY = v, 30))
			.option(intOption("fzh.config.maxPlayersToShow", () -> config.maxPlayersToShow, v -> config.maxPlayersToShow = v, 10))
			.option(booleanOption("fzh.config.valueBeforeName", () -> config.valueBeforeName, v -> config.valueBeforeName = v, true))
			
			.build();
	}

	private static OptionGroup createGlobalGroup() {
		return OptionGroup.createBuilder()
			.name(translatable("fzh.config.group.global.name"))
			.description(OptionDescription.of(translatable("fzh.config.group.global.description")))
			
			.option(booleanOption("fzh.config.alwaysDisplayed", () -> config.alwaysDisplayed, v -> config.alwaysDisplayed = v, false))
			.option(ButtonOption.createBuilder()
				.name(translatable("fzh.config.positionModifyScreen.name"))
				.description(OptionDescription.of(translatable("fzh.config.positionModifyScreen.description")))
				.action((yaclScreen, buttonOption) -> MinecraftClient.getInstance().setScreen(new PositionModifyScreen(yaclScreen)))
				.build())
			.option(booleanOption("fzh.config.isEnabled", () -> config.globalSwitch, v -> config.globalSwitch = v, true))
			.option(intOption("fzh.config.textMargin", () -> config.textMargin, v -> config.textMargin = v, 10))
			
			.build();
	}
	
	// ---------- 通用辅助方法，消灭重复代码 ----------
	private static Option<Boolean> booleanOption(String key, Supplier<Boolean> getter, Consumer<Boolean> setter, boolean defaultVal) {
		return Option.<Boolean>createBuilder()
			.name(translatable(key + ".name"))
			.description(OptionDescription.of(translatable(key + ".description")))
			.binding(defaultVal, getter, setter)
			.controller(TickBoxControllerBuilder::create)
			.build();
	}
	
	private static Option<Integer> intOption(String key, Supplier<Integer> getter, Consumer<Integer> setter, int defaultVal) {
		return Option.<Integer>createBuilder()
			.name(translatable(key + ".name"))
			.description(OptionDescription.of(translatable(key + ".description")))
			.binding(defaultVal, getter, setter)
			.controller(IntegerFieldControllerBuilder::create)
			.build();
	}
	
	private static Text translatable(String key) {
		return Text.translatable(key);
	}
}