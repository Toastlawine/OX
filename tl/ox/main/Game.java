package tl.ox.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import tl.ox.configurations.CFG_Config;
import tl.ox.configurations.CFG_Statements;
import tl.ox.enums.EN_State;
import tl.ox.methods.MT_HotbarText;
import tl.ox.methods.MT_Locations;
import tl.ox.methods.MT_Particles;
import tl.ox.methods.MT_Player;
import tl.ox.methods.MT_Scoreboard;
import tl.ox.methods.MT_Title;
import tl.ox.storage.ST_String;

public class Game {

	private static int task;
	private static HashMap<Player, Integer> ps;
	private static List<Player> specs;
	private static List<String> used;
	private static int s;

	private static EN_State state;
	private static int minP, maxP, radius, lives, think, maxSt, st;
	private static boolean invisible, knockstick, circle, tablist, thinking, first, current;
	private static Location lobby, spawn, locO, locX;

	public static void startTimer() {
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (state == EN_State.LOBBY) {
					switch (s) {
					case 60:
					case 30:
					case 15:
					case 10:
					case 5:
					case 3:
					case 2:
						setAllLevels(s);
						messageAll(ST_String.getPF() + "§aDas Spiel startet in §b" + s + "§a Sekunden!", false);
						soundAll(Sound.CHICKEN_EGG_POP, 1, 1);
						break;
					case 1:
						setAllLevels(s);
						messageAll(ST_String.getPF() + "§aDas Spiel startet in §beiner§a Sekunde!", false);
						soundAll(Sound.CHICKEN_EGG_POP, 1, 1);
						break;
					case 0:
						setAllLevels(s);
						teleportAll(spawn);
						state = EN_State.INGAME;
						s = 11;
						MT_Scoreboard.scoreboardAll();
						soundAll(Sound.ORB_PICKUP, 1, 1);
						if (knockstick) {
							MT_Player.giveSticks();
						}
						break;
					default:
						setAllLevels(s);
						break;
					}
				} else if (state == EN_State.INGAME) {
					if (thinking) {
						switch (s) {
						case 30:
						case 15:
						case 5:
						case 4:
						case 3:
						case 2:
						case 1:
							setAllLevels(s);
							titleAll("§a" + s, "");
							soundAll(Sound.CLICK, 0.6f, 1f);
							break;
						case 0:
							setAllLevels(s);
							result();
							nextDelay();
							break;
						default:
							setAllLevels(s);
							break;
						}
					} else {
						if (first) {
							switch (s) {
							case 10:
							case 5:
							case 3:
							case 2:
								setAllLevels(s);
								messageAll(ST_String.getPF() + "§aErste Frage in §b" + s + "§a Sekunden...", true);
								soundAll(Sound.CHICKEN_EGG_POP, 1, 1);
								break;
							case 1:
								setAllLevels(s);
								messageAll(ST_String.getPF() + "§aErste Frage in §beiner§a Sekunde...", true);
								soundAll(Sound.CHICKEN_EGG_POP, 1, 1);
								break;
							case 0:
								setAllLevels(s);
								first = false;
								nextStatement();
								break;
							default:
								setAllLevels(s);
								break;
							}
						} else {
							switch (s) {
							case 5:
							case 3:
							case 2:
								messageAll(ST_String.getPF() + "§aNächste Frage in §b" + s + "§a Sekunden...", true);
								soundAll(Sound.CHICKEN_EGG_POP, 1, 1);
								setAllLevels(s);
								break;
							case 1:
								messageAll(ST_String.getPF() + "§aNächste Frage in §beiner§a Sekunde...", true);
								soundAll(Sound.CHICKEN_EGG_POP, 1, 1);
								setAllLevels(s);
								break;
							case 0:
								first = false;
								nextStatement();
								setAllLevels(s);
								break;
							default:
								setAllLevels(s);
								break;
							}
						}
					}
				} else if (state == EN_State.END) {
					switch (s) {
					case 10:
					case 5:
					case 3:
					case 2:
						setAllLevels(s);
						messageAll(ST_String.getPF() + "§aDas Spiel wird in §b" + s + "§a Sekunden zurückgesetzt...",
								true);
						break;
					case 1:
						setAllLevels(s);
						messageAll(ST_String.getPF() + "§aDas Spiel wird in §beiner§a Sekunde zurückgesetzt...", true);
						break;
					case 0:
						kickAll("§aDas Spiel wird zurückgesetzt");
						cancelTimer();
						register();
						break;
					default:
						setAllLevels(s);
						break;
					}
				}
				s--;
			}
		}, 20, 20);
	}

	public static void checkEnd() {
		if (ps.size() <= 1 || st >= maxSt) {
			messageAll("§e§kA§r§e------------------------------§kA", true);
			messageAll(ST_String.getWinMessage(), true);
			messageAll("§e§kA§r§e------------------------------§kA", true);
			soundAll(Sound.LEVEL_UP, 1, 1);
			s = 10;
			state = EN_State.END;
		}
	}

	public static void nextDelay() {
		s = 6;
		thinking = false;
		if (invisible) {
			MT_Player.showPlayersToPlayers();
		}
		checkEnd();
	}

	public static void nextStatement() {
		st++;
		thinking = true;
		s = think;
		if (invisible) {
			MT_Player.hidePlayersFromPlayers();
		}
		LinkedHashMap<String, Boolean> hm = CFG_Statements.getSm();
		List<String> sm = new ArrayList<>(hm.keySet());
		current = generateStatement(sm, hm, 0);
	}

	public static boolean generateStatement(List<String> sm, LinkedHashMap<String, Boolean> hm, int tries) {
		int rdm = (int) (Math.random() * sm.size());
		String s = sm.get(rdm);
		if (used.contains(s)) {
			if (tries > 10) {
				tries = 0;
				used = new ArrayList<>();
			}
			return generateStatement(sm, hm, tries++);
		}
		messageAll(ST_String.getPF() + "§b§lO§r§a oder §c§lX§r§a?", true);
		messageAll(ST_String.getPF() + "§6§l" + s, true);
		return hm.get(s);
	}

	public static void result() {
		String t;
		if (current) {
			t = "§b§lO";
		} else {
			t = "§c§lX";
		}
		for (Player ap : ps.keySet()) {
			char r = MT_Locations.getField(ap);
			boolean w;
			if (r == 'n') {
				MT_Title.showTitle(ap, t, "§cDu hast nichts ausgewählt");
				w = false;
			} else {
				if (current) {
					if (r == 'o') {
						MT_Title.showTitle(ap, t, "§aRichtig ✔");
						w = true;
					} else {
						MT_Title.showTitle(ap, t, "§cFalsch ✘");
						w = false;
					}
				} else {
					if (r == 'x') {
						MT_Title.showTitle(ap, t, "§aRichtig ✔");
						w = true;
					} else {
						MT_Title.showTitle(ap, t, "§cFalsch ✘");
						w = false;
					}
				}
			}
			if (!w) {
				ps.put(ap, ps.get(ap) - 1);
				Location loc = ap.getLocation();
				if (ps.get(ap) <= 0) {
					ap.playSound(loc, Sound.BLAZE_DEATH, 1, 1);
					MT_Particles.smoke(loc);
					makeSpectator(ap);
				}
				loc.getWorld().strikeLightning(loc);
			} else
				ap.playSound(ap.getLocation(), Sound.LEVEL_UP, 1, 2);
		}
		MT_Scoreboard.scoreboardAll();
	}

	public static void makeSpectator(Player p) {
		ps.remove(p);
		specs.add(p);
		MT_Player.clear(p, true);
		p.setAllowFlight(true);
		MT_Player.hideSelfFromEveryone(p);
		MT_Player.showEveryoneToSelf(p);
		MT_HotbarText.spamText(p, "§aDu bist Spectator");
	}

	public static void kickAll(String m) {
		for (Player ap : Bukkit.getOnlinePlayers()) {
			ap.kickPlayer(ST_String.getPF() + m);
		}
	}

	public static void titleAll(String text, String sub) {
		for (Player ap : ps.keySet()) {
			MT_Title.showTitle(ap, text, sub);
		}
	}

	public static void setAllLevels(int l) {
		if (l >= 0) {
			for (Player ap : ps.keySet()) {
				ap.setLevel(l);
			}
		}
	}

	public static void teleportAll(Location loc) {
		for (Player ap : ps.keySet()) {
			ap.teleport(loc);
		}
	}

	public static void soundAll(Sound s, float v, float p) {
		for (Player ap : ps.keySet()) {
			ap.playSound(ap.getLocation(), s, v, p);
		}
	}

	public static void messageAll(String m, boolean spec) {
		for (Player ap : ps.keySet()) {
			ap.sendMessage(m);
		}
		if (spec) {
			for (Player ap : specs) {
				ap.sendMessage(m);
			}
		}
	}

	public static void cancelTimer() {
		Bukkit.getScheduler().cancelTask(task);
	}

	public static void register() {
		s = 61;
		st = 0;
		state = EN_State.LOBBY;
		minP = CFG_Config.getMinPlayers();
		maxP = CFG_Config.getMaxPlayers();
		lives = CFG_Config.getLives();
		think = CFG_Config.getThinkingTime() + 1;
		maxSt = CFG_Config.getMaxStatements();
		invisible = CFG_Config.getInvisible();
		knockstick = CFG_Config.getKnockStick();
		thinking = false;
		first = true;
		lobby = CFG_Config.getLobbyLocation();
		spawn = CFG_Config.getSpawnLocation();
		locO = CFG_Config.getOLocation();
		locX = CFG_Config.getXLocation();
		ps = new HashMap<>();
		specs = new ArrayList<>();
		used = new ArrayList<>();
		radius = CFG_Config.getRadius();
		setCircle(CFG_Config.getCircle());
	}

	public static EN_State getState() {
		return state;
	}

	public static void setState(EN_State state) {
		Game.state = state;
	}

	public static int getMinP() {
		return minP;
	}

	public static void setMinP(int minP) {
		Game.minP = minP;
	}

	public static int getMaxP() {
		return maxP;
	}

	public static void setMaxP(int maxP) {
		Game.maxP = maxP;
	}

	public static boolean isInvisible() {
		return invisible;
	}

	public static void setInvisible(boolean invisible) {
		Game.invisible = invisible;
	}

	public static boolean isKnockstick() {
		return knockstick;
	}

	public static void setKnockstick(boolean knockstick) {
		Game.knockstick = knockstick;
	}

	public static HashMap<Player, Integer> getPs() {
		return ps;
	}

	public static boolean isCircle() {
		return circle;
	}

	public static void setCircle(boolean circle) {
		Game.circle = circle;
		MT_Locations.refreshFromAndTo(locO, locX);
	}

	public static Location getLobby() {
		return lobby;
	}

	public static void setLobby(Location lobby) {
		Game.lobby = lobby;
	}

	public static Location getSpawn() {
		return spawn;
	}

	public static void setSpawn(Location spawn) {
		Game.spawn = spawn;
	}

	public static Location getLocO() {
		return locO;
	}

	public static void setLocO(Location locO) {
		Game.locO = locO;
		MT_Locations.refreshFromAndTo(locO, locX);
	}

	public static Location getLocX() {
		return locX;
	}

	public static void setLocX(Location locX) {
		Game.locX = locX;
		MT_Locations.refreshFromAndTo(locO, locX);
	}

	public static int getRadius() {
		return radius;
	}

	public static void setRadius(int radius) {
		Game.radius = radius;
		MT_Locations.refreshFromAndTo(locO, locX);
	}

	public static int getLives() {
		return lives;
	}

	public static void setLives(int lives) {
		Game.lives = lives;
	}

	public static int getS() {
		return s;
	}

	public static void setS(int s) {
		Game.s = s;
	}

	public static int getThink() {
		return think;
	}

	public static void setThink(int think) {
		Game.think = think + 1;
	}

	public static boolean isThinking() {
		return thinking;
	}

	public static void setThinking(boolean thinking) {
		Game.thinking = thinking;
	}

	public static List<Player> getSpecs() {
		return specs;
	}

	public static boolean isTablist() {
		return tablist;
	}

	public static void setTablist(boolean tablist) {
		Game.tablist = tablist;
	}

	public static int getMaxSt() {
		return maxSt;
	}

	public static void setMaxSt(int maxSt) {
		Game.maxSt = maxSt;
	}

}
