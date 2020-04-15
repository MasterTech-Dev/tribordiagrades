package mt.tribordia.grades.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import mt.tribordia.grades.Main;

public class EventPlayerJoin implements Listener {
	protected final Main main;
	
	public EventPlayerJoin(Main main) {
		this.main = main;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void playerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		
		boolean first = !this.main.getAccountManager().isRegistered(player.getName());
		this.main.getAccountManager().login(player);
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers())
					p.sendMessage(main.getPrefix() + main.getLanguageManager().getMessage(main.getLanguageManager().getLang(p), "events.join." + (first ? "first_join_message" : "join_message")).replace("%player%", player.getName()));
			}
		}.runTaskLater(main, 10L);
	}
}
