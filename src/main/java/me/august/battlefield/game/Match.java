package me.august.battlefield.game;

import me.august.battlefield.player.BattlefieldPlayer;
import me.august.battlefield.team.BattlefieldTeam;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {

	private World world;
	private BattlefieldMap map;
	private List<BattlefieldTeam> teams;
	private List<BattlefieldPlayer> players;

	public Match(BattlefieldMap map, World world) {
		this.map = map;
		this.world = world;

		teams = new ArrayList<>();
		players = new ArrayList<>();
	}

	public void addTeam(BattlefieldTeam team) {
		teams.add(team);
	}

	public World getWorld() {
		return world;
	}

	public BattlefieldMap getMap() {
		return map;
	}

	public List<BattlefieldTeam> getTeams() {
		return teams;
	}

	public List<BattlefieldPlayer> getPlayers() {
		return players;
	}

	public void addPlayer(BattlefieldPlayer player) {
		players.add(player);
	}

	public void removePlayer(BattlefieldPlayer player) {
		players.remove(player);
	}

	public void joinRandomTeam(BattlefieldPlayer player) {
		int lowest = teams.get(0).getMaxPlayers();
		for(BattlefieldTeam team : teams) {
			int count = team.getPlayerCount();
			if(count < lowest) lowest = count;
		}
		List<BattlefieldTeam> joinable = new ArrayList<>();
		for(BattlefieldTeam team : teams) {
			if(team.getPlayerCount() == lowest) joinable.add(team);
		}
		BattlefieldTeam join = joinable.get(new Random().nextInt(joinable.size()));
		join.addPlayer(player);
	}

}
