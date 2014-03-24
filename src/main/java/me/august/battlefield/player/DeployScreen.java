package me.august.battlefield.player;

import me.august.battlefield.BattlefieldPlugin;
import me.august.battlefield.guns.BattlefieldClass;
import me.august.battlefield.guns.Gun;
import me.august.battlefield.guns.KitItem;
import me.august.battlefield.util.ItemAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 3/24/14.
 */
public class DeployScreen {

	private BattlefieldPlayer player;
	private int wait;
	private boolean canDeploy;
	private Inventory screen;
	private BattlefieldClass viewingClass;

	public DeployScreen(BattlefieldPlayer player, int wait) {
		this.player = player;
		this.viewingClass = player.getBattlefieldClass();
		this.wait = wait;
		canDeploy = false;
		new BukkitRunnable() {
			@Override
			public void run() {
				setCanDeploy(true);
			}
		}.runTaskLater(BattlefieldPlugin.get(), wait);

		screen = Bukkit.createInventory(player.getPlayer(), 45, ChatColor.BOLD + "Deploy");

		/* Give player blindness */
		player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1, true));

		openScreen();

	}

	public void openScreen() {
		List<ItemAction> classViewers = new ArrayList<>();
		for(final BattlefieldClass bfClass : BattlefieldClass.values()) {
			if(bfClass == BattlefieldClass.ALL) continue;
			classViewers.add(new ItemAction(new ItemStack(Material.STONE), new Runnable() {
				@Override
				public void run() {
					showClass(bfClass);
				}
			}, InventoryAction.PICKUP_ALL));
		}
		for(int i = 0; i < classViewers.size(); i++) {
			screen.setItem(i, classViewers.get(i).getItem());
		}

	}

	public void showClass(BattlefieldClass bfClass) {
		viewingClass = bfClass;
		List<Gun> classGuns = new ArrayList<>();
		for(KitItem item : BattlefieldPlugin.get().getAvailableItems()) {
			if(!(item instanceof Gun)) continue;
			if(item.getBattlefieldClass() == viewingClass) {
				classGuns.add((Gun) item);
			}
		}
		for(int i = 9; i < 9 + classGuns.size(); i++) {
			screen.setItem(i, classGuns.get(i).toItem());
		}
	}

	public BattlefieldPlayer getPlayer() {
		return player;
	}

	public int getWait() {
		return wait;
	}

	private void setCanDeploy(boolean canDeploy) {
		this.canDeploy =  canDeploy;
	}
}
