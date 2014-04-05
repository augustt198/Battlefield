package me.august.battlefield.player;

import me.august.battlefield.BattlefieldClass;
import me.august.battlefield.BattlefieldPlugin;
import me.august.battlefield.guns.Gun;
import me.august.battlefield.guns.GunAmmo;
import me.august.battlefield.guns.ItemType;
import me.august.battlefield.guns.KitItem;
import me.august.battlefield.team.BattlefieldTeam;
import me.august.battlefield.util.ItemAbility;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattlefieldPlayer implements Spawnable {

	public static BattlefieldPlayer get(Player player) {
		for(BattlefieldPlayer p : BattlefieldPlugin.getCurrentMatch().getPlayers()) {
			if(p.getPlayer().equals(player)) return p;
		}
		return null;
	}

	public static BattlefieldPlayer get(String name) {
		return get(Bukkit.getPlayer(name));
	}

	private WeakReference<Player> player;
	private BattlefieldClass battlefieldClass;
	private BattlefieldTeam team;
	private Map<ItemType, KitItem> loadout;
	private Map<Gun, GunAmmo> ammo;
	private Squad squad;
	private boolean canDeploy;
	private boolean deploying;
	private Spawnable nextSpawn;

	public BattlefieldPlayer(Player player) {
		this.player = new WeakReference<>(player);
		battlefieldClass = BattlefieldClass.ASSAULT;
		loadout = new HashMap<>();
		ammo = new HashMap<>();
		canDeploy = false;
		deploying = false;
		BattlefieldPlugin.getCurrentMatch().addPlayer(this);
	}

	public void remove() {
		BattlefieldPlugin.getCurrentMatch().removePlayer(this);
	}

	public void setBattlefieldClass(BattlefieldClass battlefieldClass) {
		this.battlefieldClass = battlefieldClass;
	}

	public void setTeam(BattlefieldTeam team) {
		this.team = team;
	}

	public Squad getSquad() {
		return squad;
	}

	public boolean isInSquad() {
		return squad != null;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

	public BattlefieldTeam getTeam() {
		return team;
	}

	public BattlefieldClass getBattlefieldClass() {
		return battlefieldClass;
	}

	public Player getPlayer() {
		return player.get();
	}

	public String getName() {
		return getPlayer().getName();
	}

	public String getColoredName() {
		return "" + ChatColor.RESET + team.getColor() + getName() + ChatColor.RESET;
	}

	public boolean isAlive() {
		return !getPlayer().isDead();
	}

	public boolean isDeploying() {
		return deploying;
	}

	public Spawnable getNextSpawn() {
		return nextSpawn;
	}

	public Location getLocation() {
		return getPlayer().getLocation();
	}

	public List<SpawnPoint> getSpawnPoints() {
		List<SpawnPoint> spawnPoints = new ArrayList<>();

		if(squad != null) {
			for(BattlefieldPlayer player : squad.getPlayers()) {
				if(player == this) continue;
				if(!player.isAlive()) continue;
				spawnPoints.add(new SpawnPoint(SpawnPoint.Type.SQUAD_MATE, player, player.getName()));
			}
		}

		spawnPoints.add(new SpawnPoint(SpawnPoint.Type.TEAM_BASE, team, team.getNormalName() +  " base"));

		return spawnPoints;
	}

	public void launchDeployScreen(final int wait /* Wait until deploy */) {

		getPlayer().setGameMode(GameMode.CREATIVE);

		getPlayer().getInventory().clear();
		deploying = true;

		GunAmmo ammo = new GunAmmo(1, 1);

		new BukkitRunnable() {
			@Override
			public void run() {
				setCanDeploy(true);
			}
		}.runTaskLater(BattlefieldPlugin.get(), wait * 20);

		List<SpawnPoint> spawnPoints = getSpawnPoints();
		for(int i = 0; i < spawnPoints.size(); i++) {
			final SpawnPoint sp = spawnPoints.get(i);
			ItemStack spItem = sp.toItem();

			getPlayer().getInventory().setItem(i, spItem);

			new ItemAbility(spItem).undroppable().onLeftClick(new Runnable() {
				@Override
				public void run() {
					visitSpawnpoint(sp);
				}
			}).onRightClick(new Runnable() {
				@Override
				public void run() {
					sendMessage(ChatColor.GREEN + "Spawnpoint has been set!");
					setNextSpawn(sp.getSpawn());
				}
			}
			).unmovable().undroppable().withPlayer(this).unplaceable();
		}

		ItemStack menuItem = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta menuMeta = menuItem.getItemMeta();
		menuMeta.setDisplayName(ChatColor.AQUA + "Open Gun & Squad Menu");
		menuItem.setItemMeta(menuMeta);
		getPlayer().getInventory().setItem(7, menuItem);
		new ItemAbility(menuItem).onRightClick(new Runnable() {
			@Override
			public void run() {
				clearMenu();
				createDeployScreen(wait);
			}
		}).unmovable().undroppable().withPlayer(this);

		ItemStack deployItem = new ItemStack(Material.NETHER_STAR);
		ItemMeta deployItemMeta = deployItem.getItemMeta();
		deployItemMeta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Deploy");
		deployItem.setItemMeta(deployItemMeta);
		getPlayer().getInventory().setItem(8, deployItem);

		new ItemAbility(deployItem).onRightClick(new Runnable() {
			@Override
			public void run() {
				attemptDeploy();
			}
		}).unmovable().undroppable().withPlayer(this);

	}

	public void clearMenu() {
		ItemAbility.remove(this, "menu");
	}

	private void visitSpawnpoint(SpawnPoint point) {
		teleport(point.getSpawn().getLocation());
		getPlayer().playSound(getLocation(), Sound.ENDERMAN_TELEPORT, 4, 0);

	}



	@SuppressWarnings("deprecated")
	public void attemptDeploy() {
		if(!canDeploy) {
			sendMessage(ChatColor.RED + "You cannot deploy yet!");
		} else {
			if(nextSpawn == null) {
				sendMessage(ChatColor.RED + "You must select a spawnpoint");
				return;
			}
			if(loadout.size() < 2) {
				sendMessage(ChatColor.RED + "Please select a primary and secondary weapon before spawning");
				return;
			}
			deploying = false;
			sendMessage(ChatColor.GREEN + "Deploying");
			teleport(nextSpawn.getLocation());
			ItemAbility.remove(this);
			Player p = getPlayer();
			p.getInventory().clear();
			p.setGameMode(GameMode.SURVIVAL);
			p.setHealth(20);
			p.setSaturation(20);
			p.setFoodLevel(20);
			p.getInventory().clear();
			addKitToInventory();

			p.updateInventory();

		}
	}

	public void createDeployScreen(int wait) {
		new DeployScreen(this, wait);
	}

	private void addKitToInventory() {
		for(ItemType type : loadout.keySet()) {
			getPlayer().getInventory().setItem(type.getSlot(), loadout.get(type).toItem());
		}
	}

	public void setKitItem(ItemType type, KitItem item) {
		loadout.put(type, item);
		String weapon = type == ItemType.PRIMARY || type == ItemType.SECONDARY ? " weapon " : "";
		sendMessage(ChatColor.GREEN + "Your " + type.name().toLowerCase() + weapon + "has been set to " +
				ChatColor.GOLD + item.getName());
	}

	public KitItem getKitItem(ItemType type) {
		return loadout.get(type);
	}

	public boolean isCanDeploy() {
		return canDeploy;
	}

	public void setCanDeploy(boolean canDeploy) {
		this.canDeploy = canDeploy;
	}

	public Map<ItemType, KitItem> getLoadout() {
		return loadout;
	}

	public Map<Gun, GunAmmo> getAmmo() {
		return ammo;
	}

	/* Aliases */
	public void sendMessage(String msg) {
		getPlayer().sendMessage(msg);
	}

	public void teleport(Location location) {
		getPlayer().teleport(location);
	}

	public void setNextSpawn(Spawnable nextSpawn) {
		this.nextSpawn = nextSpawn;
	}
}
