package mt.tribordia.grades.managers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemBuilder {
	private ItemStack item;
	
	public ItemBuilder(ItemStack item) {
		this.item = item;
	}
	public ItemBuilder(Material material) {
		this.item = new ItemStack(material, 1, (short) 0);
	}
	public ItemBuilder(Material material, int amount) {
		this.item = new ItemStack(material, amount, (short) 0);
	}
	public ItemBuilder(Material material, int amount, int data) {
		this.item = new ItemStack(material, amount, (short) data);
	}
	
	public ItemBuilder setName(String name) {
		ItemMeta meta = meta();
		meta.setDisplayName(name.replace("&", "§"));
		item.setItemMeta(meta);
		return this;
	}
	public ItemBuilder setLore(String... lore) {
		List<String> lores = new ArrayList<>();
		for (String l : Arrays.asList(lore)) lores.add(l.replace("&", "§"));
		return setLore(lores);
	}
	public ItemBuilder setLore(List<String> lores) {
		ItemMeta meta = meta();
		meta.setLore(lores);
		item.setItemMeta(meta);
		return this;
	}
	public ItemBuilder addEnchant(Enchantment enchant, int level, boolean ambient) {
		ItemMeta meta = meta();
		meta.addEnchant(enchant, level, ambient);
		item.setItemMeta(meta);
		return this;
	}
	public ItemBuilder addUnsafeEnchant(Enchantment enchant, int level) {
		item.addUnsafeEnchantment(enchant, level);
		return this;
	}
	public ItemBuilder addFlag(ItemFlag... flags) {
		ItemMeta meta = meta();
		for (ItemFlag flag : Arrays.asList(flags)) meta.addItemFlags(flag);
		item.setItemMeta(meta);
		return this;
	}
	public ItemBuilder setSkullOwner(String owner) {
		SkullMeta meta = skullMeta();
		meta.setOwner(owner);
		item.setItemMeta(meta);
		return this;
	}
	public ItemBuilder setLeatherArmorColor(Color color) {
		LeatherArmorMeta meta = leatherArmorMeta();
		meta.setColor(color);
		item.setItemMeta(meta);
		return this;
	}
	public ItemBuilder setSkullTexture(String texture) {
		SkullMeta meta = skullMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", texture));
		try {
			Field field = meta.getClass().getDeclaredField("profile");
			field.setAccessible(true);
			field.set(meta, profile);
		} catch(Exception e) {
			e.printStackTrace();
		}
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemStack create() {
		return this.item;
	}
	public ItemMeta meta() {
		return this.item.getItemMeta();
	}
	public SkullMeta skullMeta() {
		return (SkullMeta) meta();
	}
	public LeatherArmorMeta leatherArmorMeta() {
		return (LeatherArmorMeta) meta();
	}
}
