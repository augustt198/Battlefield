package me.august.battlefield.listener;

import me.august.battlefield.guns.Gun;
import me.august.battlefield.guns.KitItem;
import me.august.battlefield.manager.Manager;
import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by August on 3/30/14.
 */
public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
	}

	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent event) {
		if(!(event.getEntity() instanceof Player)) return;
		event.setCancelled(true);
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		BattlefieldPlayer player = BattlefieldPlayer.get(event.getPlayer());
		if(player == null) return;
		player.launchDeployScreen(2);
	}

	@EventHandler
	public void scopeGun(PlayerInteractEvent event) {
		BattlefieldPlayer player = BattlefieldPlayer.get(event.getPlayer());
		if(player == null || player.isDeploying()) return;
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
			for(KitItem item : player.getLoadout().values()) {
				if(item.toItem().equals(event.getItem())) {
					Manager.zoom.add(player);
					if(item instanceof Gun) {
						Gun gun = (Gun) item;
						if(gun.getZoom() != 0) {
							player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE,
									gun.getZoom(), true));
						}
					}
				}
			}

		}
	}

}
