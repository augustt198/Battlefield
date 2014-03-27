package me.august.battlefield.player;

import me.august.battlefield.BattlefieldPlugin;
import me.august.battlefield.BattlefieldClass;
import me.august.battlefield.guns.ItemType;
import me.august.battlefield.guns.KitItem;
import me.august.battlefield.team.BattlefieldTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BattlefieldPlayer {

	public static BattlefieldPlayer get(Player player) {
		for(BattlefieldPlayer p : BattlefieldPlugin.getCurrentMatch().getPlayers()) {
			if(p.getPlayer() == player) return p;
		}
		return null;
	}

	public static BattlefieldPlayer get(String name) {
		return get(Bukkit.getPlayer(name));
	}

	private WeakReference<Player> player;
	private BattlefieldClass battlefieldClass;
	private BattlefieldTeam team;
	private Map<ItemType, KitItem> loadout;
	private Squad squad;

	public BattlefieldPlayer(Player player) {
		this.player = new WeakReference<>(player);
		battlefieldClass = BattlefieldClass.ASSAULT;
		loadout = new HashMap<>();
	}

	public void remove() {
		BattlefieldPlugin.getCurrentMatch().getPlayers().remove(this);
	}

	public void setBattlefieldClass(BattlefieldClass battlefieldClass) {
		this.battlefieldClass = battlefieldClass;
	}

	public void setTeam(BattlefieldTeam team) {
		this.team = team;
	}

	public Squad getSquad() {
		return squad;
	}

	public boolean isInSquad() {
		return squad != null;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

	public BattlefieldTeam getTeam() {
		return team;
	}

	public BattlefieldClass getBattlefieldClass() {
		return battlefieldClass;
	}

	public Player getPlayer() {
		return player.get();
	}

	public String getName() {
		return getPlayer().getName();
	}

	public String getColoredName() {
		return "" + ChatColor.RESET + team.getColor() + getName() + ChatColor.RESET;
	}

	public void launchDeployScreen(int wait /* Wait until deploy */) {
		new DeployScreen(this, wait);
	}

	public void setKitItem(ItemType type, KitItem item) {
		loadout.put(type, item);
		String weapon = type == ItemType.PRIMARY || type == ItemType.SECONDARY ? " weapon " : "";
		sendMessage(ChatColor.GREEN + "Your " + type.name().toLowerCase() + weapon + "has been set to " +
				ChatColor.GOLD + item.getName());
	}

	public KitItem getKitItem(ItemType type) {
		return loadout.get(type);
	}

	public Map<ItemType, KitItem> getLoadout() {
		return loadout;
	}

	/* Aliases */
	public void sendMessage(String msg) {
		getPlayer().sendMessage(msg);
	}

	public void teleport(Location location) {
		getPlayer().teleport(location);
	}

}
