package me.august.battlefield.game;

import java.util.List;

/**
 * Created by August on 3/23/14.
 */
public class RotationManager {

	private List<String> mapNames;
	private int current;
	private int next;

	public RotationManager(List<String> mapNames) {
		this.mapNames = mapNames;
		current = 0;
		next = 0;
	}

	public void cycle() {
		current = next;
		next++;
	}

	public List<String> getMapNames() {
		return mapNames;
	}

	public String getCurrent() {
		return mapNames.get(current);
	}

	public String getNext() {
		return mapNames.get(next);
	}

	public int getCurrentIndex() {
		return current;
	}

	public int getNextIndex() {
		return next;
	}

	public boolean setNextMap(String name) {
		if(!mapNames.contains(name)) return false;
		next = mapNames.indexOf(name);
		return true;
	}

	public void insertMap(String name) {
		mapNames.add(name);
	}

	public void insertMapAtIndex(String name, int index) {
		mapNames.add(index, name);
	}

}
