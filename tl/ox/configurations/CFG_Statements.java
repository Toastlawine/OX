package tl.ox.configurations;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import tl.ox.storage.ST_Statements;

public class CFG_Statements {

	private static LinkedHashMap<String, Boolean> sm;
	
	private static File file = new File("plugins/OX/Statements.yml");
	private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static boolean remove(String r) {
		List<String> l = getStatementList();
		for (String s : l) {
			if (r.equalsIgnoreCase(s.substring(1))) {
				l.remove(s);
				sm.remove(s.substring(1));
				saveList(l);
				return true;
			}
		}
		return false;
	}
	
	public static void add(String s, boolean c) {
		List<String> l = getStatementList();
		if (c) {
			l.add("O"+s);
			saveList(l);
			sm.put(s, true);
		} else {
			l.add("X"+s);
			saveList(l);
			sm.put(s, false);
		}
	}
	
	public static void load() {
		sm = new LinkedHashMap<>();
		for (String s : getStatementList()) {
			if (s.charAt(0) == 'O') {
				sm.put(s.substring(1), true);
			} else sm.put(s.substring(1), false);
		}
	}
	
	public static void saveList(List<String> s) {
		cfg.set("Statements", s);
		save();
	}
	
	public static void create() {
		if (!file.exists()) {
			cfg.set("Statements", ST_Statements.getExampleStatements());
			save();
		}
	}
	
	private static List<String> getStatementList() {
		return cfg.getStringList("Statements");
	}
	
	public static void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static LinkedHashMap<String, Boolean> getSm() {
		return sm;
	}
	
}
