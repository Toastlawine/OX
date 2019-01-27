package tl.ox.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import tl.ox.enums.EN_State;
import tl.ox.main.Game;
import tl.ox.main.Main;
import tl.ox.storage.ST_Items;

public class EV_Respawn implements Listener {

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (Game.getPs().containsKey(p)) {
					if (Game.getState() == EN_State.INGAME) {
						if (Game.isKnockstick()) {
							p.getInventory().setItem(0, ST_Items.getStick());
						}
						p.teleport(Game.getSpawn());
					} else {
						p.teleport(Game.getLobby());
					}
				} else {
					p.kickPlayer("§cKicked");
				}
			}
		}, 1);
	}
	
}
