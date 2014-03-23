package me.august.bf4.game;

import me.august.bf4.player.BattlefieldPlayer;
import me.august.bf4.team.BattlefieldTeam;

import java.util.ArrayList;
import java.util.List;

public class Match {

	private List<BattlefieldTeam> teams;
	private List<BattlefieldPlayer> players;

	public Match() {
		teams = new ArrayList<>();
		players = new ArrayList<>();
	}

	public List<BattlefieldTeam> getTeams() {
		return teams;
	}

	public List<BattlefieldPlayer> getPlayers() {
		return players;
	}

}
