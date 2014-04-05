package me.august.battlefield.manager;

import me.august.battlefield.BattlefieldPlugin;
import me.august.battlefield.guns.Gun;
import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.entity.Player;

/**
 * Created by August on 4/5/14.
 */
public class AmmoManager implements Runnable {

	@Override
	public void run() {
		for(BattlefieldPlayer player : BattlefieldPlugin.getCurrentMatch().getPlayers()) {
			if(player.getKitItemInHand() instanceof Gun) {
				Gun gun = (Gun) player.getKitItemInHand();
				Player p = player.getPlayer();
				p.setLevel(player.getAmmo().get(gun).getMagazines());
				p.setExp(((float) player.getAmmo().get(gun).getBullets() / gun.getBullets()));
			}
		}
	}

}
