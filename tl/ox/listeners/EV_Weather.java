package tl.ox.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import tl.ox.main.Main;

public class EV_Weather implements Listener {

	@EventHandler
	public void onChange(WeatherChangeEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				World w = e.getWorld();
				w.setThundering(false);
				w.setStorm(false);
				w.setWeatherDuration(1000000);
			}
		}, 1);
	}
	
}
