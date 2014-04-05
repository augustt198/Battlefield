package me.august.battlefield.listener;

import net.minecraft.server.v1_7_R1.PacketPlayOutWorldEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Created by August on 4/4/14.
 */
public class ProjectileListener implements Listener {

	@SuppressWarnings("deprecated")
	@EventHandler
	public void onProjectileLand(ProjectileHitEvent event) {

		if(event.getEntity().getShooter() != null && event.getEntity().getShooter() instanceof Player) {
			Vector v = event.getEntity().getLocation().toVector();
			Block block = event.getEntity().getLocation().getBlock();
			if(block.getType() == Material.AIR) {
				block = block.getWorld().getBlockAt(block.getLocation().subtract(0, 1, 0));
			}
			ItemStack item = new ItemStack(block.getType());
			PacketPlayOutWorldEvent packet = new PacketPlayOutWorldEvent(2001, v.getBlockX(), v.getBlockY(),
					v.getBlockZ(), item.getTypeId(), false);
			Player player = (Player) event.getEntity().getShooter();
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}

	}


}
