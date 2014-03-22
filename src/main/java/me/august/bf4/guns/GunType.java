package me.august.bf4.guns;

public enum GunType {

	PRIMARY(0), SECONDARY(1), PRIMARY_GADGET(2), SECONDARY_GADGET(3);

	private int slot;

	GunType(int slot) {
		this.slot = slot;
	}

	public int getSlot() {
		return slot;
	}

	public static GunType get(String name) {
		try {
			return GunType.valueOf(name.toUpperCase().replace(" ", "_"));
		} catch(IllegalArgumentException e) {
			return null;
		}
	}

}
