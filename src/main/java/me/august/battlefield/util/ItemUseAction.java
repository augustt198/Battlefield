package me.august.battlefield.util;

import me.august.battlefield.BattlefieldPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by August on 3/25/14.
 */
public class ItemUseAction implements Listener {

	private List<Action> actions;
	private ItemStack item;
	private Runnable run;
	private boolean cancel;

	public ItemUseAction(ItemStack item, Runnable run, Action... actions) {
		this(item, run, false, actions);
	}

	public ItemUseAction(ItemStack item, Runnable run, boolean cancel, Action... actions) {
		this.item = item;
		this.run = run;
		this.actions = Arrays.asList(actions);
		this.cancel = cancel;
		BattlefieldPlugin.registerListener(this);
	}

	@EventHandler
	public void inventoryClickEvent(PlayerInteractEvent event) {
		if(event.getPlayer().getItemInHand() == item && actions.contains(event.getAction())) {
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

}
