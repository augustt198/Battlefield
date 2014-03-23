package me.august.bf4;

import me.august.bf4.game.Match;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BattlefieldPlugin extends JavaPlugin {

	private static BattlefieldPlugin instance;

	private Match currentMatch;

	@Override
	public void onEnable() {
		instance = this;

		currentMatch = new Match();

	}

	@Override
	public void onDisable() {

	}

	public static BattlefieldPlugin get() {
		return instance;
	}

	public static Match getCurrentMatch() {
		return get().currentMatch;
	}

}
