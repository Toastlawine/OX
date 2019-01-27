package tl.ox.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import tl.ox.main.Game;
import tl.ox.storage.ST_String;

public class EV_Chat implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		if (e.getMessage().charAt(0) != '/') {
			if (Game.isInvisible()) {
				if (Game.isThinking()) {
					p.sendMessage(ST_String.getPF() + "§cDu darfst jetzt nicht schreiben!");
					return;
				}
			}
			Game.messageAll(ST_String.getPlayerPrefix(p)+e.getMessage(), true);
		}
	}

}
