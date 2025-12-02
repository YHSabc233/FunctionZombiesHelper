package com.yhsabc233.fzh.config;

public class FzhConfig {
    /**配置文件版本*/
    public int version = 2;
    
    /**HUD显示X坐标*/
    public int hpdpDisplayX = 10;
    /**HUD显示Y坐标*/
    public int hpdpDisplayY = 10;
    /**模组全局开关*/
    public boolean isEnabled = true;
    
    /**HPDP最多可显示的玩家数量*/
    public int maxPlayersToShow = 8;
    /**配色方案*/
    public String colorScheme = "TEXT";
    /**HPDP显示模式*/
    public String displayMode = "HP";
    /**HPDP显示位置*/
    public String position = "CUSTOM";
    /**文本间距*/
    public int textMargin = 10;
    /**HUD隐藏时是否保持显示*/
    public boolean alwaysDisplayed = false;
    /**数值是否显示在玩家名称前*/
    public boolean valueBeforeName = false;
    /**什么时候自动启用FZH*/
    public String displayWhen = "ALWAYS";
    
    public boolean playerHiddenSwitch = false;
    //for normally config. /\
    
    
    //for mod's config. \/
    public int gameRound = 1;
    public int playerGold = 0;
    public boolean sstSwitch = false;
    public boolean canDisplay = true;

    public static FzhConfig createDefault(){return new FzhConfig();}
    public static void init() {FzhConfigManager.loadConfig();}
}
