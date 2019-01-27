package tl.ox.configurations;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import tl.ox.methods.MT_Locations;

public class CFG_Config {

	private static File file = new File("plugins/OX/Config.yml");
	private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static void create() {
		if (!file.exists()) {
			cfg.set("Ints.Lives", 3);
			cfg.set("Ints.MinPlayers", 2);
			cfg.set("Ints.MaxPlayers", 8);
			cfg.set("Ints.MaxStatements", 20);
			cfg.set("Ints.Radius", 3);
			cfg.set("Ints.ThinkingTime", 10);
			cfg.set("Bools.Invisible", false);
			cfg.set("Bools.KnockStick", false);
			cfg.set("Bools.Circle", false);
			cfg.set("Bools.Tablist", false);
			MT_Locations.setLocation(cfg, "Locations.Lobby", MT_Locations.blankLocation());
			MT_Locations.setLocation(cfg, "Locations.Spawn", MT_Locations.blankLocation());
			MT_Locations.setLocation(cfg, "Locations.O", MT_Locations.blankLocation());
			MT_Locations.setLocation(cfg, "Locations.X", MT_Locations.blankLocation());
			save();
		}
	}
	
	public static void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setXLocation(Location loc) {
		MT_Locations.setLocation(cfg, "Locations.X", loc);
		save();
	}
	
	public static void setOLocation(Location loc) {
		MT_Locations.setLocation(cfg, "Locations.O", loc);
		save();
	}
	
	public static void setSpawnLocation(Location loc) {
		MT_Locations.setLocation(cfg, "Locations.Spawn", loc);
		save();
	}
	
	public static void setLobbyLocation(Location loc) {
		MT_Locations.setLocation(cfg, "Locations.Lobby", loc);
		save();
	}
	
	public static void setTablist(boolean t) {
		cfg.set("Bools.Tablist", t);
		save();
	}
	
	public static void setCircle(boolean c) {
		cfg.set("Bools.Circle", c);
		save();
	}
	
	public static void setKnockStick(boolean ks) {
		cfg.set("Bools.KnockStick", ks);
		save();
	}
	
	public static void setInvisible(boolean i) {
		cfg.set("Bools.Invisible", i);
		save();
	}
	
	public static void setMaxStatements(int s) {
		cfg.set("Ints.MaxStatements", s);
		save();
	}
	
	public static void setMaxPlayers(int p) {
		cfg.set("Ints.MaxPlayers", p);
		save();
	}
	
	public static void setMinPlayers(int p) {
		cfg.set("Ints.MinPlayers", p);
		save();
	}
	
	public static void setRadius(int r) {
		cfg.set("Ints.Radius", r);
		save();
	}
	
	public static void setThinkingTime(int tt) {
		cfg.set("Ints.ThinkingTime", tt);
		save();
	}
	
	public static void setLives(int l) {
		cfg.set("Ints.Lives", l);
		save();
	}
	
	public static Location getXLocation() {
		return MT_Locations.getLocation(cfg, "Locations.X");
	}
	
	public static Location getOLocation() {
		return MT_Locations.getLocation(cfg, "Locations.O");
	}
	
	public static Location getSpawnLocation() {
		return MT_Locations.getLocation(cfg, "Locations.Spawn");
	}
	
	public static Location getLobbyLocation() {
		return MT_Locations.getLocation(cfg, "Locations.Lobby");
	}
	
	public static boolean getTablist() {
		return cfg.getBoolean("Bools.Tablist");
	}
	
	public static boolean getCircle() {
		return cfg.getBoolean("Bools.Circle");
	}
	
	public static boolean getKnockStick() {
		return cfg.getBoolean("Bools.KnockStick");
	}
	
	public static boolean getInvisible() {
		return cfg.getBoolean("Bools.Invisible");
	}
	
	public static int getMaxStatements() {
		return cfg.getInt("Ints.MaxStatements");
	}
	
	public static int getMaxPlayers() {
		return cfg.getInt("Ints.MaxPlayers");
	}
	
	public static int getMinPlayers() {
		return cfg.getInt("Ints.MinPlayers");
	}
	
	public static int getRadius() {
		return cfg.getInt("Ints.Radius");
	}
	
	public static int getThinkingTime() {
		return cfg.getInt("Ints.ThinkingTime");
	}
	
	public static int getLives() {
		return cfg.getInt("Ints.Lives");
	}
	
}
