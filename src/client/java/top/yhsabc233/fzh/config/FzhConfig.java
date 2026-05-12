package top.yhsabc233.fzh.config;

public class FzhConfig {
	
	// Config Version
	public final int version = 5;
	
	public int getVersion() { return version; }
	
	// HPDP Config
	public int hpdpDisplayX = 10;
	public int hpdpDisplayY = 30;
	public boolean hpdpSwitch = true;
	public int maxPlayersToShow = 8;
	public String colorScheme = "both";
	public boolean valueBeforeName = true;
	
	// Global Config
	public boolean globalSwitch = true;
	public int textMargin = 10;
	public boolean alwaysDisplayed = false;
	public boolean betaTipEnabled = true;
	
	// Runtime flags (not persisted)
	public static boolean positionModifying = false;
	
	public static FzhConfig createDefault() { return new FzhConfig(); }
	
	public static FzhConfig CONFIG = FzhConfig.createDefault();
	
	public static void init() { FzhConfigManager.loadConfig(); }
	
}
