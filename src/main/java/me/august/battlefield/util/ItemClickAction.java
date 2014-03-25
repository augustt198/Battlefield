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
public class ItemClickAction implements Listener {

	private List<InventoryAction> actions;
	private ItemStack item;
	private Runnable run;
	private boolean cancel;

	public ItemClickAction(ItemStack item, Runnable run, InventoryAction... actions) {
		this(item, run, false, actions);
	}

		public ItemClickAction(ItemStack item, Runnable run, boolean cancel, InventoryAction... actions) {
		this.item = item;
		this.run = run;
		this.actions = Arrays.asList(actions);
		this.cancel = cancel;
		BattlefieldPlugin.registerListener(this);
	}

	@EventHandler
	public void inventoryClickEvent(InventoryClickEvent event) {
		if(actions.contains(event.getAction())) {
			run.run();
			event.setCancelled(cancel);
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
