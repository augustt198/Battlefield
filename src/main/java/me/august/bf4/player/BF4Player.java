package me.august.bf4.player;

import me.august.bf4.BF4Plugin;
import me.august.bf4.team.BF4Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.ref.WeakReference;

public class BF4Player {

	public static BF4Player get(Player player) {
		for(BF4Player p : BF4Plugin.getCurrentMatch().getPlayers()) {
			if(p.getPlayer() == player) return p;
		}
		return null;
	}

	public static BF4Player get(String name) {
		return get(Bukkit.getPlayer(name));
	}

	private WeakReference<Player> player;
	private BF4Team team;

	public BF4Player(Player player) {
		this.player = new WeakReference<>(player);
	}

	public BF4Team getTeam() {
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
