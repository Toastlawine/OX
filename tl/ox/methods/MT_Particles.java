package tl.ox.methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class MT_Particles {
	public static void smoke(Location loc) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.SMOKE_NORMAL, true,
				(float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), 0.5f, 2f, 0.5f, 0.1f, 200);
		for (Player ap : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) ap).getHandle().playerConnection.sendPacket(packet);
		}
	}
}
