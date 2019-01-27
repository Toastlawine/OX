package tl.ox.methods;

import java.lang.reflect.Constructor;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;

public class MT_Title {

	public static void showTitle(Player p, String text, String subtitle) {
		try {
			Class<PacketPlayOutTitle> ppot = PacketPlayOutTitle.class;
			Class<IChatBaseComponent> icbc = IChatBaseComponent.class;

			Object enumTitle = ppot.getDeclaredClasses()[0].getField("TITLE").get(null);
			Object enumSubTitle = ppot.getDeclaredClasses()[0].getField("SUBTITLE").get(null);
			Object t = icbc.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null,
					"{\"text\":\"" + text + "\"}");
			Object st = icbc.getDeclaredClasses()[0].getMethod("a", String.class)
					.invoke(null, "{\"text\":\"" + subtitle + "\"}");
			Constructor<?> titleConstructor = ppot.getConstructor(ppot.getDeclaredClasses()[0], icbc, int.class,
					int.class, int.class);

			Object packet1 = titleConstructor.newInstance(enumTitle, t, 1, 1, 1);
			Object packet2 = titleConstructor.newInstance(enumSubTitle, st, 1, 1, 1);
			send(p, packet1);
			send(p, packet2);
		} catch (Exception e) {

		}
	}

	private static void send(Player p, Object packet) {
		try {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket((Packet<?>) packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
