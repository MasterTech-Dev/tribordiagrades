package mt.tribordia.grades.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import mt.tribordia.grades.Main;
import mt.tribordia.grades.guis.GuiBuilder;

public class GuiManager implements Listener {
	private final Map<Class<? extends GuiBuilder>, GuiBuilder> guis = new HashMap<>();
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		final Inventory inventory = event.getInventory();
		final ItemStack item = event.getCurrentItem();
		final int slot = event.getSlot();	
		
		if (inventory == null || item == null) return;
		
		this.guis.values().stream()
							.filter(menu -> inventory.getTitle().equals(menu.name(player).replace("&", "§")))
							.forEach(menu -> {
								event.setCancelled(true);
								menu.click(player, inventory, item, slot);
							});
	}
	
	public void create(GuiBuilder gui) {
		this.guis.put(gui.getClass(), gui);
	}
	public void open(Player player, Class<? extends GuiBuilder> gui) {
		GuiBuilder menu = this.guis.get(gui);
		Inventory inventory = Bukkit.createInventory(null, menu.lines() * 9, menu.name(player).replace("&", "§"));
		menu.content(player, inventory);
		new BukkitRunnable() {
			@Override
			public void run() {
				player.openInventory(inventory);
			}
		}.runTaskLater(Main.INSTANCE, 1L);
	}
}
