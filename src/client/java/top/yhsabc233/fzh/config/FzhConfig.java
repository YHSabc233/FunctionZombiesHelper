package top.yhsabc233.fzh.config;

@SuppressWarnings({"CanBeFinal", "unused"})
public class FzhConfig {
	// Config Version
	public int version = 5;
	
	// HPDP Config
	public int hpdpDisplayX = 10;
	public int hpdpDisplayY = 10;
	public boolean hpdpSwitch = true;
	public int maxPlayersToShow = 8;
	public String colorScheme = "both";
	public String displayMode = "hp";
	public String position = "custom";
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
	public boolean timerSwitch = false;
	public int timerDisplayX = 50;
	public int timerDisplayY = 50;
	
	// ZHF Config
	public boolean zhfSwitch = false;
	
	// Global Config
	public boolean isEnabled = true;
	public int textMargin = 10;
	public boolean alwaysDisplayed = false;
	public String displayWhen = "always";
	
	// Features Toggle (almost Key Binding)
	public boolean playerHiddenSwitch = false;
	
    public static FzhConfig createDefault(){return new FzhConfig();}
    public static FzhConfig CONFIG = FzhConfig.createDefault();
    public static void init() {FzhConfigManager.loadConfig();}
}
