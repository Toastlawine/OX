package tl.ox.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tl.ox.enums.EN_State;
import tl.ox.main.Game;
import tl.ox.storage.ST_String;

public class EV_Quit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		HashMap<Player, Integer> ps = Game.getPs();
		if (ps.containsKey(p)) {
			ps.remove(p);
			if (Game.getState() == EN_State.LOBBY) {
				Game.messageAll(ST_String.getPF() + "§b" + p.getName() + "§a hat das Spiel verlassen! §b[" + ps.size()
						+ "/" + Game.getMaxP() + "]", false);
				
				if (ps.size() < Game.getMinP()) {
					Game.cancelTimer();
					Game.messageAll(ST_String.getPF() + "§cCountdown abgebrochen.", false);
					Game.setAllLevels(60);
					Game.setS(60);
				}
			} else if (Game.getState() == EN_State.INGAME) {
				Game.messageAll(ST_String.getPF()+"§b"+p.getName()+"§a hat das Spiel verlassen!", true);
				Game.checkEnd();
			}
		}
		Game.getSpecs().remove(p);
	}

}
