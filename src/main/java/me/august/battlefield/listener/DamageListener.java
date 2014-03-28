package me.august.battlefield.listener;

import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if(!(event.getEntity() instanceof Player) || !(event.getEntity() instanceof Player)) return;

		Player damagerPlayer = (Player) event.getDamager();
		Player hurtPlayer = (Player) event.getEntity();

		BattlefieldPlayer damager = BattlefieldPlayer.get(damagerPlayer);
		BattlefieldPlayer hurt = BattlefieldPlayer.get(hurtPlayer);

		if(damager == null || hurt == null) return;

		if(damager.getTeam() == hurt.getTeam()) event.setCancelled(true);

	}

}
