package me.august.battlefield.listener;

import me.august.battlefield.util.ItemAbility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

/**
 * Created by August on 3/29/14.
 */
public class EventProvider implements Listener {

	@EventHandler
	public void itemClick(InventoryClickEvent event) {
		for(ItemAbility item : new ArrayList<>(ItemAbility.getItemAbilities())) {
			if(item.getPlayer() != null && item.getPlayer().getPlayer() != event.getWhoClicked()) continue;
			if(item.getItem() == null || !event.getCurrentItem().equals(item.getItem())) continue;
			event.setCancelled(!item.isMovable());
			if(item.getOnItemClick() != null) {
				item.getOnItemClick().run();
			}
		}
	}

	@EventHandler
	public void itemDrop(PlayerDropItemEvent event) {
		for(ItemAbility item : new ArrayList<>(ItemAbility.getItemAbilities())) {
			if(item.getPlayer() != null && item.getPlayer().getPlayer() != event.getPlayer()) continue;
			if(!event.getItemDrop().getItemStack().equals(item.getItem())) continue;
			event.setCancelled(!item.isDroppable());
		}
	}

	@EventHandler
	public void itemUse(PlayerInteractEvent event) {
		for(ItemAbility item : new ArrayList<>(ItemAbility.getItemAbilities())) {
			if(item.getPlayer() != null && item.getPlayer().getPlayer() != event.getPlayer()) continue;
			if(!event.getPlayer().getItemInHand().equals(item.getItem())) continue;
			if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
				if(item.getOnLeftClick() != null) {
					item.getOnLeftClick().run();
				}
			}
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
				if(item.getOnRightClick() != null) {
					item.getOnRightClick().run();
				}
			}
		}
	}

	@EventHandler
	public void blockPlace(BlockPlaceEvent event) {
		for(ItemAbility item : new ArrayList<>(ItemAbility.getItemAbilities())) {
			if(item.getPlayer() != null && item.getPlayer().getPlayer() != event.getPlayer()) continue;
			if(!event.getItemInHand().equals(item.getItem())) continue;
			event.setCancelled(!item.isPlaceable());
		}
	}

}
