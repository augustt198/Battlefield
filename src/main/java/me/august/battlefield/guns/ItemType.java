package me.august.battlefield.guns;

public enum ItemType {

	PRIMARY(0), SECONDARY(1), PRIMARY_GADGET(2), SECONDARY_GADGET(3);

	private int slot;

	ItemType(int slot) {
		this.slot = slot;
	}

	public int getSlot() {
		return slot;
	}

	public static ItemType get(String name) {
		for(ItemType type : values()) {
			if(type.name().equalsIgnoreCase(name)) return type;
		}
		return null;
	}

}
