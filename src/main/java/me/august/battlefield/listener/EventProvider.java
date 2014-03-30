package me.august.battlefield.listener;

import me.august.battlefield.player.BattlefieldPlayer;
import me.august.battlefield.util.ItemAbility;
import me.august.battlefield.util.Log;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 3/29/14.
 */
public class EventProvider implements Listener {

	@EventHandler
	public void itemClick(InventoryClickEvent event) {
		for(ItemAbility item : new ArrayList<>(ItemAbility.getItemAbilities())) {
			if(item.getPlayer() != null && item.getPlayer().getPlayer() != event.getWhoClicked()) continue;
			if(item.getItem() == null || item.getOnItemClick() == null) continue;
			if(!event.getCurrentItem().equals(item.getItem())) continue;
			item.getOnItemClick().run();
			event.setCancelled(!item.isMovable());
		}
	}

	@EventHandler
	public void itemDrop(PlayerDropItemEvent event) {
		for(ItemAbility item :  new ArrayList<>(ItemAbility.getItemAbilities())) {
			if(item.getPlayer() != null && item.getPlayer().getPlayer() != event.getPlayer()) continue;
			if(!event.getItemDrop().getItemStack().equals(item.getItem())) continue;
			event.setCancelled(!item.isDroppable());
		}
	}

	@EventHandler
	public void itemUse(PlayerInteractEvent event) {
		for(ItemAbility item :  new ArrayList<>(ItemAbility.getItemAbilities())) {
			if(item.getPlayer() != null && item.getPlayer().getPlayer() != event.getPlayer()) continue;
			if(!event.getItem().equals(item.getItem())) continue;
			if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
				item.getOnLeftClick().run();
			}
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
				item.getOnRightClick().run();
			}
		}
	}

}
