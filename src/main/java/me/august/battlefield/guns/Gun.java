package me.august.battlefield.guns;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gun extends KitItem {

	private ItemType type;
	private GunRounds rounds;
	private int fireRate;
	private double accuracy;
	private double maxDamage;
	private Material material;

	public Gun(String name, BattlefieldClass gunClass, Material material, ItemType type,  int magazines, int bullets, int fireRate, double accuracy, double maxDamage) {
		super(name, gunClass);
		rounds = new GunRounds(magazines, bullets);

		this.material = material;
		this.type = type;
		this.fireRate = fireRate;
		this.accuracy = accuracy;
		this.maxDamage = maxDamage;
	}

	@Override
	public ItemStack toItem() {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public String toString() {
		return "Gun{" +
				"gunType=" + type.name() + ", " +
				"fireRate=" + String.valueOf(fireRate) + ", " +
				"accuracy=" + String.valueOf(accuracy) + ", " +
				"maxDamage=" + String.valueOf(maxDamage) + ", " +
				"name=" + name +
				"}";
	}

}
