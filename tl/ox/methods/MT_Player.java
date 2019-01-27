package tl.ox.methods;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import tl.ox.main.Game;
import tl.ox.storage.ST_Items;

public class MT_Player {
	
	public static void giveSticks() {
		ItemStack s = ST_Items.getStick();
		for (Player p : Game.getPs().keySet()) {
			p.getInventory().setItem(0, s);
		}
	}
	
	public static void showEveryoneToSelf(Player p) {
		for (Player ap : Bukkit.getOnlinePlayers()) {
			if (p != ap) {
				p.showPlayer(ap);
			}
		}
	}
	
	public static void showSelfToEveryone(Player p) {
		for (Player ap : Bukkit.getOnlinePlayers()) {
			if (p != ap) {
				ap.showPlayer(p);
			}
		}
	}
	
	public static void hideSelfFromEveryone(Player p) {
		for (Player ap : Bukkit.getOnlinePlayers()) {
			if (p != ap) {
				ap.hidePlayer(p);
			}
		}
	}

	public static void showPlayersToPlayers() {
		HashMap<Player, Integer> ps = Game.getPs();
		for (Player ap : ps.keySet()) {
			for (Player ap2 : ps.keySet()) {
				if (ap2 != ap) {
					ap.showPlayer(ap2);
				}
			}
		}
	}

	public static void hidePlayersFromPlayers() {
		HashMap<Player, Integer> ps = Game.getPs();
		for (Player ap : ps.keySet()) {
			for (Player ap2 : ps.keySet()) {
				if (ap2 != ap) {
					ap.hidePlayer(ap2);
				}
			}
		}
	}
	
	public static void clear(Player p, boolean inv) {
		p.setHealth(20);
		p.setFoodLevel(20);
		p.setSaturation(20);
		p.setFlying(false);
		p.setAllowFlight(false);
		if (inv) {
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.setLevel(0);
			p.setExp(0f);
		}
		p.setFireTicks(0);
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		p.updateInventory();
	}
	
}
