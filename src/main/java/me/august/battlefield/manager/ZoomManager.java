package me.august.battlefield.manager;

import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 3/31/14.
 */
public class ZoomManager implements Runnable {

	private Map<BattlefieldPlayer, Integer> zoomed = new HashMap<>();
	private int runs = 0;

	public void add(BattlefieldPlayer player) {
		zoomed.put(player, runs);
	}

	public void run() {
		runs++;
		for(BattlefieldPlayer player : zoomed.keySet()) {
			if(runs - zoomed.get(player) > 5) {
				player.getPlayer().removePotionEffect(PotionEffectType.SLOW);
				zoomed.remove(player);
			}
		}
	}

}
