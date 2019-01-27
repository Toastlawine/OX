package tl.ox.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import tl.ox.main.Game;

public class EV_DamageByEntity implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				if (Game.isKnockstick()) {
					if (Game.isThinking()) {
						e.setDamage(0);
						return;
					}
				}
			}
			e.setCancelled(true);
		}
	}

}
