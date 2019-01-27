package tl.ox.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

public class EV_CatchFire implements Listener {

	@EventHandler
	public void onCatch(BlockIgniteEvent e) {
		if (e.getCause() == IgniteCause.LIGHTNING) {
			e.setCancelled(true);
		}
	}
	
}
