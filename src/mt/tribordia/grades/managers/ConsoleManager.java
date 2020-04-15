package mt.tribordia.grades.managers;

import org.bukkit.ChatColor;

import mt.tribordia.grades.Main;

public class ConsoleManager {
	protected final Main main;
	
	public ConsoleManager(Main main) {
		this.main = main;
	}
	
	public void sendLog(String log) {
		this.main.getServer().getConsoleSender().sendMessage(log.replace("&", "§"));
	}
	public void sendInfo(String log, boolean prefix) {
		this.sendLog((prefix ? "&9[INFO]&b " : "&b") + ChatColor.stripColor(log.replace("&", "§")));
	}
	public void sendSuccess(String log, boolean prefix) {
		this.sendLog((prefix ? "&2[SUCCESS]&a " : "&a") + ChatColor.stripColor(log.replace("&", "§")));
	}
	public void sendWarn(String log, boolean prefix) {
		this.sendLog((prefix ? "&6[WARN]&e " : "&e") + ChatColor.stripColor(log.replace("&", "§")));
	}
	public void sendError(String log, boolean prefix) {
		this.sendLog((prefix ? "&4[ERROR]&c " : "&c") + ChatColor.stripColor(log.replace("&", "§")));
	}
}
