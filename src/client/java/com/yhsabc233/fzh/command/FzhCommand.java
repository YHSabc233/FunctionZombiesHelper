package com.yhsabc233.fzh.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import com.yhsabc233.fzh.config.FzhConfigManager;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class FzhCommand {
    
    private static final Text PREFIX = Text.literal("[§l§cFZH§r§f] ").copy();
    
    public static void init(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                literal("lang")
                        .executes(context -> {
                            executeFailed(context);
                            return 1;
                        })
                        
                        .then(argument("language", StringArgumentType.word())
                                .suggests((context, builder) -> builder
                                        .suggest("en_us")
                                        .suggest("zh_cn")
                                        .buildFuture()
                                ).executes(context -> {
                                    String choose = StringArgumentType.getString(context, "language");
                                    try {
                                        MinecraftClient.getInstance().getLanguageManager().setLanguage(choose);
                                        MinecraftClient.getInstance().reloadResources();
                                    } catch (Exception ex) {
                                        sendFeedback(context.getSource(), Text.translatable("fzh.message.error.language", ex).formatted(Formatting.RED), true, false);
                                    }
                                    return 1;
                                })
                        )
        );
        
        dispatcher.register(
                literal("fzhhud")
                        .executes(context -> {
                            sendFeedback(context.getSource(), Text.translatable("fzh.message.unknown"), true, false);
                            return 1;
                        })
        );
        
        dispatcher.register(
                literal("fzh")
                        // 如果输入的指令无效就调用executeFailed(context);
                        .executes(context -> {
                            executeFailed(context);
                            return 1;
                        })
                        
                        .then(literal("switch")
                                .executes(context -> {
                                    executeFailed(context);
                                    return 1;
                                })
                                
                                .then(argument("options", StringArgumentType.word())
                                        .suggests((context, builder) -> builder
                                                .suggest("true")
                                                .suggest("false")
                                                .buildFuture()
                                        ).executes(context -> {
                                            FzhConfigManager.CONFIG.isEnabled = Boolean.parseBoolean(StringArgumentType.getString(context, "options"));
                                            FzhConfigManager.saveConfig();
                                            if (FzhConfigManager.CONFIG.isEnabled) {
                                                sendFeedback(context.getSource(), Text.translatable("fzh.enabled"), true, true);
                                            } else {
                                                sendFeedback(context.getSource(), Text.translatable("fzh.disabled"), true, true);
                                            }
                                            return 1;
                                        })
                                )
                        )
                        
                        // /fzh help
                        .then(literal("help")
                                .executes(context -> {
                                    sendFeedback(context.getSource(), Text.empty()
                                                    .append(Text.translatable("fzh.message.help_0"))
                                                    .append(Text.translatable("fzh.message.help_1"))
                                                    .append(Text.translatable("fzh.message.help_2"))
                                                    .append(Text.translatable("fzh.message.help_3"))
                                                    .append(Text.translatable("fzh.message.help_4"))
                                                    .append(Text.translatable("fzh.message.help_5"))
                                                    .append(Text.translatable("fzh.message.help_6"))
                                            , true, true);
                                    return 1;
                                })
                                
                                .then(argument("config", StringArgumentType.word())
                                        .suggests((context, builder) -> builder
                                                .suggest("position")
                                                .suggest("maxPlayersToShow")
                                                .suggest("textMargin")
                                                .buildFuture()
                                        ).executes(context -> {
                                            String type = StringArgumentType.getString(context, "config");
                                            switch (type) {
                                                case "position" -> sendFeedback(context.getSource(), Text.translatable("fzh.message.help.set_1"), true, true);
                                                case "maxPlayersToShow" -> sendFeedback(context.getSource(), Text.translatable("fzh.message.help.set_2"), true, true);
                                                case "textMargin" -> sendFeedback(context.getSource(), Text.translatable("fzh.message.help.set_3"), true, true);
                                            }
                                            return 1;
                                        })
                                )
                        )
                        
                        // /fzh reload
                        .then(literal("reload")
                                .executes(context -> {
                                    FzhConfigManager.reloadConfig(context);
                                    return 1;
                                }))
                        
                        // /fzh set
                        .then(literal("set")
                                .executes(context -> {
                                    executeFailed(context);
                                    return 1;
                                })
                                
                                // /fzh set maxPlayersToShow <value:int>
                                .then(literal("maxPlayersToShow")
                                        .executes(context -> {
                                            executeFailed(context);
                                            return 1;
                                        })
                                        
                                        .then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    FzhConfigManager.CONFIG.maxPlayersToShow = IntegerArgumentType.getInteger(context, "value");
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set", "maxPlayersToShow", FzhConfigManager.CONFIG.maxPlayersToShow), true, true);
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                        )
                                )
                                
                                // /fzh set colorScheme BOTH/ICON/TEXT
                                .then(literal("colorScheme")
                                        .executes(context -> {
                                            executeFailed(context);
                                            return 1;
                                        })
                                        
                                        .then(argument("value", StringArgumentType.word())
                                                .suggests((context, builder) -> builder
                                                        .suggest("BOTH")
                                                        .suggest("ICON")
                                                        .suggest("TEXT")
                                                        .buildFuture()
                                                ).executes(context -> {
                                                    FzhConfigManager.CONFIG.colorScheme = StringArgumentType.getString(context, "value");
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set", "colorScheme", FzhConfigManager.CONFIG.colorScheme), true, true);
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                        )
                                )
                                
                                // /fzh set displayMode HEALTH/DISTANCE/PING
                                .then(literal("displayMode")
                                        .executes(context -> {
                                            executeFailed(context);
                                            return 1;
                                        })
                                        
                                        .then(argument("value", StringArgumentType.word())
                                                .suggests((context, builder) -> builder
                                                        .suggest("HP")
                                                        .suggest("DIST")
                                                        .suggest("PING")
                                                        .buildFuture()
                                                ).executes(context -> {
                                                    FzhConfigManager.CONFIG.displayMode = StringArgumentType.getString(context, "value");
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set", "displayMode", FzhConfigManager.CONFIG.displayMode), true, true);
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                        )
                                )
                                
                                // /fzh set position
                                .then(literal("position")
                                        // /fzh set position 直接执行的输出结果（也独立于executeSet_position）
                                        .executes(context -> {
                                            sendFeedback(context.getSource(), Text.translatable("fzh.message.nowPosition", FzhConfigManager.CONFIG.hpdpDisplayX, FzhConfigManager.CONFIG.hpdpDisplayY), true, true);
                                            return 1;
                                        })
                                        //)
                                        // /fzh set position CUSTOM <x> <y> (独立于executeSet_position)
                                        
                                        .then(argument("x", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    FzhConfigManager.CONFIG.position = "CUSTOM";
                                                    FzhConfigManager.CONFIG.hpdpDisplayX = IntegerArgumentType.getInteger(context, "x");
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set.positionCustom", FzhConfigManager.CONFIG.hpdpDisplayX, FzhConfigManager.CONFIG.hpdpDisplayY), true, true);
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                                .then(argument("y", IntegerArgumentType.integer())
                                                        .executes(context -> {
                                                            FzhConfigManager.CONFIG.position = "CUSTOM";
                                                            FzhConfigManager.CONFIG.hpdpDisplayX = IntegerArgumentType.getInteger(context, "x");
                                                            FzhConfigManager.CONFIG.hpdpDisplayY = IntegerArgumentType.getInteger(context, "y");
                                                            sendFeedback(context.getSource(), Text.translatable("fzh.message.set.positionCustom", FzhConfigManager.CONFIG.hpdpDisplayX, FzhConfigManager.CONFIG.hpdpDisplayY), true, true);
                                                            FzhConfigManager.saveConfig();
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                
                                // /fzh set alwaysDisplayed (指令名称原来是keepVisibleWhenHudHidden，然后由XMM010帮忙进行了简化，改成了AlwaysDisplayed  XD  )
                                .then(literal("alwaysDisplayed")
                                        .then(argument("value", StringArgumentType.word())
                                                .suggests((context, builder) -> builder
                                                        .suggest("true")
                                                        .suggest("false")
                                                        .buildFuture()
                                                ).executes(context -> {
                                                    FzhConfigManager.CONFIG.alwaysDisplayed = Boolean.parseBoolean(StringArgumentType.getString(context, "value"));
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set", "alwaysDisplayed", FzhConfigManager.CONFIG.alwaysDisplayed), true, true);
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                        )
                                )
                                
                                .then(literal("textMargin")
                                        .then(argument("value", IntegerArgumentType.integer()
                                                ).executes(context -> {
                                                    FzhConfigManager.CONFIG.textMargin = IntegerArgumentType.getInteger(context, "value");
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set", "textMargin", FzhConfigManager.CONFIG.textMargin), true, true);
                                                    if (FzhConfigManager.CONFIG.textMargin <= 7) {
                                                        sendFeedback(context.getSource(), Text.translatable("fzh.message.set.textMargin.warn").formatted(Formatting.YELLOW), false, false);
                                                    }
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                        )
                                )
                                /*
                                .then(literal("displayWhen")
                                        .then(argument("value", StringArgumentType.string())
                                                .suggests((context, builder) -> builder
                                                        .suggest("ALWAYS")
                                                        .suggest("GAME_STARTED")
                                                        .buildFuture()
                                                ).executes(context -> {
                                                    FzhConfigManager.CONFIG.displayWhen = StringArgumentType.getString(context, "value");
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set", "displayWhen", FzhConfigManager.CONFIG.displayWhen), true, true);
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                        )
                                )*/
                                
                                .then(literal("valueBeforeName")
                                        .then(argument("value", StringArgumentType.word())
                                                .suggests((context, builder) -> builder
                                                        .suggest("true")
                                                        .suggest("false")
                                                        .buildFuture()
                                                ).executes(context -> {
                                                    FzhConfigManager.CONFIG.valueBeforeName = Boolean.parseBoolean(StringArgumentType.getString(context, "value"));
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set", "valueBeforeName", FzhConfigManager.CONFIG.valueBeforeName), true, true);
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        
                        .then(literal("beta")
                                .executes(context -> {
                                    executeFailed(context);
                                    return 1;
                                })
                                
                                .then(literal("ShowSpawnTime")
                                        .then(argument("value", StringArgumentType.word())
                                                .suggests((context, builder) -> builder
                                                        .suggest("true")
                                                        .suggest("false")
                                                        .buildFuture()
                                                ).executes(context -> {
                                                    FzhConfigManager.CONFIG.sstSwitch = Boolean.parseBoolean(StringArgumentType.getString(context, "value"));
                                                    sendFeedback(context.getSource(), Text.translatable("fzh.message.set", "Show Spawn TIme", FzhConfigManager.CONFIG.sstSwitch), true, true);
                                                    FzhConfigManager.saveConfig();
                                                    return 1;
                                                })
                                        )
                                )
                        )
        );
    }
    
    public static void sendFeedback(FabricClientCommandSource source, MutableText content, boolean hasSoundEffect, boolean isSuccess) {
        Text message = PREFIX.copy().append(content);
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        source.sendFeedback(message);
        
        if (hasSoundEffect) {
            if (player != null && isSuccess) {
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 1f, 2f);
            } else if (player != null) {
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), 1f, 1f);
            }
        }
    }
    
    private static void executeFailed(CommandContext<FabricClientCommandSource> context) {
        sendFeedback(context.getSource(), Text.translatable("fzh.message.unknown").formatted(Formatting.RED), true, false);
    }
}
