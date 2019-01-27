package tl.ox.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tl.ox.commands.CMD_ox;
import tl.ox.commands.CMD_start;
import tl.ox.configurations.CFG_Config;
import tl.ox.configurations.CFG_Statements;
import tl.ox.listeners.EV_CatchFire;
import tl.ox.listeners.EV_Chat;
import tl.ox.listeners.EV_Click;
import tl.ox.listeners.EV_Damage;
import tl.ox.listeners.EV_DamageByEntity;
import tl.ox.listeners.EV_Drop;
import tl.ox.listeners.EV_Interact;
import tl.ox.listeners.EV_Join;
import tl.ox.listeners.EV_Quit;
import tl.ox.listeners.EV_Respawn;
import tl.ox.listeners.EV_Weather;
import tl.ox.methods.MT_HotbarText;

public class Main extends JavaPlugin {

	public static Main plugin;
	
	public void onEnable() {
		plugin = this;
		CFG_Config.create();
		Game.register();
		Game.setS(60);
		CFG_Statements.create();
		CFG_Statements.load();
		MT_HotbarText.startTimer();
		getCommand("ox").setExecutor(new CMD_ox());
		getCommand("start").setExecutor(new CMD_start());
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EV_Join(), plugin);
		pm.registerEvents(new EV_Quit(), plugin);
		pm.registerEvents(new EV_Chat(), plugin);
		pm.registerEvents(new EV_Weather(), plugin);
		pm.registerEvents(new EV_Interact(), plugin);
		pm.registerEvents(new EV_CatchFire(), plugin);
		pm.registerEvents(new EV_Damage(), plugin);
		pm.registerEvents(new EV_DamageByEntity(), plugin);
		pm.registerEvents(new EV_Respawn(), plugin);
		pm.registerEvents(new EV_Click(), plugin);
		pm.registerEvents(new EV_Drop(), plugin);
	}
	public void onDisable() {
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
}
