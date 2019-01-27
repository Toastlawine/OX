package tl.ox.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EV_Interact implements Listener {

	public static Material[] black = { Material.BED_BLOCK, Material.CHEST, Material.ENDER_CHEST, Material.HOPPER,
			Material.WORKBENCH, Material.FURNACE, Material.TRAPPED_CHEST, Material.DROPPER, Material.DISPENSER,
			Material.ANVIL, Material.ENCHANTMENT_TABLE, Material.COMMAND, Material.BEACON };
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.ADVENTURE) {
			try {
			if (!mayInteract(e.getClickedBlock().getType())) {
				e.setCancelled(true);
			}
			} catch(NullPointerException ex) {
			}
		}
	}
	
	public static boolean mayInteract(Material m) {
		for (Material b : black) {
			if (b == m) {
				return false;
			}
		}
		return true;
	}
	
}
