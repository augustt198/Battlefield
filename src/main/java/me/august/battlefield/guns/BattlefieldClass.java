package me.august.battlefield.guns;

public enum BattlefieldClass {

	ASSAULT, ENGINEER, SUPPORT, RECON,
	/* For PDW guns */
	ALL;

	public static BattlefieldClass get(String name) {
		for(BattlefieldClass c : values()) {
			if(c.name().equalsIgnoreCase(name)) return c;
		}
		return null;
	}

}
