package me.august.battlefield.guns;

import org.bukkit.inventory.ItemStack;

public abstract class KitItem {

	protected String name;
	protected BattlefieldClass battlefieldClass;

	public KitItem(String name) {
		this(name, BattlefieldClass.ALL);
	}

	public KitItem(String name, BattlefieldClass battlefieldClass) {
		this.name = name;
		this.battlefieldClass = battlefieldClass;
	}

	public void use() {}

	public abstract ItemStack toItem();

	public String getName() {
		return name;
	}

	public BattlefieldClass getBattlefieldClass() {
		return battlefieldClass;
	}
}
