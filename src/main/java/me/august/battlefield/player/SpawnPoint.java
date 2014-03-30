package me.august.battlefield.player;

import me.august.battlefield.util.ParsingUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpawnPoint {

	public static enum Type {
		TEAM_BASE, SQUAD_MATE, VEHICLE, OTHER
	}

	private Spawnable spawn;
	private Type spawnType;
	private String text;

	public SpawnPoint(Type spawnType, Spawnable spawn, String text) {
		this.spawnType = spawnType;
		this.spawn = spawn;
		this.text = text;
	}

	public Type getType() {
		return spawnType;
	}

	public Spawnable getSpawn() {
		return spawn;
	}

	public String getText() {
		return text;
	}

	public void spawnPlayer(BattlefieldPlayer player) {
		player.teleport(spawn.getLocation());
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

	public ItemStack toItem() {
		Material mat = Material.STONE;
		String displayName = ChatColor.GREEN + "Spawn on ";
		switch(spawnType) {

			case TEAM_BASE:
				mat = Material.ENDER_PORTAL;
				displayName += ChatColor.GOLD + "team base";
				break;
			case SQUAD_MATE:
				mat = Material.SKULL_ITEM;
				displayName += "squad mate " + ChatColor.GOLD + text;
				break;
			case VEHICLE:
				mat = Material.MINECART;
				displayName += "vehicle " + ChatColor.GOLD + text;
				break;

			default:
				displayName += "unknown";

		}

		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		List<String> lore = new ArrayList<>();
		lore.add("Coordinates: " + ParsingUtils.simpleCoordsString(spawn.getLocation().toVector()));
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;

	}

	@Override
	public String toString() {
		return "SpawnPoint{" +
				"spawnType=" + spawnType.name() + ", " +
				"location=" + spawn.getLocation().toString() + ", " +
				"text=" + text +
				"}";
	}

}
