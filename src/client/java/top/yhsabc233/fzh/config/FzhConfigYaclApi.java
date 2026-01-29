package top.yhsabc233.fzh.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import top.yhsabc233.fzh.gui.screen.PositionModifyScreen;

public class FzhConfigYaclApi {
	
	private static final FzhConfig config = FzhConfig.CONFIG;
	
	public static Screen createScreen(Screen parent) {
		return YetAnotherConfigLib.createBuilder()
			.title(Text.translatable("fzh.screen.config.title"))
			.category(ConfigCategory.createBuilder()
				.name(Text.translatable("fzh.screen.config.title"))
				.tooltip(Text.translatable("fzh.screen.config.description"))
				
				.group(OptionGroup.createBuilder()
					.name(Text.translatable("fzh.config.group.hpdp.name"))
					.description(OptionDescription.of(Text.translatable("fzh.config.group.hpdp.description")))
					
					.option(
						Option.<Boolean>createBuilder()
							.name(Text.translatable("fzh.config.hpdpSwitch.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.hpdpSwitch.description")))
							.binding(true, () -> config.hpdpSwitch, newVal -> config.hpdpSwitch = newVal)
							.controller(TickBoxControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.config.hpdpDisplayX.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.hpdpDisplayX.description")))
							.binding(10, () -> config.hpdpDisplayX, newVal -> config.hpdpDisplayX = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.config.hpdpDisplayY.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.hpdpDisplayY.description")))
							.binding(30, () -> config.hpdpDisplayY, newVal -> config.hpdpDisplayY = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.config.maxPlayersToShow.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.maxPlayersToShow.description")))
							.binding(10, () -> config.maxPlayersToShow, newVal -> config.maxPlayersToShow = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					
					.build())
				
				.group(OptionGroup.createBuilder()
					.name(Text.translatable("fzh.config.group.zhf.name"))
					.description(OptionDescription.of(Text.translatable("fzh.config.group.zhf.description")))
					
					.option(
						Option.<Boolean>createBuilder()
							.name(Text.translatable("fzh.config.zhfSwitch.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.zhfSwitch.description")))
							.binding(false, () -> config.zhfSwitch, newVal -> config.zhfSwitch = newVal)
							.controller(TickBoxControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.config.zhfDisplayX.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.zhfDisplayX.description")))
							.binding(10, () -> config.zhfDisplayX, newVal -> config.zhfDisplayX = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.config.zhfDisplayY.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.zhfDisplayY.description")))
							.binding(10, () -> config.zhfDisplayY, newVal -> config.zhfDisplayY = newVal)
							.controller(IntegerFieldControllerBuilder::create)
							.build())
					
					.build())
				
				.group(OptionGroup.createBuilder()
					.name(Text.translatable("fzh.config.group.global.name"))
					.description(OptionDescription.of(Text.translatable("fzh.config.group.global.description")))
					
					.option(
						ButtonOption.createBuilder()
							.name(Text.translatable("fzh.config.positionModifyScreen.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.positionModifyScreen.description")))
							.action((yaclScreen, buttonOption) -> MinecraftClient.getInstance().setScreen(new PositionModifyScreen(yaclScreen)))
							.build())
					.option(
						Option.<Boolean>createBuilder()
							.name(Text.translatable("fzh.config.isEnabled.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.isEnabled.description")))
							.binding(true, () -> config.globalSwitch, newVal -> config.globalSwitch = newVal)
							.controller(TickBoxControllerBuilder::create)
							.build())
					.option(
						Option.<Integer>createBuilder()
							.name(Text.translatable("fzh.config.textMargin.name"))
							.description(OptionDescription.of(Text.translatable("fzh.config.textMargin.description")))
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
