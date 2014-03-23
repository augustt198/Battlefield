package me.august.battlefield.guns.projectile;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TargetSeeker {

	private Entity projectile;
	private Entity target;
	private BukkitRunnable task;

	public TargetSeeker(final Entity projectile, final Entity target) {
		this.projectile = projectile;
		this.target = target;

		task = new BukkitRunnable() {
			@Override
			public void run() {
				// TODO: 'heat' seeking
			}
		};
	}

	public void start(Plugin plugin) {
		task.runTaskTimerAsynchronously(plugin, 1, 0);
	}

	public Entity getProjectile() {
		return projectile;
	}

	public Entity getTarget() {
		return target;
	}

}
