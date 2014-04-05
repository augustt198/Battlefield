package me.august.battlefield.guns;

import me.august.battlefield.BattlefieldClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gun extends KitItem {

	private ItemType type;
	private GunAmmo ammo;
	private int fireRate;
	private double accuracy;
	private double maxDamage;
	private int zoom;
	private double speed;
	private Material material;
	private int magazines;
	private int bullets;

	public Gun(String name, BattlefieldClass gunClass, Material material, ItemType type,  int magazines, int bullets,
			   int fireRate, double accuracy, double maxDamage, int zoom, double speed) {
		super(name, gunClass);
		ammo = new GunAmmo(magazines, bullets, this);

		this.material = material;
		this.type = type;
		this.fireRate = fireRate;
		this.accuracy = accuracy;
		this.maxDamage = maxDamage;
		this.zoom = zoom;
		this.speed = speed;
		this.magazines = magazines;
		this.bullets = bullets;
	}

	@Override
	public ItemStack toItem() {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	public int getFireRate() {
		return fireRate;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public double getMaxDamage() {
		return maxDamage;
	}

	public int getZoom() {
		return zoom;
	}

	public Material getMaterial() {
		return material;
	}

	public double getSpeed() {
		return speed;
	}

	public ItemType getType() {
		return type;
	}

	public GunAmmo getAmmo() {
		return ammo;
	}

	/* Default ammunition */
	public int getMagazines() {
		return magazines;
	}

	public int getBullets() {
		return bullets;
	}

	@Override
	public String toString() {
		return "Gun{" +
				"gunType=" + type.name() + ", " +
				"fireRate=" + fireRate + ", " +
				"accuracy=" + accuracy + ", " +
				"maxDamage=" + maxDamage + ", " +
				"zoom=" + zoom + ", " +
				"speed=" + speed + ", " +
				"name=" + name + ", " +
				"bfClass=" + battlefieldClass.name() +
				"}";
	}

}
