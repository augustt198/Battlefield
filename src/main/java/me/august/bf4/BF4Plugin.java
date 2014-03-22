package me.august.bf4;

import me.august.bf4.game.Match;
import org.bukkit.plugin.java.JavaPlugin;

public class BF4Plugin extends JavaPlugin {

	private static BF4Plugin instance;

	private Match currentMatch;

	@Override
	public void onEnable() {
		instance = this;

		currentMatch = new Match();

	}

	@Override
	public void onDisable() {

	}

	public static BF4Plugin get() {
		return instance;
	}

	public static Match getCurrentMatch() {
		return get().currentMatch;
	}

}
