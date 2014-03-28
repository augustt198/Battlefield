package me.august.battlefield.util;

import me.august.battlefield.BattlefieldPlugin;
import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by August on 3/24/14.
 */
public class ItemClickAction implements Listener {

	private static List<ItemClickAction> itemActions = new ArrayList<>();

	public static void removeByPlayer(BattlefieldPlayer player) {
		for(ItemClickAction action : itemActions) {
			if(action.getPlayer() == player) {
				BattlefieldPlugin.unregisterListener(action);
				itemActions.remove(action);
			}
		}
	}

	private List<InventoryAction> actions;
	private ItemStack item;
	private Runnable run;
	private boolean cancel;
	private BattlefieldPlayer player;

	public ItemClickAction(ItemStack item, BattlefieldPlayer player, Runnable run, InventoryAction... actions) {
		this(item, player, run, false, actions);
	}

	public ItemClickAction(ItemStack item, BattlefieldPlayer player, Runnable run, boolean cancel, InventoryAction... actions) {
		this.item = item;
		this.player = player;
		this.run = run;
		this.actions = Arrays.asList(actions);
		this.cancel = cancel;
		BattlefieldPlugin.registerListener(this);
		itemActions.add(this);
	}

	@EventHandler
	public void inventoryClickEvent(InventoryClickEvent event) {
		if(actions.contains(event.getAction()) && event.getCurrentItem().equals(item) && event.getWhoClicked() == player.getPlayer()) {
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

	public BattlefieldPlayer getPlayer() {
		return player;
	}

}
