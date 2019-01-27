package tl.ox.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EV_Click implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			if (((Player) e.getWhoClicked()).getGameMode() == GameMode.ADVENTURE) {
				e.setCancelled(true);
			}
		}
	}
	
}
