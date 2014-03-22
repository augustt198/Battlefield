package me.august.bf4.guns;

import org.bukkit.inventory.ItemStack;

public abstract class KitItem {

	protected String name;

	public KitItem(String name) {
		this.name = name;
	}

	public void use() {}

	public abstract ItemStack toItem();

	public String getName() {
		return name;
	}

}
