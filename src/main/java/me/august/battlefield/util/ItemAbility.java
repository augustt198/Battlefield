package me.august.battlefield.util;

import me.august.battlefield.player.BattlefieldPlayer;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 3/29/14.
 */
public class ItemAbility {

	private static List<ItemAbility> itemAbilities = new ArrayList<>();

	public static List<ItemAbility> getItemAbilities() {
		return itemAbilities;
	}

	public static void removeAbility(ItemAbility item) {
		itemAbilities.remove(item);
	}

	private ItemStack item;
	private BattlefieldPlayer player;
	private Runnable onItemClick;
	private Runnable onRightClick;
	private Runnable onLeftClick;
	private boolean droppable;
	private boolean movable;

	public ItemAbility(ItemStack item) {
		this.item = item;
		droppable = true;
		movable = true;
		itemAbilities.add(this);
	}

	public ItemAbility undroppable() {
		droppable = false;
		return this;
	}

	public ItemAbility droppable() {
		droppable = true;
		return this;
	}

	public ItemAbility onItemClick(Runnable onItemClick) {
		this.onItemClick = onItemClick;
		return this;
	}

	public ItemAbility unmovable() {
		movable = false;
		return this;
	}

	public ItemAbility movable() {
		movable = true;
		return this;
	}

	public ItemAbility withPlayer(BattlefieldPlayer player) {
		this.player = player;
		return this;
	}

	public ItemAbility onRightClick(Runnable onRightClick) {
		this.onRightClick = onRightClick;
		return this;
	}

	public ItemAbility onLeftClick(Runnable onLeftClick) {
		this.onLeftClick = onLeftClick;
		return this;
	}

	public ItemStack getItem() {
		return item;
	}

	public BattlefieldPlayer getPlayer() {
		return player;
	}

	public Runnable getOnItemClick() {
		return onItemClick;
	}

	public Runnable getOnRightClick() {
		return onRightClick;
	}

	public Runnable getOnLeftClick() {
		return onLeftClick;
	}

	public boolean isDroppable() {
		return droppable;
	}

	public boolean isMovable() {
		return movable;
	}
}
