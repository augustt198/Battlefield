package me.august.bf4.game;

import me.august.bf4.player.BF4Player;
import me.august.bf4.team.BF4Team;

import java.util.ArrayList;
import java.util.List;

public class Match {

	private List<BF4Team> teams;
	private List<BF4Player> players;

	public Match() {
		teams = new ArrayList<>();
		players = new ArrayList<>();
	}

	public List<BF4Team> getTeams() {
		return teams;
	}

	public List<BF4Player> getPlayers() {
		return players;
	}

}
