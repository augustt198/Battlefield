package me.august.bf4.player;

import me.august.bf4.BattlefieldPlugin;
import me.august.bf4.team.BattlefieldTeam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.ref.WeakReference;

public class BattlefieldPlayer {

	public static BattlefieldPlayer get(Player player) {
		for(BattlefieldPlayer p : BattlefieldPlugin.getCurrentMatch().getPlayers()) {
			if(p.getPlayer() == player) return p;
		}
		return null;
	}

	public static BattlefieldPlayer get(String name) {
		return get(Bukkit.getPlayer(name));
	}

	private WeakReference<Player> player;
	private BattlefieldTeam team;

	public BattlefieldPlayer(Player player) {
		this.player = new WeakReference<>(player);
	}

	public BattlefieldTeam getTeam() {
		return team;
	}

	public Player getPlayer() {
		return player.get();
	}

	/* Aliases */
	public void sendMessage(String msg) {
		getPlayer().sendMessage(msg);
	}

	public void teleport(Location location) {
		getPlayer().teleport(location);
	}

}
