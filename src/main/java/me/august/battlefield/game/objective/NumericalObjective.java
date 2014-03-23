package me.august.battlefield.game.objective;

/**
 * Created by August on 3/23/14.
 */
public class NumericalObjective extends Objective {

	private int current;
	private int max;

	public NumericalObjective(String name, int current, int max) {
		super(name);
		this.current = current;
		this.max = max;
	}

	public boolean isComplete() {
		return current >= max;
	}

	public int getCurrent() {
		return current;
	}

	public int getMax() {
		return max;
	}
}
