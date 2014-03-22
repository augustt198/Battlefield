package me.august.bf4;

import org.bukkit.plugin.java.JavaPlugin;

public class BF4Plugin extends JavaPlugin {

	private static BF4Plugin instance;

	@Override
	public void onEnable() {
		instance = this;
	}

	@Override
	public void onDisable() {

	}

}
