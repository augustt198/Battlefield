package me.august.battlefield.util;

import net.minecraft.server.v1_7_R1.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.v1_7_R1.PacketPlayOutWorldEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R1.CraftServer;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by August on 4/6/14.
 */
public class ParticleUtil {

	@SuppressWarnings("deprecated")
	public static void sendBlockBreak(Player player, Location loc, Material material) {
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		ItemStack item = new ItemStack(material);
		PacketPlayOutWorldEvent packet = new PacketPlayOutWorldEvent(2001, x, y, z, item.getTypeId(), false);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	public static void sendBlockCrack(Player player, Location loc, int damage) {
		PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(0, loc.getBlockX(), loc.getBlockY(),
				loc.getBlockZ(), damage);
		int dimension = ((CraftPlayer) player).getHandle().dimension;
		((CraftServer) player.getServer()).getHandle().sendPacketNearby(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 120, dimension, packet);
	}

}
