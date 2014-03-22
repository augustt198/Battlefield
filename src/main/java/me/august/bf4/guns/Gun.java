package me.august.bf4.guns;

import org.bukkit.Material;

public class Gun {

	private GunType type;
	private GunRounds rounds;
	private int fireRate;
	private double accuracy;
	private Material material;
	private String name;

	public Gun(String name, Material material, GunType type,  int magazines, int bullets, int fireRate, double accuracy) {
		rounds = new GunRounds(magazines, bullets);

		this.name = name;
		this.material = material;
		this.type = type;
		this.fireRate = fireRate;
		this.accuracy = accuracy;
	}

}
