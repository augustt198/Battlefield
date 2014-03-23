package me.august.battlefield.guns;

public class GunRounds {

	private int magazines;
	private int bullets;

	public GunRounds(int magazines, int bullets) {
		this.magazines = magazines;
		this.bullets = bullets;
	}

	public int getMagazines() {
		return magazines;
	}

	public int getBullets() {
		return bullets;
	}

}
