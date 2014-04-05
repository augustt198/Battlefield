package me.august.battlefield.manager;

import me.august.battlefield.BattlefieldPlugin;
import org.bukkit.Bukkit;

/**
 * Created by August on 3/31/14.
 */
public class Manager {

	public static ZoomManager zoom = new ZoomManager();
	public static AmmoManager manager = new AmmoManager();

	public Manager() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BattlefieldPlugin.get(), zoom, 1, 1);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BattlefieldPlugin.get(), manager, 1, 1);
	}

}
