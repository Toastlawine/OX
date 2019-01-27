package tl.ox.methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import tl.ox.main.Game;

public class MT_Locations {

	private static Location oFrom, oTo, xFrom, xTo;
	
	public static char getField(Player p) {
		int r = Game.getRadius();
		Location l = p.getLocation();
		if (Game.isCircle()) {
			if (Game.getLocO().distance(l) <= r) {
				return 'o';
			}
			if (Game.getLocX().distance(l) <= r) {
				return 'x';
			}
		} else {
			if (inside(oFrom, oTo, l)) {
				return 'o';
			}
			if (inside(xFrom, xTo, l)) {
				return 'x';
			}
		}
		return 'n';
	}
	
	public static boolean inside(Location from, Location to, Location here) {
		if (from.getX() > here.getX()) {
			return false;
		}
		if (from.getZ() > here.getZ()) {
			return false;
		}
		if (to.getX() < here.getX()) {
			return false;
		}
		if (to.getZ() < here.getZ()) {
			return false;
		}
		return true;
	}
	
	public static void refreshFromAndTo(Location o, Location x) {
		int r = Game.getRadius();
		oFrom = o.clone();
		oTo = o.clone();
		oFrom = addRadius(oFrom, -r);
		oTo = addRadius(oTo, r);
		xFrom = x.clone();
		xTo = x.clone();
		xFrom = addRadius(xFrom, -r);
		xTo = addRadius(xTo, r);
	}
	
	private static Location addRadius(Location loc, int r) {
		loc.setX(loc.getX()+r);
		loc.setZ(loc.getZ()+r);
		return loc;
	}
	
	public static Location blankLocation() {
		return new Location(Bukkit.getWorld("world"), 0, 0, 0, 0.0f, 0.0f);
	}

	public static void setLocation(YamlConfiguration cfg, String path, Location loc) {
		cfg.set(path + ".W", loc.getWorld().getName());
		cfg.set(path + ".X", loc.getX());
		cfg.set(path + ".Y", loc.getY());
		cfg.set(path + ".Z", loc.getZ());
		cfg.set(path + ".Yaw", loc.getYaw());
		cfg.set(path + ".Pitch", loc.getPitch());
	}

	public static Location getLocation(YamlConfiguration cfg, String path) {
		World w = Bukkit.getWorld(cfg.getString(path + ".W"));
		double x = cfg.getDouble(path + ".X");
		double y = cfg.getDouble(path + ".Y");
		double z = cfg.getDouble(path + ".Z");
		float yaw = (float) cfg.getDouble(path + ".Yaw");
		float pitch = (float) cfg.getDouble(path + ".Pitch");

		return new Location(w, x, y, z, yaw, pitch);
	}

	public static Location toPointFiveLocation(Location loc) {
		loc.setX(Math.floor(loc.getX())+0.5);
		loc.setY(Math.floor(loc.getY())+0.5);
		loc.setZ(Math.floor(loc.getZ())+0.5);
		loc.setYaw(0.0f);
		loc.setPitch(0.0f);
		return loc;
	}
	
	public static Location toBlockLocation(Location loc) {
		loc.setX(Math.floor(loc.getX()));
		loc.setY(Math.floor(loc.getY()));
		loc.setZ(Math.floor(loc.getZ()));
		loc.setYaw(0.0f);
		loc.setPitch(0.0f);
		return loc;
	}
}
