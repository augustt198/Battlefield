package me.august.battlefield.guns;

public class GunAmmo implements Cloneable {

	private int magazines;
	private int bullets;
	private Gun gun;

	public GunAmmo(int magazines, int bullets, Gun gun) {
		this.magazines = magazines;
		this.bullets = bullets;
		this.gun = gun;
	}

	public int getMagazines() {
		return magazines;
	}

	public int getBullets() {
		return bullets;
	}

	public void setMagazines(int magazines) {
		this.magazines = magazines;
	}

	public void removeBullet() {
		bullets -= 1;
		if(bullets == 0) {
			magazines -= 1;
			if(magazines > 0) {
				bullets = gun.getBullets();
			}
		}
	}

	public boolean canFire() {
		return bullets != 0 && magazines != 0;
	}

	public void setBullets(int bullets) {
		this.bullets = bullets;
	}

	@Override
	public GunAmmo clone() {
		return new GunAmmo(magazines, bullets, gun);
	}

}
