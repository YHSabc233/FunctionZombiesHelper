package com.yhsabc233.fzh.config;

public class FzhConfig {

    public int version = 3;
    
    // HPDP Config
    public int hpdpDisplayX = 10;
    public int hpdpDisplayY = 10;
	public boolean hpdpSwitch = true;
    public int maxPlayersToShow = 8;
    public String colorScheme = "TEXT";
    public String displayMode = "HP";
    public String position = "CUSTOM";
    public boolean valueBeforeName = true;
	public boolean hideSpectator = true;
    
    // SST Config
    public int sstDisplayX = 5;
    public int sstDisplayY = 50;
	public boolean sstSwitch = false;
    public int gameRound = 1;
    public int playerGold = 0;
    public boolean canDisplay = true;
    public boolean canReset = false;
	
	// Timer Config
	public int timerDisplayX = 50;
	public int timerDisplayY = 50;
	public boolean timerSwitch = false;
	
    // Global Config
    public boolean isEnabled = true;
    public int textMargin = 10;
    public boolean alwaysDisplayed = false;
    public String displayWhen = "ALWAYS";
    
    public boolean playerHiddenSwitch = false;

    public static FzhConfig createDefault(){return new FzhConfig();}
    public static FzhConfig CONFIG = FzhConfig.createDefault();
    public static void init() {FzhConfigManager.loadConfig();}
}
