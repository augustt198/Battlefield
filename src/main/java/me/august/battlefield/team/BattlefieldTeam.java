package me.august.battlefield.team;

import me.august.battlefield.game.Match;
import me.august.battlefield.player.BattlefieldPlayer;
import me.august.battlefield.player.Spawnable;
import me.august.battlefield.player.Squad;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class BattlefieldTeam implements Spawnable {

	private String name;
	private ChatColor color;
	private int maxPlayers;
	private Vector deployPoint;
	private Match match;
	private List<Squad> squads;

	public BattlefieldTeam(String name, ChatColor color, Vector deployPoint) {
		this(name, color, 32, deployPoint);
	}

	public BattlefieldTeam(String name, ChatColor color, int maxPlayers, Vector deployPoint) {
		this.name = name;
		this.color = color;
		this.maxPlayers = maxPlayers;
		this.deployPoint = deployPoint;
		squads = new ArrayList<>();
		for(int i = 0; i < Math.ceil(maxPlayers / Squad.MAX_SQUAD_SIZE); i++) {
			squads.add(new Squad(Squad.SQUAD_NAMES[i], false));
		}
	}

	public String getName() {
		return "" + ChatColor.RESET + color + name + ChatColor.RESET;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public String getNormalName() {
		return name;
	}

	public ChatColor getColor() {
		return color;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public Vector getDeployPoint() {
		return deployPoint;
	}

	public int getPlayerCount() {
		int members = 0;
		for(BattlefieldPlayer p : match.getPlayers()) {
			if(p.getTeam() == this) members++;
		}
		return members;
	}

	public List<BattlefieldPlayer> getPlayers() {
		List<BattlefieldPlayer> players = new ArrayList<>();
		for(BattlefieldPlayer p : match.getPlayers()) {
			if(p.getTeam() == this) players.add(p);
		}
		return players;
	}

	public void addPlayer(BattlefieldPlayer player) {
		player.sendMessage(ChatColor.AQUA + "You have joined " + getName());
		player.getPlayer().setDisplayName(color + player.getName());
		player.getPlayer().setPlayerListName(color + player.getName());
		player.setTeam(this);
		player.launchDeployScreen(2);
	}

	public List<Squad> getSquads() {
		return squads;
	}

	public Match getMatch() {
		return match;
	}

	public Vector getBase() {
		return deployPoint;
	}

	public Location getBaseLocation() {
		return deployPoint.toLocation(match.getWorld());
	}

	public Location getLocation() {
		return getBaseLocation();
	}

}
