package mt.tribordia.grades.guis;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface GuiBuilder {
	String name(Player player);
	int lines();
	void content(Player player, Inventory inventory);
	void click(Player player, Inventory inventory, ItemStack item, int slot);
}
