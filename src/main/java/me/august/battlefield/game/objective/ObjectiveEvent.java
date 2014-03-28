package me.august.battlefield.game.objective;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by August on 3/23/14.
 */
public class ObjectiveEvent extends Event {

	private static HandlerList handlers = new HandlerList();

	private Objective objective;

	public ObjectiveEvent(Objective objective) {
		this.objective = objective;
	}

	public Objective getObjective() {
		return objective;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
