package tl.ox.methods;

import java.util.ConcurrentModificationException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import tl.ox.main.Main;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class MT_HotbarText {

	private static HashMap<Player, String> msgs = new HashMap<>();

	public static void spamText(Player p, String t) {
		msgs.put(p, t);
	}

	public static void startTimer() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				try {
				for (Player ap : Bukkit.getOnlinePlayers()) {
					ap.setFoodLevel(20);
					ap.setSaturation(20);
					if (ap.getFireTicks() > 0) {
						ap.setFireTicks(1);
					}
				}
				for (Player p : msgs.keySet()) {
					if (p.isOnline()) {
						sendText(p, msgs.get(p));
					} else {
						msgs.remove(p);
					}
				}
				} catch(ConcurrentModificationException e) {
				}
			}
		}, 10, 10);
	}

	public static void sendText(Player p, String t) {
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + t + "\"}");
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(icbc, (byte) 2));
	}

}
