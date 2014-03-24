package me.august.battlefield.guns;

import me.august.battlefield.BattlefieldClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Kit {

	private BattlefieldClass type;
	private Map<ItemType, KitItem> loadout;

	public Kit(BattlefieldClass type) {
		this.type = type;
		loadout = new HashMap<>();
	}

	public void setPrimary(KitItem item) {
		loadout.put(ItemType.PRIMARY, item);
	}

	public void setSecondary(KitItem item) {
		loadout.put(ItemType.SECONDARY, item);
	}

	public void setPrimaryGadget(KitItem item) {
		loadout.put(ItemType.PRIMARY_GADGET, item);
	}

	public void setSecondaryGadget(KitItem item) {
		loadout.put(ItemType.SECONDARY_GADGET, item);
	}

	public BattlefieldClass getType() {
		return type;
	}

	public void apply(Player player) {
		Inventory inv = player.getInventory();
		for(ItemType itemType : loadout.keySet()) {
			inv.setItem(itemType.getSlot(), loadout.get(itemType).toItem());
		}
	}

	public KitItem getItem(ItemType itemType) {
		return loadout.get(itemType);
	}

}
