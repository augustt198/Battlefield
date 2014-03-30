package me.august.battlefield.listener;

import me.august.battlefield.BattlefieldPlugin;
import me.august.battlefield.player.BattlefieldPlayer;
import me.august.battlefield.player.SpawnPoint;
import me.august.battlefield.util.ItemAbility;
import me.august.battlefield.util.Log;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by August on 3/23/14.
 */
public class ConnectionListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final BattlefieldPlayer player = new BattlefieldPlayer(event.getPlayer());
		BattlefieldPlugin.getCurrentMatch().joinRandomTeam(player);
		List<SpawnPoint> spawnPoints = player.getSpawnPoints();
		for(int i = 0; i < spawnPoints.size(); i++) {
			final SpawnPoint sp = spawnPoints.get(i);
			ItemStack spItem = sp.toItem();

			player.getPlayer().getInventory().setItem(i, spItem);

			new ItemAbility(spItem).undroppable().onLeftClick(new Runnable() {
				@Override
				public void run() {
					player.teleport(sp.getLocation());
				}
			}).unmovable().undroppable();
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		BattlefieldPlayer player = BattlefieldPlayer.get(event.getPlayer());
		if(player != null) player.remove();
	}

}
