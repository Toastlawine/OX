package tl.ox.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tl.ox.configurations.CFG_Config;
import tl.ox.configurations.CFG_Statements;
import tl.ox.main.Game;
import tl.ox.methods.MT_Boolean;
import tl.ox.methods.MT_Locations;
import tl.ox.storage.ST_String;

public class CMD_ox implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("ox")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("ox.ox")) {
					if (args.length > 0) {
						args[0] = args[0].toLowerCase();
						switch (args[0]) {
						case "bool":
							if (p.hasPermission("ox.ox.bool")) {
								if (args.length > 1) {
									args[1] = args[1].toLowerCase();
									if (args.length < 4) {
										switch (args[1]) {
										case "invis":
											if (args.length > 2) {
												if (MT_Boolean.isOnOrOff(args[2])) {
													if (args[2].equalsIgnoreCase("on")) {
														CFG_Config.setInvisible(true);
														Game.setInvisible(true);
														p.sendMessage(ST_String.getPF()
																+ "§aSpieler sind nun während des Auswählens unsichtbar!");
													} else {
														CFG_Config.setInvisible(false);
														Game.setInvisible(false);
														p.sendMessage(ST_String.getPF()
																+ "§aSpieler sind nun während des Auswählens sichtbar!");
													}
												} else
													p.sendMessage(ST_String.getPF() + "§c/ox bool invis on/off");
											} else
												p.sendMessage(ST_String.getPF() + "§c/ox bool invis on/off");
											break;
										case "knock":
											if (args.length > 2) {
												if (MT_Boolean.isOnOrOff(args[2])) {
													if (args[2].equalsIgnoreCase("on")) {
														CFG_Config.setKnockStick(true);
														Game.setKnockstick(true);
														p.sendMessage(ST_String.getPF()
																+ "§aSpieler erhalten nun Rückstoßstöcke!");
													} else {
														CFG_Config.setKnockStick(false);
														Game.setKnockstick(false);
														p.sendMessage(ST_String.getPF()
																+ "§aSpieler erhalten nun keine Rückstoßstöcke!");
													}
												} else
													p.sendMessage(ST_String.getPF() + "§c/ox bool knock on/off");
											} else
												p.sendMessage(ST_String.getPF() + "§c/ox bool knock on/off");
											break;
										case "circle":
											if (args.length > 2) {
												if (MT_Boolean.isOnOrOff(args[2])) {
													if (args[2].equalsIgnoreCase("on")) {
														CFG_Config.setCircle(true);
														Game.setCircle(true);
														p.sendMessage(ST_String.getPF()
																+ "§aDie Auswahlfelder sind jetzt rund!");
													} else {
														CFG_Config.setCircle(false);
														Game.setCircle(false);
														p.sendMessage(ST_String.getPF()
																+ "§aDie Auswahlfelder sind jetzt quadratisch!");
													}
												} else
													p.sendMessage(ST_String.getPF() + "§c/ox bool circle on/off");
											} else
												p.sendMessage(ST_String.getPF() + "§c/ox bool circle on/off");
											break;
										case "tablist":
											if (args.length > 2) {
												if (MT_Boolean.isOnOrOff(args[2])) {
													if (args[2].equalsIgnoreCase("on")) {
														CFG_Config.setTablist(true);
														Game.setTablist(true);
														p.sendMessage(ST_String.getPF()
																+ "§aDie Tablist wird jetzt ersetzt!");
													} else {
														CFG_Config.setTablist(false);
														Game.setTablist(false);
														p.sendMessage(ST_String.getPF()
																+ "§aDie Tablist wird jetzt nicht mehr ersetzt!");
													}
												} else
													p.sendMessage(ST_String.getPF() + "§c/ox bool circle on/off");
											} else
												p.sendMessage(ST_String.getPF() + "§c/ox bool circle on/off");
											break;
										default:
											break;
										}
									} else
										p.sendMessage(ST_String.getTooManyArgs());
								} else
									p.sendMessage(ST_String.getPF() + "§c/ox bool circle/invis/knock/tablist on/off");
							} else
								p.sendMessage(ST_String.getNoPerm());
							break;
						case "int":
							if (p.hasPermission("ox.ox.int")) {
								if (args.length > 2) {
									if (args.length < 4) {
										try {
											int n = Integer.parseInt(args[2]);
											args[1] = args[1].toLowerCase();
											if (n > 0) {
												switch (args[1]) {
												case "lives":
													CFG_Config.setLives(n);
													Game.setLives(n);
													p.sendMessage(ST_String.getPF() + "§aJeder Spieler hat jetzt §b" + n
															+ "§a Leben!");
													break;
												case "max":
													if (n <= 16) {
														CFG_Config.setMaxPlayers(n);
														Game.setMaxP(n);
														p.sendMessage(ST_String.getPF() + "§aEs können nun maximal §b"
																+ n + "§a Spieler in eine Runde!");
													} else
														p.sendMessage(ST_String.getPF()
																+ "§cGib eine Zahl zwischen §b1§c und §b16§c an!");
													break;
												case "maxstate":
													CFG_Config.setMaxStatements(n);
													Game.setMaxSt(n);
													p.sendMessage(ST_String.getPF() + "§aEin Spiel kann nun maximal §b"
															+ n + "§a Runden lang sein!");
													break;
												case "min":
													if (n <= Game.getMaxP()) {
														CFG_Config.setMinPlayers(n);
														Game.setMinP(n);
														p.sendMessage(ST_String.getPF() + "§aEs müssen nun minimal §b"
																+ n + "§a Spieler in eine Runde!");
													} else
														p.sendMessage(ST_String.getPF()
																+ "§cDer Wert muss mindestens so klein wie die maximale Spieleranzahl sein! (§b"
																+ Game.getMaxP() + "§c)");
													break;
												case "radius":
													CFG_Config.setRadius(n);
													Game.setRadius(n);
													p.sendMessage(ST_String.getPF()
															+ "§aDie Auswahlfelder haben nun einen Radius von §b" + n
															+ "§a!");
													break;
												case "think":
													CFG_Config.setThinkingTime(n);
													Game.setThink(n);
													p.sendMessage(ST_String.getPF() + "§aSpieler haben jetzt §b" + n
															+ "§a Sekunden Zeit zum Überlegen!");
													break;
												default:
													p.sendMessage(ST_String.getPF()
															+ "§c/ox int lives/max/maxstate/min/radius/think <Wert>");
													break;
												}
											} else
												p.sendMessage(
														ST_String.getPF() + "§cDer Wert muss mindestens §b1§c sein!");
										} catch (NumberFormatException e) {
											p.sendMessage(
													ST_String.getPF() + "§b" + args[2] + "§c ist keine gültige Zahl!");
										}
									} else
										p.sendMessage(ST_String.getTooManyArgs());
								} else
									p.sendMessage(ST_String.getPF() + "§c/ox int max/maxstate/min/radius/think <Wert>");
							} else
								p.sendMessage(ST_String.getNoPerm());
							break;
						case "loc":
							if (p.hasPermission("ox.ox.loc")) {
								if (args.length > 1) {
									args[1] = args[1].toLowerCase();
									if (args.length < 3) {
										Location loc = p.getLocation();
										Location locPF = MT_Locations.toPointFiveLocation(loc);
										switch (args[1]) {
										case "lobby":
											CFG_Config.setLobbyLocation(loc);
											Game.setLobby(loc);
											p.sendMessage(ST_String.getPF() + "§aPosition §bLobby§a gespeichert!");
											break;
										case "spawn":
											CFG_Config.setSpawnLocation(loc);
											Game.setSpawn(loc);
											p.sendMessage(ST_String.getPF() + "§aPosition §bSpawn§a gespeichert!");
											break;
										case "o":
											CFG_Config.setOLocation(locPF);
											Game.setLocO(locPF);
											p.sendMessage(ST_String.getPF() + "§aPosition §bO§a gespeichert!");
											break;
										case "x":
											CFG_Config.setXLocation(locPF);
											Game.setLocX(locPF);
											p.sendMessage(ST_String.getPF() + "§aPosition §bX§a gespeichert!");
											break;
										default:
											p.sendMessage(ST_String.getPF() + "§c/ox loc lobby/spawn/o/x");
											break;
										}
									} else
										p.sendMessage(ST_String.getTooManyArgs());
								} else
									p.sendMessage(ST_String.getPF() + "§c/ox loc lobby/spawn/o/x");
							} else
								p.sendMessage(ST_String.getNoPerm());
							break;
						case "perms":
							if (args.length == 1) {
								p.sendMessage("§a§lAlle Permissions:");
								p.sendMessage("§a- /ox : §box.ox");
								p.sendMessage("§a- /ox bool : §box.ox.bool");
								p.sendMessage("§a- /ox int : §box.ox.int");
								p.sendMessage("§a- /ox loc : §box.ox.loc");
								p.sendMessage("§a- /ox sm : §box.ox.sm");
								p.sendMessage("§a- /start : §box.start");
							} else
								p.sendMessage(ST_String.getTooManyArgs());
							break;
						case "sm":
							if (p.hasPermission("ox.ox.sm")) {
								if (args.length > 1) {
									args[1] = args[1].toLowerCase();
									switch (args[1]) {
									case "add":
										if (args.length > 3) {
											if (args[2].equalsIgnoreCase("o") || args[2].equalsIgnoreCase("x")) {
												boolean c = false;
												if (args[2].equalsIgnoreCase("o")) {
													c = true;
												} else if (args[2].equalsIgnoreCase("x")) {
													c = false;
												}
												String s = args[3];
												for (int i = 4; i < args.length; i++) {
													s = s + " " + args[i];
												}
												CFG_Statements.add(s, c);
												p.sendMessage(ST_String.getPF() + "§aBehauptung gespeichert!");
											} else
												p.sendMessage(ST_String.getPF() + "§c/ox sm add o/x <Behauptung>");
										} else
											p.sendMessage(ST_String.getPF() + "§c/ox sm add o/x <Behauptung>");
										break;
									case "rem":
										if (args.length > 2) {
											String s = args[2];
											for (int i = 3; i < args.length; i++) {
												s = s + " " + args[i];
											}
											if (CFG_Statements.remove(s)) {
												p.sendMessage(ST_String.getPF() + "§aBehauptung entfernt!");
											} else
												p.sendMessage(ST_String.getPF()
														+ "§cDie Behauptung konnte nicht gefunden werden!");
										} else
											p.sendMessage(ST_String.getPF() + "§c/ox sm rem <Behauptung>");
										break;
									case "list":
										if (args.length > 2) {
											if (args.length < 4) {
												try {
													int page = Integer.parseInt(args[2]);
													LinkedHashMap<String, Boolean> hm = CFG_Statements.getSm();
													int lastPage = hm.size() / 10;
													if (hm.size() % 10 != 0)
														lastPage++;
													if (page <= lastPage && page >= 0) {
														List<String> l = new ArrayList<>(hm.keySet());
														p.sendMessage(ST_String.getPF() + "§aSeite §b" + page
																+ "§a von §b" + lastPage + "§a:");
														for (int i = page * 10 - 10; i < page * 10; i++) {
															try {
																p.sendMessage("§a- §b"
																		+ MT_Boolean.booleanToOX(hm.get(l.get(i)))
																		+ ": §a" + l.get(i));
															} catch (IndexOutOfBoundsException e) {
																break;
															}
														}
													} else
														p.sendMessage(ST_String.getPF()
																+ "§c gib eine Zahl zwischen §b1§c und §b" + lastPage
																+ "§c an!");
												} catch (NumberFormatException e) {
													p.sendMessage(ST_String.getPF() + "§b" + args[2]
															+ "§c ist keine gültige Zahl!");
												}
											} else
												p.sendMessage(ST_String.getTooManyArgs());
										} else
											p.sendMessage(ST_String.getPF() + "§c/ox sm list <Seite>");
										break;
									default:
										break;
									}
								} else
									p.sendMessage(ST_String.getPF() + "§c/ox sm add/list/rem");
							} else
								p.sendMessage(ST_String.getNoPerm());
							break;
						default:
							p.sendMessage(ST_String.getPF() + "§c/ox bool/int/loc/perms/sm");
							break;
						}
					} else
						sender.sendMessage(ST_String.getPF() + "§c/ox bool/int/loc/perms/sm");
				} else
					p.sendMessage(ST_String.getNoPerm());
			}
		}

		return false;
	}

}
