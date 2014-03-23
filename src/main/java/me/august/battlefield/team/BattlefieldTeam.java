package me.august.battlefield.team;

import org.bukkit.ChatColor;

public class BattlefieldTeam {

	private String name;
	private ChatColor color;
	private int maxPlayers;

	public BattlefieldTeam(String name, ChatColor color) {
		this(name, color, 32);
	}

	public BattlefieldTeam(String name, ChatColor color, int maxPlayers) {
		this.name = name;
		this.color = color;
		this.maxPlayers = maxPlayers;
	}

	public String getName() {
		return "" + ChatColor.RESET + color + name + ChatColor.RESET;
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

}
