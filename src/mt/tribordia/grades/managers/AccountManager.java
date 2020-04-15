package mt.tribordia.grades.managers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import mt.tribordia.grades.Main;
import mt.tribordia.grades.accounts.Account;
import mt.tribordia.grades.grades.Grade;

@SuppressWarnings ("deprecation")
public class AccountManager {
	protected final Main main;
	private Map<UUID, Account> accounts;
	
	public AccountManager(Main main) {
		this.main = main;
		this.accounts = new HashMap<>();
	}
	
	public void login(Player player) {
		if (!this.isRegistered(player.getName())) this.create(player);
		else this.load(player);
	}
	private void create(Player player) {
		UUID uuid = player.getUniqueId();
		File file = new File(this.main.getDataFolder() + "/assigns", uuid.toString() + ".yml");
		try {
			file.createNewFile();
			new BukkitRunnable() {
				@Override
				public void run() {
					FileConfiguration config = YamlConfiguration.loadConfiguration(file);
					
					Grade grade = main.getGradeManager().getDefault();
					String lang = main.getDefaultLang();
					
					config.set("grade", grade.getName());
					config.set("lang", lang);
					
					try {
						config.save(file);
						accounts.put(uuid, new Account(uuid, grade, lang));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.runTaskLater(main, 5L);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void load(Player player) {
		UUID uuid = player.getUniqueId();
		File file = new File(this.main.getDataFolder() + "/assigns", uuid.toString() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		Grade grade = main.getGradeManager().get(config.getString("grade"));
		String lang = config.getString("lang");
		
		Account account = new Account(uuid, grade, lang);
		this.accounts.put(uuid, account);
		account.setNameTag();
		account.setPermissions();
	}
	public void reload(Player player) {
		Account account = this.get(player);
		account.removePermissions();
		account.setNameTag();
		account.setPermissions();
	}
	public void logout(Player player) {
		this.save(player);
		UUID uuid = player.getUniqueId();
		this.get(player).removePermissions();
		this.accounts.remove(uuid);
	}
	public void save(Player player) {
		Account account = this.get(player);
		
		UUID uuid = account.getOwner();
		Grade grade = account.getGrade();
		String lang = account.getLang();
		
		File file = new File(this.main.getDataFolder() + "/assigns", uuid.toString() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		try {
			config.set("grade", grade.getName());
			config.set("lang", lang);
			
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRegistered(String name) {
		return new File(main.getDataFolder() + "/assigns", Bukkit.getOfflinePlayer(name).getUniqueId().toString() + ".yml").exists();
	}
	public boolean isConnected(String name) {
		return this.accounts.containsKey(Bukkit.getOfflinePlayer(name).getUniqueId());
	}
	
	public Account get(Player player) {
		return this.accounts.get(player.getUniqueId());
	}
}
