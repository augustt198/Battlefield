package me.august.bf4.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.ref.WeakReference;

public class BF4Player {

	private WeakReference<Player> player;

	public BF4Player(Player player) {
		this.player = new WeakReference<>(player);
	}

	public Player get() {
		return player.get();
	}

	/* Aliases */
	public void sendMessage(String msg) {
		get().sendMessage(msg);
	}

	public void teleport(Location location) {
		get().teleport(location);
	}

}
