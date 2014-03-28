package me.august.battlefield.player;

import org.bukkit.Location;

import java.util.List;

public class SpawnPoint {

	public static enum Type {
		TEAM_BASE, SQUAD_MATE, VEHICLE, OTHER
	}

	private Location location;	private Type spawnType;

	private String text;

	public SpawnPoint(Type spawnType, Location location, String text) {
		this.spawnType = spawnType;
		this.location = location;
		this.text = text;
	}

	public Type getType() {
		return spawnType;
	}

	public Location getLocation() {
		return location;
	}

	public String getText() {
		return text;
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
