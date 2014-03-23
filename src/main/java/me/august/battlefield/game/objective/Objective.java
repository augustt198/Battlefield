package me.august.battlefield.game.objective;

/**
 * Created by August on 3/23/14.
 */
public abstract class Objective {

	protected String name;

	public Objective(String name) {
		this.name = name;
	}

	public abstract boolean isComplete();

	public String getName() {
		return name;
	}

}
