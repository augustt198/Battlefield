package me.august.battlefield.util;

import me.august.battlefield.BattlefieldPlugin;
import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by August on 3/25/14.
 */
public class ItemUseAction implements Listener {

	private static List<ItemUseAction> itemActions = new ArrayList<>();

	public static void removeByPlayer(BattlefieldPlayer player) {
		List<ItemUseAction> remove = new ArrayList<>();
		for(ItemUseAction action : itemActions) {
			if(action.getPlayer() == player) {
				BattlefieldPlugin.unregisterListener(action);
				remove.add(action);
			}
		}
		for(ItemUseAction action : remove) itemActions.remove(action);
	}

	private List<Action> actions;
	private ItemStack item;
	private Runnable run;
	private boolean cancel;
	private BattlefieldPlayer player;

	public ItemUseAction(ItemStack item, BattlefieldPlayer player, Runnable run, Action... actions) {
		this(item, player, run, false, actions);
	}

	public ItemUseAction(ItemStack item, BattlefieldPlayer player, Runnable run, boolean cancel, Action... actions) {
		this.item = item;
		this.player = player;
		this.run = run;
		this.actions = Arrays.asList(actions);
		this.cancel = cancel;
		BattlefieldPlugin.registerListener(this);
		itemActions.add(this);
	}

	@EventHandler
	public void inventoryClickEvent(PlayerInteractEvent event) {
		if(event.getPlayer() == player.getPlayer() && event.getPlayer().getItemInHand().equals(item) && actions.contains(event.getAction())) {
			run.run();
			event.setCancelled(cancel);
		}
	}

	public List<Action> getActions() {
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
