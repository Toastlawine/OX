package tl.ox.storage;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ST_Items {

	public static ItemStack getStick() {
		ItemStack s = new ItemStack(Material.STICK, 1);
		ItemMeta sm = s.getItemMeta();
		sm.addEnchant(Enchantment.KNOCKBACK, 1, true);
		sm.setDisplayName("§5Knüppel");
		s.setItemMeta(sm);
		return s;
	}
	
}
