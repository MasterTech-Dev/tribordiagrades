package mt.tribordia.grades.guis.list;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import mt.tribordia.grades.Main;
import mt.tribordia.grades.guis.GuiBuilder;
import mt.tribordia.grades.managers.ItemBuilder;

public class GUIGradeMain implements GuiBuilder {
	protected final Main main;
	
	public GUIGradeMain(Main main) {
		this.main = main;
	}
	
	@Override
	public String name(Player player) {
		return this.main.getLanguageManager().getMessage(this.main.getLanguageManager().getLang(player), "guis.menu_grade_main");
	}

	@Override
	public int lines() {
		return 1;
	}

	@Override
	public void content(Player player, Inventory inventory) {
		String lang = this.main.getLanguageManager().getLang(player);
		
		ItemBuilder create = new ItemBuilder(Material.WORKBENCH).setName(this.main.getLanguageManager().getMessage(lang, "items.menu_grades_main.create.name")).setLore(this.main.getLanguageManager().getMessageList(lang, "items.menu_grades_main.create.lores"));
		ItemBuilder modify = new ItemBuilder(Material.BOOK_AND_QUILL).setName(this.main.getLanguageManager().getMessage(lang, "items.menu_grades_main.modify.name")).setLore(this.main.getLanguageManager().getMessageList(lang, "items.menu_grades_main.modify.lores"));
		ItemBuilder list  = new ItemBuilder(Material.PAPER).setName(this.main.getLanguageManager().getMessage(lang, "items.menu_grades_main.list.name")).setLore(this.main.getLanguageManager().getMessageList(lang, "items.menu_grades_main.list.lores"));
		ItemBuilder delete = new ItemBuilder(Material.LAVA_BUCKET).setName(this.main.getLanguageManager().getMessage(lang, "items.menu_grades_main.delete.name")).setLore(this.main.getLanguageManager().getMessageList(lang, "items.menu_grades_main.delete.lores"));
		
		inventory.setItem(2, create.create());
		inventory.setItem(3, modify.create());
		inventory.setItem(5, list.create());
		inventory.setItem(6, delete.create());
	}

	@Override
	public void click(Player player, Inventory inventory, ItemStack item, int slot) {
		String lang = this.main.getLanguageManager().getLang(player);
		
		if (item.getType().equals(Material.WORKBENCH)) {
			if (!player.hasPermission("tribordia.grades.create")) {
				player.closeInventory();
				player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				player.sendMessage(this.main.getPrefix() + this.main.getLanguageManager().getMessage(lang, "item.menu_grades_main.create.no_permission"));
				return;
			}
			player.closeInventory();
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
			player.sendMessage(this.main.getPrefix() + this.main.getLanguageManager().getMessage(lang, "items.menu_grades_main.create.click_message"));
			this.main.create.put(player, "");
		} else if (item.getType().equals(Material.BOOK_AND_QUILL)) {
			if (!player.hasPermission("tribordia.grades.modify")) {
				player.closeInventory();
				player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				player.sendMessage(this.main.getPrefix() + this.main.getLanguageManager().getMessage(lang, "item.menu_grades_main.modify.no_permission"));
				return;
			}
			player.closeInventory();
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
			player.sendMessage(this.main.getPrefix() + this.main.getLanguageManager().getMessage(lang, "items.menu_grades_main.modify.click_message"));
			this.main.modify.put(player, "");
		} else if (item.getType().equals(Material.PAPER) ) {
			player.closeInventory();
		} else if (item.getType().equals(Material.LAVA_BUCKET)) {
			player.closeInventory();
		}
	}
}
