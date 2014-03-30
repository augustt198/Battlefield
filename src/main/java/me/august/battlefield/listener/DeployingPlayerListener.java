package me.august.battlefield.listener;

import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;

/**
 * Created by August on 3/30/14.
 */
public class DeployingPlayerListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		BattlefieldPlayer player = BattlefieldPlayer.get(event.getPlayer());
		if(player == null) return;
		event.setCancelled(player.isDeploying());
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		BattlefieldPlayer player = BattlefieldPlayer.get(event.getPlayer());
		if(player == null) return;
		event.setCancelled(player.isDeploying());
	}

	@EventHandler
	public void onHangingBreak(HangingBreakByEntityEvent event) {
		if(!(event.getRemover() instanceof Player)) return;
		BattlefieldPlayer player = BattlefieldPlayer.get((Player) event.getRemover());
		if(player == null) return;
		event.setCancelled(player.isDeploying());
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player)) return;
		BattlefieldPlayer player = BattlefieldPlayer.get((Player) event.getEntity());
		if(player == null) return;
		event.setCancelled(player.isDeploying());
	}

	@EventHandler
	public void dealDamage(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) return;
		BattlefieldPlayer player = BattlefieldPlayer.get((Player) event.getDamager());
		if(player == null) return;
		event.setCancelled(player.isDeploying());
	}

	@EventHandler
	public void playerInteract(PlayerInteractEvent event) {
		BattlefieldPlayer player = BattlefieldPlayer.get(event.getPlayer());
		if(player == null) return;
		event.setCancelled(player.isDeploying());
	}

	@EventHandler
	public void onEntity(VehicleEntityCollisionEvent event) {
		if(!(event.getEntity() instanceof Player)) return;
		BattlefieldPlayer player = BattlefieldPlayer.get((Player) event.getEntity());
		if(player == null) return;
		event.setCancelled(player.isDeploying());
	}

}
