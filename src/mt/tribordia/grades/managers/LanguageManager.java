package mt.tribordia.grades.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import mt.tribordia.grades.Main;

public class LanguageManager {
	protected final Main main;
	
	public LanguageManager(Main main) {
		this.main = main;
	}
	
	public String getLang(Player player) {
		return YamlConfiguration.loadConfiguration(new File(this.main.getDataFolder() + "/assigns", player.getUniqueId().toString() + ".yml")).getString("lang");
	}
	public String getMessage(String lang, String key) {
		return YamlConfiguration.loadConfiguration(new File(this.main.getDataFolder() + "/settings/langs", lang + ".yml")).getString(key).replace("&", "§");
	}
	public List<String> getMessageList(String lang, String key) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File(this.main.getDataFolder() + "/settings/langs", lang + ".yml"));
		List<String> list = new ArrayList<>();
		for (String element : config.getStringList(key)) list.add(element.replace("&", "§"));
		return list;
	}
}
