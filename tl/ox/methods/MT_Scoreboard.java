package tl.ox.methods;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import tl.ox.main.Game;

public class MT_Scoreboard {

	private static ScoreboardManager sbm = Bukkit.getScoreboardManager();

	/*public static void updateAll() {
		HashMap<Player, Integer> ps = Game.getPs();
		for (Player p : ps.keySet()) {
			final Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
			int i = 0;
			for (Player ap : ps.keySet()) {
				int l = ps.get(ap);
				if (l > 0) {
					o.getScore("§b" + ap.getName() + "§a: " + l).setScore(i);
				} else
					o.getScore("§b" + ap.getName() + "§a: §c✘").setScore(i);
				i++;
			}
		}
	}*/

	public static void scoreboardAll() {
		final Scoreboard board = sbm.getNewScoreboard();
		final Objective o = board.registerNewObjective("ox", "lives");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§a§lLeben:");

		int i = 0;
		HashMap<Player, Integer> ps = Game.getPs();
		for (Player ap : ps.keySet()) {
			int l = ps.get(ap);
			if (l > 0) {
				o.getScore("§b" + ap.getName() + "§a: " + l).setScore(i);
			} else
				o.getScore("§b" + ap.getName() + "§a: §c✘").setScore(i);
			i++;
		}
		for (Player ap : Bukkit.getOnlinePlayers()) {
			ap.setScoreboard(board);
		}
	}

}
