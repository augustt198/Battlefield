package me.august.battlefield.game;

/**
 * Created by August on 4/6/14.
 */
public class Cooldown {

	private long start;
	private int wait;

	public Cooldown(int wait) {
		this.wait = wait;
		start = System.currentTimeMillis();
	}

	public boolean isExpired() {
		return System.currentTimeMillis() - start > wait;
	}

}
