package me.august.battlefield.listener;

import me.august.battlefield.util.ParticleUtil;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * Created by August on 4/4/14.
 */
public class ProjectileListener implements Listener {

	@SuppressWarnings("deprecated")
	@EventHandler
	public void onProjectileLand(ProjectileHitEvent event) {

		if(event.getEntity().getShooter() != null && event.getEntity().getShooter() instanceof Player) {
			Player player = (Player) event.getEntity().getShooter();
			Projectile p = event.getEntity();
			ParticleUtil.sendBlockBreak(player, p.getLocation().subtract(0, 1, 0), p.getLocation().getBlock().getType());
			if(p.getLocation().subtract(0, 1, 0).getBlock().getType().isSolid()) {
				ParticleUtil.sendBlockCrack(player, p.getLocation().getBlock().getLocation().subtract(0, 1, 0), 5);
			}

		}

	}

}
