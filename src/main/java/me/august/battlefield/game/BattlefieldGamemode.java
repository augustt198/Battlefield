package me.august.battlefield.game;

/**
 * Created by August on 3/23/14.
 */
public enum BattlefieldGamemode {

	CONQUEST("Conquest"), RUSH("Rush"), TEAM_DEATH_MATCH("Team Death Match");

	private String name;

	BattlefieldGamemode(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
