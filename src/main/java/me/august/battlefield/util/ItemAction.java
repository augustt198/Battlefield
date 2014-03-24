package me.august.battlefield.util;

import me.august.battlefield.BattlefieldPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by August on 3/24/14.
 */
public class ItemAction implements Listener {

	List<InventoryAction> actions;
	ItemStack item;
	Runnable run;

	public ItemAction(ItemStack item, Runnable run, InventoryAction... actions) {
		this.item = item;
		this.run = run;
		this.actions = Arrays.asList(actions);
		BattlefieldPlugin.registerListener(this);
	}

	@EventHandler
	public void inventoryClickEvent(InventoryClickEvent event) {
		if(actions.contains(event.getAction())) {
			run.run();
		}
	}

	public List<InventoryAction> getActions() {
		return actions;
	}

	public ItemStack getItem() {
		return item;
	}

	public Runnable getRunnable() {
		return run;
	}
}
