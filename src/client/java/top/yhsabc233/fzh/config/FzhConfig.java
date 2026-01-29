package top.yhsabc233.fzh.config;

@SuppressWarnings("CanBeFinal")
public class FzhConfig {
	// 此类提及的内容均为默认值
	
	// Config Version
	public int version = 5;
	
	// HPDP Config
	public int hpdpDisplayX = 10;
	public int hpdpDisplayY = 30;
	public boolean hpdpSwitch = true;
	public int maxPlayersToShow = 8;
	public String displayMode = "hp";
	public boolean valueBeforeName = true;
	
	// ZHF Config
	public boolean zhfSwitch = false;
	public int zhfDisplayX = 10;
	public int zhfDisplayY = 10;
	
	// Global Config
	public boolean globalSwitch = true;
	public int textMargin = 10;
	public boolean alwaysDisplayed = false;
	
    public static FzhConfig createDefault(){return new FzhConfig();}
    public static FzhConfig CONFIG = FzhConfig.createDefault();
    public static void init() {FzhConfigManager.loadConfig();}
}
