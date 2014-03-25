package me.august.battlefield.team;

import me.august.battlefield.game.Match;
import me.august.battlefield.player.BattlefieldPlayer;
import me.august.battlefield.player.DeployScreen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class BattlefieldTeam {

	private String name;
	private ChatColor color;
	private int maxPlayers;
	private Vector deployPoint;
	private Match match;

	public BattlefieldTeam(String name, ChatColor color, Vector deployPoint) {
		this(name, color, 32, deployPoint);
	}

	public BattlefieldTeam(String name, ChatColor color, int maxPlayers, Vector deployPoint) {
		this.name = name;
		this.color = color;
		this.maxPlayers = maxPlayers;
		this.deployPoint = deployPoint;
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
		player.setTeam(this);
		player.launchDeployScreen(2);
	}

}
