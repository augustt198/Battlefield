package me.august.battlefield.listener;

import me.august.battlefield.BattlefieldPlugin;
import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by August on 3/23/14.
 */
public class ConnectionListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		BattlefieldPlayer player = new BattlefieldPlayer(event.getPlayer());
		BattlefieldPlugin.getCurrentMatch().joinRandomTeam(player);
	}

}
