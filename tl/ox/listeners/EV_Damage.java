package tl.ox.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EV_Damage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getCause() != DamageCause.VOID && e.getCause() != DamageCause.ENTITY_ATTACK
					&& e.getCause() != DamageCause.LIGHTNING) {
				e.setCancelled(true);
			} else if (e.getCause() == DamageCause.LIGHTNING) {
				e.setDamage(0);
			}
		}
	}

}
