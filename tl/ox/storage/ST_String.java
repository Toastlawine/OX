package tl.ox.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import tl.ox.main.Game;

public class ST_String {
	
	public static String getPlayerPrefix(Player p) {
		if (Game.getSpecs().contains(p)) {
			return "§7[Spectator] "+p.getName()+": ";
		} else {
			return "§f"+p.getName()+": ";
		}
	}
	
	public static String getWinMessage() {
		HashMap<Player, Integer> ps = Game.getPs();
		List<Player> ws = new ArrayList<>();
		int m = 0;
		for (Player ap : Game.getPs().keySet()) {
			if (ps.get(ap) > m) m = ps.get(ap);
		}
		for (Player ap : Game.getPs().keySet()) {
			if (ps.get(ap) == m) ws.add(ap);
		}
		String s = ST_String.getPF()+"§b§l";
		if (ws.size() > 1) {
			s = s + ws.get(0).getName();
			for (int i = 1; i < ws.size() - 1; i++) {
				s = s + "§r§a, §b§l"+ws.get(1).getName();
			}
			return s = s + "§r§a und §b§l"+ws.get(ws.size()-1).getName()+"§r§a haben gewonnen!";
		} else {
			try {
				return s + ws.get(0).getName() + "§r§a hat gewonnen!";
			} catch(Exception e) {
				return s+"§r§aUnentschieden";
			}
		}
	}

	public static String getTooManyArgs() {
		return getPF() + "§cZu viele Argumente!";
	}

	public static String getNoPerm() {
		return getPF() + "§cDazu hast du keine Rechte!";
	}

	public static String getPF() {
		return "§7[§b§lO§c§lX§r§7]§f ";
	}

}
