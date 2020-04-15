package mt.tribordia.grades.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import mt.tribordia.grades.Main;
import mt.tribordia.grades.accounts.Account;
import mt.tribordia.grades.guis.list.GUIGradeMain;

public class EventPlayerChat implements Listener {
	protected final Main main;
	
	public EventPlayerChat(Main main) {
		this.main = main;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void playerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Account account = this.main.getAccountManager().get(player);
		String message = event.getMessage();
		String msg = message.toLowerCase().replace(" ", "_").replace("-", "_"); 
		
		if (this.main.create.containsKey(player)) {
			if (msg.toLowerCase().equals("x")) {
				this.main.create.remove(player);
				this.main.getGuiManager().open(player, GUIGradeMain.class);
				return;
			}
			if (this.main.getGradeManager().isCreated(msg)) {
				player.sendMessage(this.main.getPrefix() + this.main.getLanguageManager().getMessage(account.getLang(), "items.menu_grades_main.create.grade_already_created").replace("%grade%", msg));
				player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				return;
			}
			return;
		} else if (main.modify.containsKey(player)) {
			if (msg.toLowerCase().equals("x")) {
				this.main.modify.remove(player);
				this.main.getGuiManager().open(player, GUIGradeMain.class);
				return;
			}
			if (!this.main.getGradeManager().isCreated(msg)) {
				player.sendMessage(this.main.getPrefix() + this.main.getLanguageManager().getMessage(account.getLang(), "items.menu_grades_main.modify.grade_does_not_exist").replace("%grade%", msg));
				player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				return;
			}
			return;
		}
		
		if (player.hasPermission("tribordia.chat.mentions")) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (message.contains("@" + p.getName())) {
					message = message.replace("@" + p.getName(), "§b§l§n" + p.getName() + "§r");
					if (!player.getName().equals(p.getName())) {
						p.sendMessage(this.main.getPrefix() + this.main.getLanguageManager().getMessage(this.main.getLanguageManager().getLang(p), "chat.mention").replace("%player%", player.getName()));
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
					}
				}
			}
		}
		event.setFormat(account.getNameTag() + account.getGrade().getColouredSeparator() + (player.hasPermission("tribordia.chat.colors") ? message.replace("&", "§") : message));
	}
}
