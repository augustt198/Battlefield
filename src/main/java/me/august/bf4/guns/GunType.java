package me.august.bf4.guns;

public enum GunType {

	PRIMARY, SECONDARY, TERTIARY;

	public static GunType get(String name) {
		try {
			return GunType.valueOf(name.toUpperCase());
		} catch(IllegalArgumentException e) {
			return null;
		}
	}

}
