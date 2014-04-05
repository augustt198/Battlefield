package me.august.battlefield.listener;

import me.august.battlefield.guns.Gun;
import me.august.battlefield.guns.GunAmmo;
import me.august.battlefield.guns.KitItem;
import me.august.battlefield.manager.Manager;
import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
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
import org.bukkit.util.Vector;

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
	public void useGun(PlayerInteractEvent event) {
		BattlefieldPlayer player = BattlefieldPlayer.get(event.getPlayer());
		if(player == null || player.isDeploying()) return;

		Gun gun = null;

		for(KitItem item : player.getLoadout().values()) {
			if(item.toItem().equals(event.getItem())) {
				if(item instanceof Gun) gun = (Gun) item;
			}
		}

		if(gun == null) return;

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Manager.zoom.add(player);
			if(gun.getZoom() != 0) {
				player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE,
						gun.getZoom(), true));
			}

		} else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			GunAmmo ammo = player.getAmmo().get(gun);
			Player p = event.getPlayer();
			if(ammo != null) {
				if(ammo.canFire()) {
					ammo.removeBullet();
				} else {
					p.sendMessage(ChatColor.RED + "Your " + gun.getName() + " is out of ammo!");
					return;
				}
			}
			float yaw = p.getLocation().getYaw();
			float pitch = p.getLocation().getPitch();
			Vector v = new Vector(
					Math.cos(Math.toRadians(yaw + 90)),
					Math.sin(Math.toRadians(-pitch)),
					Math.sin(Math.toRadians(yaw + 90))
			).multiply(gun.getSpeed());
			p.launchProjectile(Arrow.class, v);
			p.getWorld().playSound(p.getLocation(), Sound.FIREWORK_BLAST, 5, 0);

		}

	}

}
