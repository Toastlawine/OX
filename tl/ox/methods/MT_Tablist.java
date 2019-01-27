package tl.ox.methods;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import tl.ox.storage.ST_String;

public class MT_Tablist {

	public static void setTablist(Player p) {
		try {
			PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			Object header = new ChatComponentText(ST_String.getPF());
			Object footer = new ChatComponentText("ßaßlViel Spaﬂ!");
			Field a = packet.getClass().getDeclaredField("a");
			a.setAccessible(true);
			Field b = packet.getClass().getDeclaredField("b");
			b.setAccessible(true);

			a.set(packet, header);
			b.set(packet, footer);
		} catch (Exception e) {
		}
	}
}
