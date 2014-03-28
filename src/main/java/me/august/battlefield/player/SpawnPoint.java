package me.august.battlefield.player;

import org.bukkit.Location;

import java.util.List;

public class SpawnPoint {

	public static enum Type {
		TEAM_BASE, SQUAD_MATE, VEHICLE, OTHER
	}

	private Type spawnType;
	private Location location;

	public SpawnPoint(Type spawnType, Location location) {
		this.spawnType = spawnType;
		this.location = location;
	}

	public Type getType() {
		return spawnType;
	}

	public Location getLocation() {
		return location;
	}

	public void spawnPlayer(BattlefieldPlayer player) {
		player.teleport(location);
	}

	public void spawnPlayers(BattlefieldPlayer... players) {
		for(BattlefieldPlayer player : players) {
			spawnPlayer(player);
		}
	}

	public void spawnPlayers(List<BattlefieldPlayer> players) {
		for(BattlefieldPlayer player : players) {
			spawnPlayer(player);
		}
	}

}
