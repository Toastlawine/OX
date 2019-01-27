package tl.ox.listeners;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tl.ox.enums.EN_State;
import tl.ox.main.Game;
import tl.ox.methods.MT_Player;
import tl.ox.methods.MT_Tablist;
import tl.ox.storage.ST_String;

public class EV_Join implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(Game.getLobby());
		p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
		MT_Player.clear(p, true);
		if (Game.isTablist())
			MT_Tablist.setTablist(p);
		if (Game.getState() == EN_State.LOBBY) {
			p.setLevel(Game.getS());
			HashMap<Player, Integer> ps = Game.getPs();
			if (ps.size() < Game.getMaxP()) {
				ps.put(p, Game.getLives());
				Game.messageAll(ST_String.getPF() + "§b" + p.getName() + "§a hat das Spiel betreten! §b[" + ps.size()
						+ "/" + Game.getMaxP() + "]", false);
				int needed = Game.getMinP() - ps.size();
				if (needed > 0) {
					if (needed == 1) {
						Game.messageAll(ST_String.getPF() + "§aEs wird noch §bein§a Spieler zum starten benötigt.",
								false);
					} else
						Game.messageAll(
								ST_String.getPF() + "§aEs werden noch §b" + needed + "§a Spieler zum starten benötigt.",
								false);
				} else {
					if (ps.size() == Game.getMinP()) {
						Game.startTimer();
					}
				}
				if (ps.size() == Game.getMaxP()) {
					if (Game.getS() > 10) {
						Game.setS(10);
					}
				}
			} else
				p.kickPlayer(ST_String.getPF()
						+ "§cDie Runde ist leider voll! §bDu kannst in kürze als Spectator beitreten!");
		} else {
			Game.makeSpectator(p);
			p.teleport(Game.getSpawn());
			p.sendMessage(ST_String.getPF()+"§aDas Spiel läuft bereits. Daher bist du Spectator.");
		}
	}

}
