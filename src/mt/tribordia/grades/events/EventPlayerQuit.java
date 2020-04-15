package mt.tribordia.grades.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import mt.tribordia.grades.Main;

public class EventPlayerQuit implements Listener {
	protected final Main main;
	
	public EventPlayerQuit(Main main) {
		this.main = main;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void playerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(null);

		for (Player p : Bukkit.getOnlinePlayers())
			p.sendMessage(this.main.getLanguageManager().getMessage(this.main.getLanguageManager().getLang(p), "events.leave.leave_message").replace("%player%", player.getName()));
	}
}
