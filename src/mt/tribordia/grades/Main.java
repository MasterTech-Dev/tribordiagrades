package mt.tribordia.grades;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import mt.tribordia.grades.events.EventPlayerChat;
import mt.tribordia.grades.events.EventPlayerJoin;
import mt.tribordia.grades.events.EventPlayerQuit;
import mt.tribordia.grades.guis.list.GUIGradeMain;
import mt.tribordia.grades.managers.AccountManager;
import mt.tribordia.grades.managers.ConsoleManager;
import mt.tribordia.grades.managers.GradeManager;
import mt.tribordia.grades.managers.GuiManager;
import mt.tribordia.grades.managers.LanguageManager;

public class Main extends JavaPlugin {
	/// FIELDS
	// > Global
	public static Main INSTANCE; // Default class instance
	public Map<UUID, PermissionAttachment> attachments; // Players permissions
	public Map<Player, String> create = new HashMap<>(),   // Create a grade
																modify = new HashMap<>(),  // Modify a grade
																delete = new HashMap<>();   // Delete a grade
	private Scoreboard tabOrder; // Default tab order
	// > Managers
	private ConsoleManager consoleManager; // Console manager
	private LanguageManager languageManager; // Languages manager
	private GradeManager gradeManager; // Grades manager
	private AccountManager accountManager; // Accounts manager
	private GuiManager guiManager; // GUIs manager
	// > Settings
	private boolean logs, // Display logs ?
										guis; // Use GUI ?
	private String prefix, // Plugin's prefix
									lang; // Default language
	private File configurationFile; // Configuration file
	private FileConfiguration configuration; // Configuration access
	// > Grades
	private File gradesFile; // Grades file
	private FileConfiguration gradesConfig; // Grades configuration access
	
	/// WHEN THE PLUGIN STARTS
	@Override
	public void onEnable() {
		super.onEnable(); // Optimization
		INSTANCE = this; // Instance initialization
		this.attachments = new HashMap<>(); // Permissions attachments initialization
		
		this.loadManagers(); // Load managers
		this.loadLangs(); // Load languages
		this.loadSettings(); // Load settings
		this.loadGrades(); // Load grades
		this.loadAccounts(); // Load accounts
		this.loadGuis(); // Load guis
		this.loadEvents(); // Load events
		this.loadCommands(); // Load commands
	}
	/// WHEN THE PLUGIN SHUTDOWN
	@Override
	public void onDisable() {
		super.onDisable(); // Optimization
		this.unloadAccounts(); // Unload accounts
	}
	
	/// FUNCTIONS
	private void loadSettings() {
		this.configurationFile = new File(this.getDataFolder() + "/settings", "configuration.yml"); // Load settings file
		if (!this.configurationFile.exists()) this.saveResource("settings/configuration.yml", false); // Create it if does not exists
		this.configuration = YamlConfiguration.loadConfiguration(this.configurationFile); // Load settings configuration access
		
		if (!new File(this.getDataFolder() + "/assigns").exists()) new File(this.getDataFolder() + "/assigns").mkdir(); 

		this.consoleManager.sendInfo("Loading all settings...", true); // *LOG*
		this.prefix =this. configuration.getString("prefix") + (this.configuration.getString("prefix").endsWith(" ") ? "" : " "); // Load prefix appearance
		this.logs = this.configuration.getBoolean("logs"); // Load logs display
		this.guis = this.configuration.getBoolean("guis"); // Load GUIs usage
		this.lang = this.configuration.getString("lang"); // Load default language
		this.consoleManager.sendInfo(this.languageManager.getMessage(this.lang, "logs.settings_loaded"), true); // *LOG*
	}
	private void loadManagers() {
		this.consoleManager = new ConsoleManager(this); // Create the console manager
		this.languageManager = new LanguageManager(this); // Create the languages manager
		this.gradeManager = new GradeManager(this); // Create the grades manager
		this.accountManager = new AccountManager(this); // Create the accounts manager
		this.guiManager = new GuiManager(); // Create the GUIs manager
	}
	private void loadLangs() {
		this.consoleManager.sendInfo("Loading all languages files...", true); // *LOG*
		this.saveResource("settings/langs/en.yml", false); // Create / Update English file
		this.saveResource("settings/langs/fr.yml", false); // Create / Update French file
		this.consoleManager.sendInfo("All languages loaded (2)", true); // *LOG*
	}
	private void loadGuis() {
		this.guiManager.create(new GUIGradeMain(this));
	}
	private void loadGrades() {
		tabOrder = Bukkit.getScoreboardManager().getNewScoreboard();
		this.gradesFile = new File(this.getDataFolder() + "/settings", "grades.yml");
		if (!this.gradesFile.exists()) this.saveResource("settings/grades.yml", false);
		this.gradesConfig = YamlConfiguration.loadConfiguration(this.gradesFile);
		
		for (String key : this.gradesConfig.getKeys(false)) if (this.gradeManager.isReady(key)) this.gradeManager.load(key);
	}
	private void loadAccounts() {
		for (Player player : Bukkit.getOnlinePlayers()) this.accountManager.login(player);
	}
	private void unloadAccounts() {
		for (Player player : Bukkit.getOnlinePlayers()) this.accountManager.logout(player);
	}
	private void loadEvents() {
		PluginManager pluginManager = this.getServer().getPluginManager();
		
		pluginManager.registerEvents(new EventPlayerJoin(this), this);
		pluginManager.registerEvents(new EventPlayerQuit(this), this);
		pluginManager.registerEvents(new EventPlayerChat(this), this);
		pluginManager.registerEvents(this.guiManager, this);
	}
	private void loadCommands() {
		
	}
	public void saveGradesConfig() {
		try {
			this.gradesConfig.save(this.gradesFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/// GETTERS
	// > Global
	public Scoreboard getTabOrder() {
		return this.tabOrder;
	}
	// > Managers
	public ConsoleManager getConsoleManager() {
		return this.consoleManager;
	}
	public LanguageManager getLanguageManager() {
		return this.languageManager;
	}
	public GradeManager getGradeManager() {
		return this.gradeManager;
	}
	public AccountManager getAccountManager() {
		return this.accountManager;
	}
	public GuiManager getGuiManager() {
		return this.guiManager;
	}
	// > Settings
	public File getConfigurationFile() {
		return this.configurationFile;
	}
	public FileConfiguration getConfiguration() {
		return this.configuration;
	}
	public String getPrefix() {
		return this.prefix.replace("&", "§");
	}
	public boolean useGuis() {
		return this.guis;
	}
	public boolean displayLogs() {
		return this.logs;
	}
	public String getDefaultLang() {
		return this.lang;
	}
	// > Grades
	public File getGradesFile() {
		return this.gradesFile;
	}
	public FileConfiguration getGradesConfig() {
		return this.gradesConfig;
	}
}
