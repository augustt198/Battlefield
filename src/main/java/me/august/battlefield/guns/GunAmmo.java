package me.august.battlefield.guns;

public class GunAmmo implements Cloneable {

	private int magazines;
	private int bullets;

	public GunAmmo(int magazines, int bullets) {
		this.magazines = magazines;
		this.bullets = bullets;
	}

	public int getMagazines() {
		return magazines;
	}

	public int getBullets() {
		return bullets;
	}

	@Override
	public GunAmmo clone() {
		return new GunAmmo(magazines, bullets);
	}

}
