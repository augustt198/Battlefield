package me.august.bf4.guns;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Kit {

	private BattlefieldClass type;
	private Map<GunType, KitItem> loadout;

	public Kit(BattlefieldClass type) {
		this.type = type;
		loadout = new HashMap<>();
	}

	public void setPrimary(KitItem item) {
		loadout.put(GunType.PRIMARY, item);
	}

	public void setSecondary(KitItem item) {
		loadout.put(GunType.SECONDARY, item);
	}

	public void setPrimaryGadget(KitItem item) {
		loadout.put(GunType.PRIMARY_GADGET, item);
	}

	public void setSecondaryGadget(KitItem item) {
		loadout.put(GunType.SECONDARY_GADGET, item);
	}

	public BattlefieldClass getType() {
		return type;
	}

	public void apply(Player player) {
		Inventory inv = player.getInventory();
		for(GunType gunType : loadout.keySet()) {
			inv.setItem(gunType.getSlot(), loadout.get(gunType).toItem());
		}
	}

	public KitItem getItem(GunType gunType) {
		return loadout.get(gunType);
	}

}
